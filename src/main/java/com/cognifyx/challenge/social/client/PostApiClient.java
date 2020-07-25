package com.cognifyx.challenge.social.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${client.name:post}", url="${integration.base-url}")
public interface PostApiClient extends PostApi{
}
