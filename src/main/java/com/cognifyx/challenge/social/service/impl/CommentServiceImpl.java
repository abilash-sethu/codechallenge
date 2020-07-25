package com.cognifyx.challenge.social.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognifyx.challenge.social.domain.AggregateCountResult;
import com.cognifyx.challenge.social.domain.Comment;
import com.cognifyx.challenge.social.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

	public Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	
	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public Comment save(Comment comment) {
		logger.trace("save {}",comment);
		return mongoTemplate.save(comment);
	}
	
	public AggregateCountResult getPostWithMaximumComments() {
		GroupOperation groupOperation=group("postId").count().as("count");
		SortOperation sortOperation=sort(Direction.DESC,"count" );
		LimitOperation getFirst = limit(1);
		Aggregation aggregation=newAggregation(groupOperation,sortOperation,getFirst);
		AggregationResults<AggregateCountResult> result  = mongoTemplate.aggregate(aggregation,"comment", AggregateCountResult.class);
		AggregateCountResult value = result.getUniqueMappedResult();
		return value;
	}

}
