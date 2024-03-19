package com.jsp.Springboot_liveproject1.entity;



import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String mode;
	private LocalTime paymentTime;
	private double amount;
	@ManyToOne
	User user;
	
	
}
