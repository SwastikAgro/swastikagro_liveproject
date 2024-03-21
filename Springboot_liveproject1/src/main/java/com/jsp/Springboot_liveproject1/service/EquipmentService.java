package com.jsp.Springboot_liveproject1.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.Springboot_liveproject1.dao.EquipmentDao;

import com.jsp.Springboot_liveproject1.dao.UserDao;
import com.jsp.Springboot_liveproject1.entity.Equipment;
import com.jsp.Springboot_liveproject1.entity.User;
import com.jsp.Springboot_liveproject1.exception.EquipmentNameNotFound;
import com.jsp.Springboot_liveproject1.exception.UserNotFound;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@Service
public class EquipmentService {

	@Autowired
	private  EquipmentDao edao;
	@Autowired
	private UserDao udao;

	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(int id,Equipment e){
		User userDb = udao.fetchById(id);
		if(userDb!=null) {
			e.setUser(userDb);
			Equipment equipmentDb = edao.saveEquipment(e);
			ResponseStructure<Equipment> r=new ResponseStructure<Equipment>();
			r.setData(equipmentDb);
			r.setMessage("Equipment saved successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new UserNotFound("user Not Found for id : "+id);
	}
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(int id){
		Equipment db = edao.fetchByIdEqp(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMessage("fetched successfully");
			m.setStatus(HttpStatus.FOUND.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			return null;
		}
	}
	public List<Equipment> fetchAllEqp(){
		List<Equipment> db = edao.fetchAllEqp();
		if(db!=null) {
			return db;
			
			
		}
		else {
			throw new UserNotFound();
		}
	}
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(String name){
		Equipment db = edao.fetchByName(name);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<>();
			m.setData(db);
			m.setMessage("fetched Equipments Based on name");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNameNotFound();
		}
	}
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(Equipment equipment){
		Equipment db = edao.fetchByIdEqp(equipment.getId());
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(edao.updateEquipment(equipment));
			m.setMessage("updated successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserNotFound();
		}
	}
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(int id){
		Equipment db = edao.deleteEquipment(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMessage("deleted successfully");
			m.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.GONE);
		}
		else {
			throw new UserNotFound();
		}
	}

}
