package com.cognifyx.challenge.social.client;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognifyx.challenge.social.client.model.UserResponse;

public interface UserApi {

	@RequestMapping(value = "/users", produces = "*/*", method = RequestMethod.GET)
	public ResponseEntity<List<UserResponse>> getUsers();
}
