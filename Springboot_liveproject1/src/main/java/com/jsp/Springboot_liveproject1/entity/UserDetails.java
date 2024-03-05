package com.jsp.Springboot_liveproject1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetails {
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;

}
