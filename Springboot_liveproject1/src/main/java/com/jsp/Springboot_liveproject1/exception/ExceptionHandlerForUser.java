package com.jsp.Springboot_liveproject1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForUser {
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String>> UserNotFound(UserNotFound ex){
				ResponseStructure<String> m=new ResponseStructure<String>();
				m.setData("not found for user id");
				m.setMessage(ex.getMsg());
				m.setStatus(HttpStatus.NOT_FOUND.value());
	return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
}
