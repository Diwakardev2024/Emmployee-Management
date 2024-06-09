package com.employee_app.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee_app.ws.entity.EmpAddressEntity;

@Repository
public interface EmpAddressRepository extends JpaRepository<EmpAddressEntity, String> {
	

}
