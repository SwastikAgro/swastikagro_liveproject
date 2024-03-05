package com.jsp.Springboot_liveproject1.util;

import lombok.Data;
@Data
public class ResponseStructure<T> {
	private int status;
	private String message;
	private T data;
}
