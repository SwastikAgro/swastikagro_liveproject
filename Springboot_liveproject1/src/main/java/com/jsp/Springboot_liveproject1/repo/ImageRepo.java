package com.jsp.Springboot_liveproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Springboot_liveproject1.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer>{

	
}
