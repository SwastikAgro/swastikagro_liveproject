package com.jsp.Springboot_liveproject1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Springboot_liveproject1.entity.Equipment;
import com.jsp.Springboot_liveproject1.service.EquipmentService;
import com.jsp.Springboot_liveproject1.util.ResponseStructure;

@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService service;
	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam int id,@RequestBody Equipment e){
		return service.saveEquipment(id,e);
	}

	@GetMapping("/fetchByIdEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(@RequestParam int id){
		return service.fetchByIdEqp(id);
	}
	@GetMapping("/fetchAllEqp")
	public List<Equipment> fetchAllEqp(){
		return service.fetchAllEqp();
	}
	@GetMapping("/fetchByNameEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(@RequestParam String name){
		return service.fetchByName(name);
	}
	@PutMapping("/updateEqp")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return service.updateEquipment(equipment);
	}
	@DeleteMapping("/deleteEqp")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam int id){
		return service.deleteEquipment(id);
	}
}
