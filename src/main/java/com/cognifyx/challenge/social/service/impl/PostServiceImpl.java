package com.cognifyx.challenge.social.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Post;
import com.cognifyx.challenge.social.dto.PostDTO;
import com.cognifyx.challenge.social.mapper.PostMapper;
import com.cognifyx.challenge.social.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	public Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private PostMapper postMapper;

	@Override
	public PostDTO save(Post post) {
		logger.trace("save {}", post);
		return postMapper.map(mongoTemplate.save(post));
	}

	@Override
	public List<PostDTO> getPostsByUserId(Long userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		return postMapper.map(mongoTemplate.find(query, Post.class));
	}

	@Override
	public AggregateCountResult getUserInfoWithMaximumPosts() {
		GroupOperation groupOperation = group("userId").count().as("count");
		SortOperation sortOperation = sort(Direction.DESC, "count");
		LimitOperation getFirst = limit(1);
		Aggregation aggregation = newAggregation(groupOperation, sortOperation, getFirst);
		AggregationResults<AggregateCountResult> result = mongoTemplate.aggregate(aggregation, "post",
				AggregateCountResult.class);
		AggregateCountResult value = result.getUniqueMappedResult();
		return value;
	}

	@Override
	public PostDTO getPostById(Long postId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(postId));
		return postMapper.map(mongoTemplate.findOne(query, Post.class));
	}

}
