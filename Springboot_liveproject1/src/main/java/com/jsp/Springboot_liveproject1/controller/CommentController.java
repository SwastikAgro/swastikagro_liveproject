package com.jsp.Springboot_liveproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.service.CommentService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@RestController
public class CommentController {
	@Autowired
	private CommentService service;
	public ResponseStructure<ResponseEntity<Comment>> saveComment(@RequestBody Comment comment){
		return service.saveComment(comment);
	}

}
