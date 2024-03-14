package com.jsp.Springboot_liveproject1.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.jsp.Springboot_liveproject1.dao.ImageDao;
import com.jsp.Springboot_liveproject1.dao.UserDao;
import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.exception.ImageNotFound;
import com.jsp.Springboot_liveproject1.exception.UserNotFound;
import com.jsp.Springboot_liveproject1.repo.ImageRepo;
import com.jsp.Springboot_liveproject1.repo.UserRepo;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImageDao idao;
	@Autowired
	private UserDao udao;

//	public ResponseEntity<ResponseStructure<Image>> uploadProfile(int id, String description, MultipartFile file)
//			throws IOException {
//		User userDb = udao.fetchUser(id);
//		if (userDb != null) {
//			Image iDb = idao.uploadProfile(id,description, file);
//
//			userDb.setImage(iDb);
//			udao.updateUser(userDb);
//			ResponseStructure<Image> r = new ResponseStructure<Image>();
//			r.setMessage("save img Successfully.....");
//			r.setData(iDb);
//			r.setStatus(HttpStatus.ACCEPTED.value());
//			return new ResponseEntity<ResponseStructure<Image>>(r, HttpStatus.ACCEPTED);
//		} else {
//			throw new UserNotFound("user not found for id: " + id);
//		}
//}
	@Autowired
	private Image image;
	@Autowired
	private ImageRepo imageRepository;
	@Autowired
	private UserRepo userRepository;

	public ResponseEntity<ResponseStructure<Image>> uploadProfile(int uId, String name, MultipartFile file)throws IOException {

		User userDb = udao.fetchById(uId);
		if (userDb != null) {
			if(userDb.getImage()!=null) {
				
				ResponseStructure<Image> imageResponseStructor=new ResponseStructure<Image>();
				
				imageResponseStructor.setMessage("Profile is already exist");
				imageResponseStructor.setData(userDb.getImage());
				imageResponseStructor.setStatus(HttpStatus.ACCEPTED.OK.value());
				
				return new ResponseEntity<>(imageResponseStructor,HttpStatus.ACCEPTED.OK);
			}else {
				Image i1=new Image();
				i1.setDescription(name);
				i1.setPic(file.getBytes());
				idao.uploadProfile(i1);		
				userDb.setImage(i1);
				udao.updateUser(userDb);
				ResponseStructure<Image>imageResponseStructor=new ResponseStructure<Image>();		
				imageResponseStructor.setMessage("Image Uploaded Successfully");
				imageResponseStructor.setData(i1);
				imageResponseStructor.setStatus(HttpStatus.ACCEPTED.OK.value());
				
				return new ResponseEntity<>(imageResponseStructor,HttpStatus.ACCEPTED);
			}
		}else {
			throw new UserNotFound("User Not Found with the given id to upload profile  :"+uId);
		}
	}

	
	
	

	public ResponseEntity<ResponseStructure<Image>> fetchById(int id) {
		Image imageDb = idao.fetchById(id);
		if (imageDb != null) {
			ResponseStructure<Image> r = new ResponseStructure<Image>();
			r.setMessage("fetch image Successfully.....");
			r.setData(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r, HttpStatus.FOUND);
		} else {
			throw new ImageNotFound("Image not found for id : " + id);
		}
	}

	public ResponseEntity<ResponseStructure<Image>> deleteById(int id) {
		Image imageDb = idao.deleteById(id);
		if (imageDb != null) {
			ResponseStructure<Image> r = new ResponseStructure<Image>();
			r.setMessage("delete image Successfully.....");
			r.setData(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r, HttpStatus.FOUND);
		} else {
			throw new ImageNotFound("Image not found for id : " + id);
		}
	}

	public ResponseEntity<ResponseStructure<Image>> updateImage(int id, String description, MultipartFile file)
			throws IOException {
		Image imageDb = idao.updateImage(id, description, file);
		if (imageDb != null) {
			ResponseStructure<Image> r = new ResponseStructure<Image>();
			r.setData(imageDb);
			r.setMessage("update image successfully......!");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Image>>(r, HttpStatus.ACCEPTED);
		} else
			throw new ImageNotFound("Image not found for id : " + id);

	}

	public ResponseEntity<byte[]> getImage(@RequestParam int id) {
		byte[] imageBytes = idao.fetchById(id).getPic();

		// Set appropriate content type (e.g., image/jpeg)
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}

}
