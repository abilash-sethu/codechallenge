package com.cognifyx.challenge.social.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import com.cognifyx.challenge.social.client.model.Address;
import com.cognifyx.challenge.social.client.model.Location;
import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.User;
import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.dto.UserDTO;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = { "com.cognifyx.challenge.social" })
@ActiveProfiles("test")
public class TestUserService {

	@MockBean
	private CommentService commentService;

	@MockBean
	private PostService postService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Test
	public void testCreateUser() {
		User user = User.builder().id(1l).name("user_1").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user);

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(user.getId()));
		User result = mongoTemplate.findOne(query, User.class);
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getPhone(), result.getPhone());
		assertEquals(user.getAddress().getCity(), result.getAddress().getCity());
		assertEquals(user.getAddress().getStreet(), result.getAddress().getStreet());
		assertEquals(user.getAddress().getSuite(), result.getAddress().getSuite());
		assertEquals(user.getAddress().getZipcode(), result.getAddress().getZipcode());
		assertEquals(user.getAddress().getGeo().getLat(), result.getAddress().getGeo().getLat());
		assertEquals(user.getAddress().getGeo().getLng(), result.getAddress().getGeo().getLng());

	}

	@Test
	public void testGetAllUsersWithPagination() {
		User user1 = User.builder().id(1l).name("user_1").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user1);

		User user2 = User.builder().id(2l).name("user_2").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user2);

		User user3 = User.builder().id(3l).name("user_3").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user3);

		User user4 = User.builder().id(4l).name("user_4").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user4);

		User user5 = User.builder().id(5l).name("user_5").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user5);

		User user6 = User.builder().id(6l).name("user_6").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user6);

		List<UserDTO> results = userService.getAll(PageRequest.of(1, 3));

		assertFalse(CollectionUtils.isEmpty(results));
		assertEquals(3, results.size());
		assertEquals(4, results.get(0).getId());
		assertEquals(5, results.get(1).getId());
		assertEquals(6, results.get(2).getId());
	}

	@Test
	public void testGetUserWithMaximumPosts() {
		User user = User.builder().id(12l).name("user_1").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user);
		Mockito.doReturn(AggregateCountResult.builder().id(12l).count(10l).build()).when(postService)
				.getUserInfoWithMaximumPosts();

		UserDTO result = userService.getUserWithMaximumPosts();
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getPhone(), result.getPhone());
		assertEquals(user.getAddress().getCity(), result.getAddress().getCity());
		assertEquals(user.getAddress().getStreet(), result.getAddress().getStreet());
		assertEquals(user.getAddress().getSuite(), result.getAddress().getSuite());
		assertEquals(user.getAddress().getZipcode(), result.getAddress().getZipcode());
		assertEquals(user.getAddress().getGeo().getLat(), result.getAddress().getGeo().getLat());
		assertEquals(user.getAddress().getGeo().getLng(), result.getAddress().getGeo().getLng());

	}

	@Test
	public void testGetUserAndPostInfoWithMaximumComments() {
		User user = User.builder().id(12l).name("user_1").email("user_1@gmail.com").phone("9890000")
				.address(Address.builder().city("city_1").street("street_1").suite("suite_1").zipcode("678688")
						.geo(Location.builder().lat(12.00d).lng(50.00d).build()).build())
				.build();
		userService.save(user);

		Mockito.doReturn(AggregateCountResult.builder().id(10l).count(5l).build()).when(commentService)
				.getPostWithMaximumComments();
		PostDTO postDTO = PostDTO.builder().id(10l).body("test_body").title("test_title").userId(12l).build();
		Mockito.doReturn(postDTO).when(postService).getPostById(10l);

		UserDTO result = userService.getUserAndPostInfoWithMaximumComments();
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getPhone(), result.getPhone());
		assertEquals(user.getAddress().getCity(), result.getAddress().getCity());
		assertEquals(user.getAddress().getStreet(), result.getAddress().getStreet());
		assertEquals(user.getAddress().getSuite(), result.getAddress().getSuite());
		assertEquals(user.getAddress().getZipcode(), result.getAddress().getZipcode());
		assertEquals(user.getAddress().getGeo().getLat(), result.getAddress().getGeo().getLat());
		assertEquals(user.getAddress().getGeo().getLng(), result.getAddress().getGeo().getLng());
		assertNotNull(result.getPost());
		assertEquals(postDTO.getId(), result.getPost().getId());

	}

}
