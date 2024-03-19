package com.jsp.Springboot_liveproject1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Springboot_liveproject1.entity.Equipment;
import com.jsp.Springboot_liveproject1.repo.EquipmentRepo;

@Repository
public class EquipmentDao {
	@Autowired
	private EquipmentRepo erepo;
	
	public Equipment saveEquipment(Equipment e) {
		return erepo.save(e);
	}
	public Equipment fetchByIdEqp(int id) {
		Optional<Equipment> db = erepo.findById(id);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public java.util.List<Equipment> fetchAllEqp(){
		return erepo.findAll();
	}
	public Equipment fetchByName(String name) {
		Equipment db = erepo.fetchByname(name);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}
	public Equipment updateEquipment(Equipment equipment){
		Optional<Equipment> db = erepo.findById(equipment.getId()); 
		if(db.isPresent()) {
			Equipment data = db.get();
			if(equipment.getName()==null) {
				equipment.setName(data.getName());
			}
			if(equipment.getQuantity()==0) {
				equipment.setQuantity(data.getQuantity());;
			}
			if(equipment.getCostperhr()==0) {
				equipment.setCostperhr(data.getCostperhr());;
			}
			return erepo.save(data);
		}
		else {
			return null;
		}
	}
	
	public Equipment deleteEquipment(int id) {
		Optional<Equipment> db = erepo.findById(id);
		if(db.isPresent()) {
			Equipment equipment=new Equipment();
			equipment.setUser(null);
			updateEquipment(equipment);
			erepo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
}
