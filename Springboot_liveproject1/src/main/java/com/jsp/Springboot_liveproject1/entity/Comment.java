package com.jsp.Springboot_liveproject1.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Comment{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String msg;
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
}
