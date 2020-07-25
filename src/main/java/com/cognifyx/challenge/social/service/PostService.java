package com.cognifyx.challenge.social.service;


import java.util.List;

import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Post;
import com.cognifyx.challenge.social.dto.PostDTO;

public interface PostService {

	PostDTO save(Post post);
	
	List<PostDTO> getPostsByUserId(Long userId);
	
	PostDTO getPostById(Long postId);
	
	AggregateCountResult getUserInfoWithMaximumPosts();

}
