package com.cognifyx.challenge.social.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${client.name:comment}", url="${integration.base-url}")
public interface CommentApiClient extends CommentApi{
}
