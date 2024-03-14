package com.jsp.Springboot_liveproject1.entity;


import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Post{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	private Image image;
	private int likes;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Comment> comment;
	private LocalDate date;
	private LocalTime time;
	private String caption;
	private String location;
	
}
