package com.jsp.Springboot_liveproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.service.CommentService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@RestController
public class CommentController {
	@Autowired
	private CommentService service;
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int pid,@RequestParam int uid,@RequestParam String  msg){
		return service.saveComment(pid,uid,msg);
	}

}
