package com.jsp.Springboot_liveproject1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Springboot_liveproject1.dao.CommentDao;
import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;
	public ResponseStructure<ResponseEntity<Comment>> saveComment(Comment comment) {
		
		return null;
	}

}
