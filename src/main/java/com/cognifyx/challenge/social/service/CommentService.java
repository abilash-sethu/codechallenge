package com.cognifyx.challenge.social.service;



import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Comment;

public interface CommentService {

	Comment save(Comment comment);
	
	AggregateCountResult getPostWithMaximumComments();
	
}
