package com.jsp.Springboot_liveproject1.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.Springboot_liveproject1.entity.Post;
import com.jsp.Springboot_liveproject1.repo.PostRepo;

@Repository
public class PostDao{
	@Autowired
	private PostRepo postRepo;
	
	public Post savePost(Post post) {

		return postRepo.save(post);
	}

	public Post fetchPostById(int id) {
		Optional<Post> db = postRepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
		
	}
	public Post deletePostByID(int id) {
		Optional<Post> db = postRepo.findById(id);
		
		if(db.isPresent()) {
			postRepo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
		
	}	

}
