package com.jsp.Springboot_liveproject1.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.entity.UserDetails;
import com.jsp.Springboot_liveproject1.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	private UserRepo repo;
	@Autowired
	private AddressDao aDao;

	public User saveUser(User details) {

		return repo.save(details);
	}

	public User fetchById(int id) {

		Optional<User> userDb = repo.findById(id);
		if (userDb.isPresent()) {
			return userDb.get();
		} else {
			return null;
		}
	}

//	public User fetchUser(int id) {
//		Optional<User> db = repo.findById(id);
//		if (db.isPresent())
//			return db.get();
//
//		else
//			return null;
//
//	}

	public User deleteUser(int id) {
		Optional<User> db = repo.findById(id);
		if (db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		} else {
			return null;

		}

	}

	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		User m = db.get();
		if (db.isPresent()) {
			if (user.getFirstName() == null) {
				user.setFirstName(m.getFirstName());
			}
			if (user.getLastName() == null) {
				user.setLastName(m.getLastName());
			}
			if (user.getEmail() == null) {
				user.setEmail(m.getEmail());
			}
			if (user.getPhone() == 0) {
				user.setPhone(m.getPhone());
			}
			if (user.getPwd() == null) {
				user.setPwd(m.getPwd());
			}
			if (user.getGender() == null) {
				user.setGender(m.getGender());
			}
			if (user.getAge() == 0) {
				user.setAge(m.getAge());
			}
			if (user.getAddress() == null) {
				user.setAddress(m.getAddress());
			}

			return repo.save(user);

		} else {
			return null;
		}
	}

	public List<User> fetchAll() {
		return repo.findAll();
	}

	public User fetchByEmail(String email) {
		User db = repo.fetchByEmail(email);
		if (db != null) {
			return db;
		} else {
			return null;
		}
	}

}
