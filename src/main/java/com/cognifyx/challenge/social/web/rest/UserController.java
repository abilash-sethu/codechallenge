package com.cognifyx.challenge.social.web.rest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognifyx.challenge.social.dto.UserDTO;
import com.cognifyx.challenge.social.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
		return ResponseEntity.ok(userService.getAll(pageable));
	}
	
	@GetMapping("/user-max-post")
	public ResponseEntity<UserDTO> getUserWithMaximumPosts() {
		return ResponseEntity.ok(userService.getUserWithMaximumPosts());
	}
	
	@GetMapping("/user-max-comments")
	public ResponseEntity<UserDTO> getUserAndPostInfoWithMaximumComments() {
		return ResponseEntity.ok(userService.getUserAndPostInfoWithMaximumComments());
	}
	
}
