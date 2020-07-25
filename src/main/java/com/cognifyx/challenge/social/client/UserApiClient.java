package com.cognifyx.challenge.social.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${client.name:user}", url="${integration.base-url}")
public interface UserApiClient extends UserApi{

}
