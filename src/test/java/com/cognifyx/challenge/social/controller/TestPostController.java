package com.cognifyx.challenge.social.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.service.PostService;
import com.cognifyx.challenge.social.web.rest.PostController;
import static org.hamcrest.Matchers.hasItem;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

@WebMvcTest(controllers = { PostController.class })
public class TestPostController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostService postService;

	@BeforeEach
	public void init() {
		PostDTO postDTO1 = PostDTO.builder().id(1l).userId(34l).build();
		PostDTO postDTO2 = PostDTO.builder().id(2l).userId(34l).build();
		when(postService.getPostsByUserId(34l)).thenReturn(Arrays.asList(postDTO1, postDTO2));
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/post/34").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].id").value(hasItem(1)))
				.andExpect(jsonPath("$.[*].userId").value(hasItem(34)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(2)));
	}

}
