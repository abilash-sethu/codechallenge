package com.cognifyx.challenge.social.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cognifyx.challenge.social.client.PostApi;
import com.cognifyx.challenge.social.client.model.PostResponse;
import com.cognifyx.challenge.social.domain.Post;
import com.cognifyx.challenge.social.mapper.PostMapper;
import com.cognifyx.challenge.social.service.PostService;

public class LoadPostInformationProcess implements Process {

	public Logger logger = LoggerFactory.getLogger(LoadPostInformationProcess.class);

	private PostApi postApi;

	private PostService postService;
	
	private PostMapper postMapper;

	public LoadPostInformationProcess(PostApi postApi, PostService postService,PostMapper postMapper) {
		this.postApi = postApi;
		this.postService = postService;
		this.postMapper=postMapper;
	}

	@Override
	public void run() {
		try {
			logger.trace("start");
			List<PostResponse> posts = postApi.getPots().getBody();
			logger.debug("posts {}", posts.size());
			logger.debug("posts data {}", posts);
			posts.forEach(postResponse -> {
				Post postDoc = postMapper.map(postResponse);
				postService.save(postDoc);
			});
		} catch (Exception ex) {
			logger.error("error occurred while loading posts {}", ex.getMessage(), ex);
		}

	}

}
