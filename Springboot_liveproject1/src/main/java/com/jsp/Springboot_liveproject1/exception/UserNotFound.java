package com.jsp.Springboot_liveproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFound extends RuntimeException{
	private String msg="not found";
}
