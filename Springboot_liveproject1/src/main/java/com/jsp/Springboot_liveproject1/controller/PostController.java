package com.jsp.Springboot_liveproject1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Springboot_liveproject1.entity.Post;
import com.jsp.Springboot_liveproject1.service.PostService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;


@RestController
public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/savePost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int uId,@RequestParam MultipartFile file,@RequestParam String caption) throws IOException {
		
		return postService.savePost(uId,file,caption);
	}
	@GetMapping("/fetchPost")
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(@RequestParam int id){
		return postService.fetchById(id);
	}
	@DeleteMapping("/deletePost")
	public ResponseEntity<ResponseStructure<Post>> deletePostById(@RequestParam int id){
		return postService.deletePost(id);
	}
	
}
