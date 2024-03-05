package com.jsp.Springboot_liveproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Springboot_liveproject1.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
