package com.jsp.Springboot_liveproject1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Springboot_liveproject1.entity.Address;
import com.jsp.Springboot_liveproject1.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address a) {
		Optional<Address> db = repo.findById(a.getId());
		if (db.isPresent()) {
			Address aDb = db.get();
			if (a.getHouseNum() == null) {
				a.setHouseNum(aDb.getHouseNum());
			}
			if (a.getStreat() == null) {
				a.setStreat(aDb.getStreat());
			}
			if (a.getLandMark() == null) {
				a.setLandMark(aDb.getLandMark());
			}
			if (a.getState() == null) {
				a.setState(aDb.getState());
			}
			if (a.getDistrict() == null) {
				a.setDistrict(null);
			}
			if (a.getMandal() == null) {
				a.setMandal(aDb.getMandal());
			}
			if (a.getPinCode() == 0) {
				a.setPinCode(aDb.getPinCode());
			}
			return repo.save(a);
		} else
			return null;

	}

}
