package com.jsp.Springboot_liveproject1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Springboot_liveproject1.dao.CommentDao;
import com.jsp.Springboot_liveproject1.dao.PostDao;
import com.jsp.Springboot_liveproject1.dao.UserDao;
import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.entity.Post;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.exception.PostNotFound;
import com.jsp.Springboot_liveproject1.exception.UserNotFound;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private UserDao udao;
	public ResponseEntity<ResponseStructure<Comment>> saveComment(int pid, int uid, String msg) {
		Post postDb = pdao.fetchPostById(pid);
		if(postDb!=null) {
			User userDb = udao.fetchById(uid);
			if(userDb!=null) {
			Comment commentDb = dao.saveComment(msg, userDb);
			List<Comment> list=new ArrayList<Comment>();
			list.add(commentDb);
			list.addAll(postDb.getComment());
			postDb.setComment(list);
			System.out.println("service");
			pdao.savePost(postDb);
			System.out.println("after service");
			ResponseStructure<Comment> r=new ResponseStructure<Comment>();
			r.setData(commentDb);
			r.setMessage("comment saved successfully....!");
			r.setStatus(HttpStatus.CONTINUE.value());
			return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
			}else
				throw new UserNotFound("user not found for id : "+uid);
		}else
			throw new PostNotFound("post not found for pid:"+pid);
		
	}
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int commentId){
		Comment db = dao.deleteComment(commentId);
		if(db!=null) {
			ResponseStructure<Comment> r= new ResponseStructure<Comment>();
			r.setData(db);
			r.setMessage("deleted successfully");
			r.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.GONE);
		}
		else {
			throw new UserNotFound();
		}
	}
}
