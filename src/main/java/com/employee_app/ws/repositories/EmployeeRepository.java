package com.employee_app.ws.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee_app.ws.entity.EmployeeEntity;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

	Optional<EmployeeEntity> findByEmail(String email);
//	Page<EmployeeEntity> findAllWithPagination(PageRequest of);
	
	@Query(value="select * from Employee emp where emp.employee_id=?",nativeQuery=true)
	EmployeeEntity findByEmployeeId(String empId);
	
}
