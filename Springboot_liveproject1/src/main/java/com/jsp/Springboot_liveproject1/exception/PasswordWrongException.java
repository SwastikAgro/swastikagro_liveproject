package com.jsp.Springboot_liveproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordWrongException extends RuntimeException {
	private String msg="password is wrong";
}
