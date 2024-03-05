package com.jsp.Springboot_liveproject1.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.repo.ImageRepo;


@Service
public class ImageService {
	@Autowired
	private ImageRepo repo;
	
	public String upload(MultipartFile file,String description) throws IOException {
		
		Image pic=new Image();
		pic.setPic(file.getBytes());
		pic.setDescription(description);
		repo.save(pic);
		return "image saved successfully";
		
	}
}
