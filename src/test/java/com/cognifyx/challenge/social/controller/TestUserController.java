package com.cognifyx.challenge.social.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.dto.UserDTO;
import com.cognifyx.challenge.social.service.UserService;
import com.cognifyx.challenge.social.web.rest.UserController;
import static org.hamcrest.Matchers.hasItem;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

@WebMvcTest(controllers = { UserController.class })
public class TestUserController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@BeforeEach
	public void init() {
		UserDTO userDTO1 = UserDTO.builder().id(1l).build();
		UserDTO userDTO2 = UserDTO.builder().id(2l).build();
		Pageable pageable = PageRequest.of(0, 2);
		when(userService.getAll(pageable)).thenReturn(Arrays.asList(userDTO1, userDTO2));
		
		UserDTO userWithMaxComments = UserDTO.builder().id(10l).post(PostDTO.builder().id(2l).build()).build();
		when(userService.getUserAndPostInfoWithMaximumComments()).thenReturn(userWithMaxComments);
		
		UserDTO userWithMaxPosts = UserDTO.builder().id(20l).build();
		when(userService.getUserWithMaximumPosts()).thenReturn(userWithMaxPosts);

	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user?page=0&size=2").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].id").value(hasItem(1)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(2)));
	}
	
	@Test
	public void testGetUserWithMaximumPosts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user-max-post").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(20));
		}
	
	@Test
	public void testGetUserAndPostInfoWithMaximumComments() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user-max-comments").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(10))
				.andExpect(jsonPath("$.post.id").value(2));
	}
}
