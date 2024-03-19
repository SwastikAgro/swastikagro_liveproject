package com.jsp.Springboot_liveproject1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.repo.CommentRepo;
@Repository
public class CommentDao {
	@Autowired
	private CommentRepo crepo;
	@Autowired
	private Comment c;
	public Comment saveComment(String msg,User user) {
		c.setMsg(msg);
		c.setUser(user);
		System.out.println("dao");
		return crepo.save(c);
	}

	

}
