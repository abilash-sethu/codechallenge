package com.cognifyx.challenge.social.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cognifyx.challenge.social.client.model.PostResponse;
import com.cognifyx.challenge.social.domain.Post;
import com.cognifyx.challenge.social.dto.PostDTO;

@Mapper(componentModel = "spring")
public interface PostMapper {
	
	Post map(PostResponse postResponse);
	PostDTO map(Post post);
	List<PostDTO> map(List<Post> posts);

}
