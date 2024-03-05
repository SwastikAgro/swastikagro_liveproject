package com.jsp.Springboot_liveproject1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String houseNum;
	String streat;
	String landMark;
	String mandal;
	String district;
	String state;
	int pinCode;
}
