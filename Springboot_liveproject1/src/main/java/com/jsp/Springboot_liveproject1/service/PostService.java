package com.jsp.Springboot_liveproject1.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Springboot_liveproject1.dao.ImageDao;
import com.jsp.Springboot_liveproject1.dao.PostDao;
import com.jsp.Springboot_liveproject1.dao.UserDao;
import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.entity.Post;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.exception.PostNotFound;
import com.jsp.Springboot_liveproject1.exception.UserNotFound;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;
@Service
public class PostService {
	
	@Autowired
	private UserDao dao;
	@Autowired
	private Image img;
	@Autowired
	private ImageDao idao;
	@Autowired
	private Post p;
	@Autowired
	private PostDao pdao;

	public ResponseEntity<ResponseStructure<Post>> savePost(int uId, MultipartFile file, String caption)
			throws IOException {
		User db = dao.fetchById(uId);
		if (db != null) {
			Image img = new Image();
			img.setPic(file.getBytes());
			img.setDescription(file.getOriginalFilename());
			idao.uploadProfile(img);

			Post p1 = new Post();
			p1.setImage(img);
			p1.setCaption(caption);
			p1.setDate(LocalDate.now());
			p1.setTime(LocalTime.now());

			Post pdb = pdao.savePost(p1);
			List<Post> postSer = new ArrayList<Post>();
			postSer.add(pdb);
			postSer.addAll(db.getPost());
			db.setPost(postSer);

			User updatedUserDbObject = dao.updateUser(db);

			ResponseStructure<Post> postResponseStructure = new ResponseStructure<Post>();

			postResponseStructure.setData(pdb);
			postResponseStructure.setMessage("User Given post is saved Successfully");
			postResponseStructure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<>(postResponseStructure, HttpStatus.OK);

		}
		else {
			throw new UserNotFound("User is not found with the given Id"+uId);
		}

	}

	public ResponseEntity<ResponseStructure<Post>> fetchById(int id) {
		Post db = pdao.fetchPostById(id);
		
		if(db!=null) {
		ResponseStructure<Post>	responseStructure=new ResponseStructure<Post>();
		responseStructure.setData(db);
		responseStructure.setMessage("post notfound Successfully");
		responseStructure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<>(responseStructure,HttpStatus.FOUND);
		}
		else {
			throw new PostNotFound("user not found for search"+id);
		}
	}

	public ResponseEntity<ResponseStructure<Post>> deletePost(int id) {
		Post post=pdao.fetchPostById(id);
		if(post!=null) {
			List<User> users = dao.fetchAll() ;
			for(User u : users) {
				List<Post> posts = u.getPost();
				if(posts.contains(post)) {
					posts.remove(post);
					dao.updateUser(u);
					pdao.deletePostByID(id);
					break;
				}
			}
			ResponseStructure<Post> s = new ResponseStructure<Post>();
			s.setData(post);
			s.setMessage("post deleted");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>(s,HttpStatus.OK);
		}
		else {
			throw new PostNotFound("user not found for your search:"+id);
		}
	}
}
