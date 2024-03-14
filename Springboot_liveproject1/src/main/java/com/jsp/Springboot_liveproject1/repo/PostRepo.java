package com.jsp.Springboot_liveproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Springboot_liveproject1.entity.Post;



public interface PostRepo extends JpaRepository<Post, Integer>{

}
