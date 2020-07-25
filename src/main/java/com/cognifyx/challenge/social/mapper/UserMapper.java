package com.cognifyx.challenge.social.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cognifyx.challenge.social.client.model.UserResponse;
import com.cognifyx.challenge.social.domain.User;
import com.cognifyx.challenge.social.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User map(UserResponse userResponse);
	UserDTO map(User user);
	List<UserDTO> map(List<User> user);

}
