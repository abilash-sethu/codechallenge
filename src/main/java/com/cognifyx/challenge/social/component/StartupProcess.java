package com.cognifyx.challenge.social.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.cognifyx.challenge.social.client.CommentApi;
import com.cognifyx.challenge.social.client.PostApi;
import com.cognifyx.challenge.social.client.UserApi;
import com.cognifyx.challenge.social.mapper.CommentMapper;
import com.cognifyx.challenge.social.mapper.PostMapper;
import com.cognifyx.challenge.social.mapper.UserMapper;
import com.cognifyx.challenge.social.service.CommentService;
import com.cognifyx.challenge.social.service.PostService;
import com.cognifyx.challenge.social.service.UserService;

@Component
@Profile("!test")
public class StartupProcess {

	public Logger logger = LoggerFactory.getLogger(StartupProcess.class);

	public StartupProcess(UserApi userApi, UserService userService, PostApi postApi, PostService postService,
			CommentApi commentApi, CommentService commentService, CommentMapper commentMapper, PostMapper postMapper,
			UserMapper userMapper) {

		logger.trace(" startup process");
		LoadUserInformationProcess loadUserInformationProcess = new LoadUserInformationProcess(userApi, userService,
				userMapper);
		loadUserInformationProcess.run();

		LoadPostInformationProcess loadPostInformationProcess = new LoadPostInformationProcess(postApi, postService,
				postMapper);
		loadPostInformationProcess.run();

		LoadCommentInformationProcess loadCommentInformationProcess = new LoadCommentInformationProcess(commentApi,
				commentService, commentMapper);
		loadCommentInformationProcess.run();
	}

}
