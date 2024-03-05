package com.jsp.Springboot_liveproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.entity.User;


public interface UserRepo extends JpaRepository<User,Integer>{

	@Query("select a from User a where a.email=?1")
	User fetchByEmail(String email);
	@Query("select a from User a where a.image=?1")
	public User fetchByImage(Image id);
	
}
