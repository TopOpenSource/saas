package com.sdstc.gateway.rest;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "oauth")
public interface TestService {

}
