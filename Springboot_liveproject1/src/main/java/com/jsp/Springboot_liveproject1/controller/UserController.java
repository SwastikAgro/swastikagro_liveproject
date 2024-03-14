package com.jsp.Springboot_liveproject1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.entity.UserDetails;
import com.jsp.Springboot_liveproject1.service.UserService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> register(@RequestBody User u){
		return userService.register(u);
	}
	@PostMapping("/save")//save
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	@GetMapping("/fetchById")//fetch by id
	public ResponseEntity<ResponseStructure<User>> fetchById(@RequestParam int id){
		return userService.fetchById(id);
	}
	@GetMapping("/fetchUser")//fetch all
	public List<User> fetchAll(){
		return userService.fetchAll();
	}
	@DeleteMapping("/delete")//delete
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id){
		return userService.deleteById(id);
	}
	@PutMapping("/update")//update
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestBody User user) {
		return userService.loginUser(user);
	}
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> otpSend(@RequestParam String email){
		System.out.println("hello");
		return userService.otp(email);
	}
	
}
