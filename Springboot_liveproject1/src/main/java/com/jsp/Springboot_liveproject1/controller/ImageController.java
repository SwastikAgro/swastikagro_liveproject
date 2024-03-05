package com.jsp.Springboot_liveproject1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.service.ImageService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;


@RestController
public class ImageController {
	@Autowired
	private ImageService service;
	
	@PostMapping("/image")
	public String  upload(@RequestParam MultipartFile file,@RequestParam String description) throws IOException {
		return service.upload(file,description);
		
	}
	public ResponseEntity<ResponseStructure<Image>> fetchImage(@RequestParam int id){
		
	}
}
