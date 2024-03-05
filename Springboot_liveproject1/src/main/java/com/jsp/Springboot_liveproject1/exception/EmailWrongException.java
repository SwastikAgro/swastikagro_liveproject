package com.jsp.Springboot_liveproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailWrongException extends RuntimeException{
	private String msg="email is wrong";
}
