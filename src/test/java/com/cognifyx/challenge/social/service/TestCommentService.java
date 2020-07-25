package com.cognifyx.challenge.social.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Comment;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = { "com.cognifyx.challenge.social" })
@ActiveProfiles("test")
public class TestCommentService {

	@Autowired
	private CommentService commentService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void testCreateComment() {
		Comment comment = Comment.builder().id(1l).postId(9l).body("comment_body_1").name("name_1")
				.email("test_1@gmail.com").build();
		commentService.save(comment);

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(comment.getId()));
		Comment result = mongoTemplate.findOne(query, Comment.class);
		assertEquals(comment.getId(), result.getId());
		assertEquals(comment.getEmail(), result.getEmail());
		assertEquals(comment.getBody(), result.getBody());
		assertEquals(comment.getPostId(), result.getPostId());
	}

	@Test
	public void testGetPostWithMaximumComments() {
		Comment comment1 = Comment.builder().id(1l).postId(35l).body("comment_body_1").name("name_1")
				.email("test_1@gmail.com").build();
		commentService.save(comment1);
		Comment comment2 = Comment.builder().id(2l).postId(35l).body("comment_body_2").name("name_2")
				.email("test_2@gmail.com").build();
		commentService.save(comment2);
		Comment comment3 = Comment.builder().id(3l).postId(67l).body("comment_body_3").name("name_3")
				.email("test_3@gmail.com").build();
		commentService.save(comment3);
		Comment comment4 = Comment.builder().id(4l).postId(61l).body("comment_body_4").name("name_4")
				.email("test_4@gmail.com").build();
		commentService.save(comment4);
		Comment comment5 = Comment.builder().id(5l).postId(90l).body("comment_body_5").name("name_5")
				.email("test_5@gmail.com").build();
		commentService.save(comment5);
		Comment comment6 = Comment.builder().id(6l).postId(90l).body("comment_body_6").name("name_6")
				.email("test_6@gmail.com").build();
		commentService.save(comment6);
		Comment comment7 = Comment.builder().id(7l).postId(90l).body("comment_body_7").name("name_7")
				.email("test_7@gmail.com").build();
		commentService.save(comment7);

		AggregateCountResult countResult = commentService.getPostWithMaximumComments();
		assertEquals(90, countResult.getId());
		assertEquals(3, countResult.getCount());
	}

}
