package com.metrica.vibely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class PostController {
//	<<--FIELDS-->>
	private PostService postService;

//	<<--CONSTRUCTOR-->>
	@Autowired
	public PostController(PostService postService) {
		this.postService=postService;
	}
//	<<--METHODS-->>

}
