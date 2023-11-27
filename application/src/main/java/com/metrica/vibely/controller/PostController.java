package com.metrica.vibely.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.service.PostService;
import com.metrica.vibely.model.request.CreatePostRequest;

import jakarta.validation.Valid;

/**
 * <h1>Post Controller</h1>
 * 
 * @since 2023-11-27
 * @author Adri
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/post")
public class PostController {
//	<<--FIELDS-->>
	private PostService postService;

//	<<--CONSTRUCTOR-->>
	@Autowired
	public PostController(final PostService postService) {
		this.postService=postService;
	}
	
//	<<--METHODS-->>
	@GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.postService.getById(id));
    }
	
	@PostMapping
    public ResponseEntity<PostDTO> create(
            @RequestBody
            @Valid
            CreatePostRequest createPostRequest,
            BindingResult bindingResult) {	
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        PostDTO postDTO = CreatePostRequest.toPostDTO(createPostRequest);
        PostDTO postDTOResponse = this.postService.create(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTOResponse);
    }
	
	@PutMapping
    public ResponseEntity<PostDTO> update(
    		@RequestBody
    		@Valid 
    		CreatePostRequest createPostRequest, 
    		BindingResult bindingResult) {
		PostDTO postDTO = CreatePostRequest.toPostDTO(createPostRequest);
		PostDTO postDTOResponse = this.postService.update(postDTO);
        return ResponseEntity.ok(postDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        this.postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}










