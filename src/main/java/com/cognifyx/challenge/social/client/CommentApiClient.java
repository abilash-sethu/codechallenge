package com.cognifyx.challenge.social.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@FeignClient(name="${client.name:comment}", url="${integration.base-url}")
@Profile("!test")
public interface CommentApiClient extends CommentApi{
}
