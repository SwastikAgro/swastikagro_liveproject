package com.jsp.Springboot_liveproject1.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.jsp.Springboot_liveproject1.dao.UserDao;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.entity.UserDetails;
import com.jsp.Springboot_liveproject1.exception.EmailWrongException;
import com.jsp.Springboot_liveproject1.exception.PasswordWrongException;
import com.jsp.Springboot_liveproject1.exception.UserNotFound;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;




@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private JavaMailSender javaMailSender;
	public String sendSimpleMail(UserDetails details) {
//		dao.saveUser(details);
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("swastikagro86@gmail.com");
		mailMessage.setTo(details.getRecipient());
		mailMessage.setText(details.getMsgBody());
		mailMessage.setSubject(details.getSubject());
		javaMailSender.send(mailMessage);
		return "Mail Sent Successfully";
	}
	public ResponseEntity<ResponseStructure<User>> register(User u){
		ResponseStructure<User> r=new ResponseStructure<User>();
		r.setData(dao.saveUser(u));
		r.setMessage("RegisterSuccessfully.........!");
		r.setStatus(HttpStatus.CONTINUE.value());
		String email=u.getEmail();
		String subject="Welcome to live project agro....!";
		if(email!=null) {
			sendSimpleMail(email,u.getFirstName()+u.getLastName()+"Register Successfully.....!",subject);
		}
		return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.CONTINUE);
	}
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> m=new ResponseStructure<User>();
		m.setData(dao.saveUser(user));
		m.setMessage("user data saved succuessfully");
		m.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<User>> fetchById(int id){
		User db=dao.fetchById(id);
		if(db!=null) {

			ResponseStructure<User> m=new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("user found successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("user not found....") ;
		}
	}
	public List<User> fetchAll(){
		return dao.fetchAll();
	}
	public ResponseEntity<ResponseStructure<User>> deleteById(int id){
		User db = dao.deleteUser(id);
		if(db!=null) {
			ResponseStructure<User> m=new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("user deleted successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("no user found for this id:"+id) ;
		}
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		User db = dao.fetchById(user.getId());
		if(db!=null) {
			ResponseStructure<User> m=new ResponseStructure<User>();
			m.setData(dao.updateUser(user));
			m.setMessage("user updated successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("no user found:"+user.getId()) ;
		}
	}
	public ResponseEntity<ResponseStructure<User>> loginUser(User user) {
		User db = dao.fetchByEmail(user.getEmail());
		if (db != null) {
			if (db.getPwd().equals(user.getPwd())) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setData(db);
				structure.setMessage("user login successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
			}
			throw new PasswordWrongException();
		}
		throw new EmailWrongException();
	}
	public String sendSimpleMail(String email,String msg,String subject) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom("k.anupumajba@gmail.com");
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setTo(email);
		simpleMailMessage.setText(msg);
		javaMailSender.send(simpleMailMessage);
		return"register successfully.....!";
	}
	public ResponseEntity<ResponseStructure<Integer>> otp(String email){
		User db=dao.fetchByEmail(email);
		if(db!=null) {
			Random random=new Random();
			int value=random.nextInt(900000);
			ResponseStructure<Integer> r=new ResponseStructure<Integer>();
			r.setStatus(HttpStatus.CONTINUE.value());
			r.setMessage("OTP for Password");
			r.setData(value);
			String subject="Welcome to live project agro otp Generation....!";
			sendSimpleMail(email,"OTP for Password Verification:"+value,subject);
			return new ResponseEntity<ResponseStructure<Integer>>(r,HttpStatus.CONTINUE);
		}else {
			throw new UserNotFound("User not found for email: "+email);
		}
	}

}
