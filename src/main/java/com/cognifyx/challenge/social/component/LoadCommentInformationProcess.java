package com.cognifyx.challenge.social.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifyx.challenge.social.client.CommentApi;
import com.cognifyx.challenge.social.client.model.CommentResponse;
import com.cognifyx.challenge.social.domain.Comment;
import com.cognifyx.challenge.social.mapper.CommentMapper;
import com.cognifyx.challenge.social.service.CommentService;

public class LoadCommentInformationProcess implements Process {

	public Logger logger = LoggerFactory.getLogger(LoadCommentInformationProcess.class);

	private CommentApi commentApi;

	private CommentService commentService;
	
	private CommentMapper commentMapper;
	public LoadCommentInformationProcess(CommentApi commentApi, CommentService commentService,CommentMapper commentMapper) {
		this.commentApi = commentApi;
		this.commentService = commentService;
		this.commentMapper=commentMapper;
	}

	@Override
	public void run() {
		try {
			logger.trace("start");
			List<CommentResponse> comments = commentApi.getComments().getBody();
			logger.debug("comments {}", comments.size());
			logger.debug("comments data {}", comments);
			comments.forEach(commentResponse -> {
				Comment comment = this.commentMapper.map(commentResponse);
				commentService.save(comment);
			});
		} catch (Exception ex) {
			logger.error("error occurred while loading comments {}", ex.getMessage(), ex);
		}

	}

}
