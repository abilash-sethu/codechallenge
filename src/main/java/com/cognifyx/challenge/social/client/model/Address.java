package com.cognifyx.challenge.social.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Location geo;

}
