package com.jsp.Springboot_liveproject1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentNameNotFound  extends RuntimeException{
	private String msg="Equipment name not found please enter the valid Equipment name"; 
}
