package com.jsp.Springboot_liveproject1.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Springboot_liveproject1.entity.Image;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.repo.ImageRepo;
import com.jsp.Springboot_liveproject1.repo.UserRepo;

@Repository
public class ImageDao {
	@Autowired
	private ImageRepo irepo;
	@Autowired
	private Image i;
	@Autowired
	private UserDao userdao;
	@Autowired
	private UserRepo userRepo;

	public Image uploadProfile(Image image) {
		return irepo.save(image);
	}

	public Image fetchById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent())
			return db.get();
		else
			return null;

	}

	public Image deleteById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imageDb = db.get();
			User userdb = userRepo.fetchByImage(imageDb);
			userdb.setImage(null);
			userdao.updateUser(userdb);
			irepo.deleteById(id);
			return imageDb;
		} else
			return null;
	}

	public Image updateImage(int id, String description, MultipartFile file) throws IOException {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imageDb = db.get();
			if (description != null) {
				imageDb.setDescription(description);
			}
			if (file != null) {
				imageDb.setPic(imageDb.getPic());
			}
			return irepo.save(imageDb);

		}
		return null;
	}

}
