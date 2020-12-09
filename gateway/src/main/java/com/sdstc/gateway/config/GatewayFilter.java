package com.sdstc.gateway.config;

import cn.hutool.core.util.CharsetUtil;
import com.sdstc.gateway.rest.oauth.service.TokenService;
import com.sdstc.pub.common.ResultDto;
import com.sdstc.pub.common.SystemCodeEnum;
import com.sdstc.pub.constant.SystemConstant;
import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cheng
 * gateway全局拦截
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    @Autowired
    private TokenService tokenService;

    @Value("${auth.ignoreurls}")
    private String[] ignoreurls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
        //获取token
        String token = this.getToken(request);
        if(!StringUtils.isEmpty(token)){
            //设置token 用于feign传输
            FeignOauth2RequestInterceptor.setToken(token);
        }

        if(this.isMatchIgnoreURLs(url)){
            //无需验证的url
            return chain.filter(exchange);
        }else{
            //获取用户信息
            LoginUserInfo userInfo = tokenService.hasPerm(url);
            //验证是否有权限
            if(userInfo.getHasPerm()){
                return chain.filter(this.addTenantId2Header(exchange,userInfo));
            }else{
                return this.unauthorized(exchange);
            }
        }
    }

    /**
     * 将登录信息加入header
     * @param exchange
     * @param loginUserInfo
     * @return
     */
    private ServerWebExchange addTenantId2Header(ServerWebExchange exchange,LoginUserInfo loginUserInfo){
        ServerHttpRequest host = exchange.getRequest().mutate().header(SystemConstant.TENANTID_HEADER, String.valueOf(loginUserInfo.getTenantId()))
                                                               .header(SystemConstant.LOGIN_USER_ID,String.valueOf(loginUserInfo.getId()))
                                                               .header(SystemConstant.LOGIN_USER_ACCOUNT,loginUserInfo.getUserAccount())
                                                               .build();

        ServerWebExchange build = exchange.mutate().request(host).build();
        return build;
    }

    /**
     * 从请求中获取token
     *
     * @param request
     * @return
     */
    private String getToken(ServerHttpRequest request) {
        /**
         * 1.从header中获取token
         * 2.如果不存在在params中获取
         */
        List<String> tokens = request.getHeaders().get(SystemConstant.AUTHORIZATION_HEADER);
        if (tokens != null && tokens.size() == 1) {
            return tokens.get(0);
        } else {
            MultiValueMap<String, String> params=request.getQueryParams();
            tokens=params.get(SystemConstant.AUTHORIZATION_HEADER);
            if (tokens != null && tokens.size() == 1) {
                return "Bearer  "+tokens.get(0);
            }else{
                return null;
            }
        }
    }
    /**
     * 验证URL是否为忽略验证
     *
     * @return
     */
    private boolean isMatchIgnoreURLs(String url) {
        boolean hasPerm=false;
        //规则验证
        PathMatcher matcher = new AntPathMatcher();
        List<String>matchUrl=Arrays.stream(ignoreurls).filter(x -> {
            return matcher.match(x,url);
        }).collect(Collectors.toList());

        if(matchUrl!=null&&matchUrl.size()>0){
            hasPerm=true;
        }
        return hasPerm;
    }


    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ResultDto result = new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(result.toJsonString().getBytes(CharsetUtil.systemCharset()));
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
