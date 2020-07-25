package com.cognifyx.challenge.social.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cognifyx.challenge.social.domain.User;
import com.cognifyx.challenge.social.dto.UserDTO;

public interface UserService {

	UserDTO save(User user);
	
	List<UserDTO> getAll(Pageable pageable);
	
	UserDTO getUserWithMaximumPosts();
	
	UserDTO getUserAndPostInfoWithMaximumComments();
}
