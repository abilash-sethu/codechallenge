package com.cognifyx.challenge.social.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	private String name;
	private String catchPhrase;
	private String bs;
}
