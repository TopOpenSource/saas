package com.sdstc.rest.oauth;

import com.sdstc.rest.oauth.impl.Oauth2ServiceImpl;
import com.sdstc.system.dto.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "oauth2")
public interface Oauth2Service {
    @PostMapping("oauth/token")
    TokenDto getToken(@RequestParam Map<String, String> parameters);

}
