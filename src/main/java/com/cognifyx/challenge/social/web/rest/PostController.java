package com.cognifyx.challenge.social.web.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.service.PostService;
@RestController
@RequestMapping("/api")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post/{userId}")
	public ResponseEntity<List<PostDTO>> getAllPostsByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(postService.getPostsByUserId(userId));
	}
	
}
