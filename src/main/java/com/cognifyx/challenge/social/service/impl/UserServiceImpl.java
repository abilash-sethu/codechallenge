package com.cognifyx.challenge.social.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.User;
import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.dto.UserDTO;
import com.cognifyx.challenge.social.mapper.UserMapper;
import com.cognifyx.challenge.social.service.CommentService;
import com.cognifyx.challenge.social.service.PostService;
import com.cognifyx.challenge.social.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	public Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO save(User user) {
		logger.debug("save {}", user);
		return userMapper.map(mongoTemplate.save(user));
	}

	@Override
	public List<UserDTO> getAll(Pageable pageable) {
		logger.debug("retrieve all users");
		Query query = new Query().with(pageable);
		return userMapper.map(mongoTemplate.find(query, User.class));
	}

	@Override
	public UserDTO getUserWithMaximumPosts() {
		logger.debug("retrieve user with maximum posts");
		AggregateCountResult postCountResult = postService.getUserInfoWithMaximumPosts();
		logger.trace("postCountResult {}", postCountResult);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(postCountResult.getId()));
		return userMapper.map(mongoTemplate.findOne(query, User.class));
	}

	@Override
	public UserDTO getUserAndPostInfoWithMaximumComments() {
		logger.debug("retrieve user and post info with maximum comments");
		AggregateCountResult commentCountResult = commentService.getPostWithMaximumComments();
		logger.trace("commentCountResult {}", commentCountResult);
		PostDTO postWithMaximumComments = postService.getPostById(commentCountResult.getId());
		logger.trace("postWithMaximumComments {}", postWithMaximumComments);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(postWithMaximumComments.getUserId()));
		UserDTO result = userMapper.map(mongoTemplate.findOne(query, User.class));
		result.setPost(postWithMaximumComments);
		return result;
	}

}
