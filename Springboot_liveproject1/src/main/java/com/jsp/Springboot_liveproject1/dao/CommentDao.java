package com.jsp.Springboot_liveproject1.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Springboot_liveproject1.entity.Comment;
import com.jsp.Springboot_liveproject1.entity.Post;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.repo.CommentRepo;
import com.jsp.Springboot_liveproject1.repo.PostRepo;
@Repository
public class CommentDao {
	@Autowired
	private CommentRepo crepo;
	@Autowired
	private Comment c;
	@Autowired
	private PostDao postDao;
	@Autowired
	private PostRepo postRepo;
	public Comment saveComment(String msg,User user) {
		c.setMsg(msg);
		c.setUser(user);
		System.out.println("dao");
		return crepo.save(c);
	}
	public Comment deleteComment(int cId) {
	    List<Post> posts = postRepo.findAll();
	    for (Post post : posts) {
	        List<Comment> comments = post.getComment();
	        Iterator<Comment> iterator = comments.iterator();
	        while (iterator.hasNext()) {
	            Comment comment = iterator.next();
	            if (comment.getId() == cId) {
	               iterator.remove();
	                postDao.updatePost(post);
	                comment.setUser(null);
	                crepo.deleteById(cId);
	                return comment;
	            }
	        }
	    }
	    return null;
	}	

}
