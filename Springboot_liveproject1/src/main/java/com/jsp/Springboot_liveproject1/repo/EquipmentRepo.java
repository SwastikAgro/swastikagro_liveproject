package com.jsp.Springboot_liveproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Springboot_liveproject1.entity.Equipment;


public interface EquipmentRepo  extends JpaRepository<Equipment, Integer>{
	@Query("select a from Equipment a where a.name=:name")
	Equipment fetchByname(String name);
	
}
