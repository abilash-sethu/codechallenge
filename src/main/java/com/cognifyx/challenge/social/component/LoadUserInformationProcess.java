package com.cognifyx.challenge.social.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifyx.challenge.social.client.UserApi;
import com.cognifyx.challenge.social.client.model.UserResponse;
import com.cognifyx.challenge.social.domain.User;
import com.cognifyx.challenge.social.mapper.UserMapper;
import com.cognifyx.challenge.social.service.UserService;

public class LoadUserInformationProcess implements Process {

	public Logger logger = LoggerFactory.getLogger(LoadUserInformationProcess.class);

	private UserApi userApi;

	private UserService userService;

	private UserMapper userMapper;

	public LoadUserInformationProcess(UserApi userApi, UserService userService, UserMapper userMapper) {
		this.userApi = userApi;
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@Override
	public void run() {
		try {
			logger.trace("start");
			List<UserResponse> users = userApi.getUsers().getBody();
			logger.debug("users {}", users.size());
			logger.debug("users data {}", users);
			users.forEach(userResponse -> {
				User userDoc = userMapper.map(userResponse);
				userService.save(userDoc);
			});
		} catch (Exception ex) {
			logger.error("error occurred while loading users {}", ex.getMessage(), ex);
		}

	}

}
