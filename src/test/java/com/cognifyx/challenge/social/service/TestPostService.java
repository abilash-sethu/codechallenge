package com.cognifyx.challenge.social.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Post;
import com.cognifyx.challenge.social.dto.PostDTO;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = { "com.cognifyx.challenge.social" })
@ActiveProfiles("test")
public class TestPostService {

	@Autowired
	private PostService postService;
	
	
	
	@Test
	public void testCreatePost() {
		Post post = Post.builder().id(1l).title("title_1").body("body_1").userId(15l).build();
		postService.save(post);

		PostDTO result = postService.getPostById(1l);
		assertEquals(post.getId(), result.getId());
		assertEquals(post.getTitle(), result.getTitle());
		assertEquals(post.getBody(), result.getBody());
		assertEquals(post.getUserId(), result.getUserId());
	}
	
	@Test
	public void testGetPostById() {
		Post post = Post.builder().id(45l).title("title_1").body("body_1").userId(20l).build();
		postService.save(post);
	
		PostDTO result = postService.getPostById(45l);
		assertEquals(post.getId(), result.getId());
		assertEquals(post.getTitle(), result.getTitle());
		assertEquals(post.getBody(), result.getBody());
		assertEquals(post.getUserId(), result.getUserId());
	}
	
	@Test
	public void testGetPostsByUserId() {
		Post post1 = Post.builder().id(1l).title("title_1").body("body_1").userId(6l).build();
		postService.save(post1);
		Post post2 = Post.builder().id(2l).title("title_2").body("body_2").userId(6l).build();
		postService.save(post2);
		Post post3 = Post.builder().id(3l).title("title_3").body("body_3").userId(7l).build();
		postService.save(post3);
		Post post4 = Post.builder().id(4l).title("title_4").body("body_4").userId(8l).build();
		postService.save(post4);
		Post post5 = Post.builder().id(5l).title("title_5").body("body_5").userId(8l).build();
		postService.save(post5);
		Post post6 = Post.builder().id(6l).title("title_6").body("body_6").userId(9l).build();
		postService.save(post6);
		
		List<PostDTO> posts = postService.getPostsByUserId(8l);
		assertFalse(CollectionUtils.isEmpty(posts));
		assertEquals(2, posts.size());
		assertEquals(post4.getId(), posts.get(0).getId());
		assertEquals(post4.getTitle(), posts.get(0).getTitle());
		assertEquals(post4.getBody(), posts.get(0).getBody());
		assertEquals(post4.getUserId(), posts.get(0).getUserId());
		
		assertEquals(post5.getId(), posts.get(1).getId());
		assertEquals(post5.getTitle(), posts.get(1).getTitle());
		assertEquals(post5.getBody(), posts.get(1).getBody());
		assertEquals(post5.getUserId(), posts.get(1).getUserId());
	}

	@Test
	public void testGetUserWithMaximumPosts() {
		Post post1 = Post.builder().id(1l).title("title_1").body("body_1").userId(1l).build();
		postService.save(post1);
		Post post2 = Post.builder().id(2l).title("title_2").body("body_2").userId(1l).build();
		postService.save(post2);
		Post post3 = Post.builder().id(3l).title("title_3").body("body_3").userId(1l).build();
		postService.save(post3);
		Post post4 = Post.builder().id(4l).title("title_4").body("body_4").userId(3l).build();
		postService.save(post4);
		Post post5 = Post.builder().id(5l).title("title_5").body("body_5").userId(3l).build();
		postService.save(post5);
		Post post6 = Post.builder().id(6l).title("title_6").body("body_6").userId(3l).build();
		postService.save(post6);
		Post post7 = Post.builder().id(7l).title("title_7").body("body_7").userId(3l).build();
		postService.save(post7);
		AggregateCountResult countResult = postService.getUserInfoWithMaximumPosts();
		assertEquals(3, countResult.getId());
		assertEquals(4, countResult.getCount());
	}

}
