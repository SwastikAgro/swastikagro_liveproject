package com.jsp.Springboot_liveproject1.entity;


import java.util.List;



import com.jsp.Springboot_liveproject1.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message="username cannot be null")
	@NotBlank(message="username cannot be blank")
	private String firstName;
	@NotNull(message="username cannot be null")
	@NotBlank(message="username cannot be blank")
	private String lastName;
	@Column(unique=true)
	private String email;
	private long phone;
	private String pwd;
	private String gender;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserType type;
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	@OneToOne(cascade=CascadeType.ALL)
	private Image image;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Post>post;
	
	

}
