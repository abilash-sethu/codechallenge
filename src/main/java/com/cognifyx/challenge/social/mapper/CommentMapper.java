package com.cognifyx.challenge.social.mapper;

import org.mapstruct.Mapper;

import com.cognifyx.challenge.social.client.model.CommentResponse;
import com.cognifyx.challenge.social.domain.Comment;


@Mapper(componentModel = "spring")
public interface CommentMapper {
	
	Comment map(CommentResponse commentResponse);
	

}
