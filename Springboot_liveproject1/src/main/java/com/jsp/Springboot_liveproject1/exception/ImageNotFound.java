package com.jsp.Springboot_liveproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageNotFound extends RuntimeException {
	String msg="not found";
}
