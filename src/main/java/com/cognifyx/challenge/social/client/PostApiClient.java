package com.cognifyx.challenge.social.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@FeignClient(name="${client.name:post}", url="${integration.base-url}")
@Profile("!test")
public interface PostApiClient extends PostApi{
}
