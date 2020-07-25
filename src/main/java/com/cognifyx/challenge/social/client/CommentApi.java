package com.cognifyx.challenge.social.client;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cognifyx.challenge.social.client.model.CommentResponse;

public interface CommentApi {

	@RequestMapping(value = "/comments", produces = "*/*", method = RequestMethod.GET)
	public ResponseEntity<List<CommentResponse>> getComments();
}
