package com.cognifyx.challenge.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comment {
	
	@Id
	private Long id;
	private Long postId;
	private String name;
	private String email;
	private String body;
}
