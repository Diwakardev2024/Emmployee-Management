package com.employee_app.ws.service;

import java.util.List;

import com.employee_app.ws.ui.sharedDto.EmployeeDto;


public interface EmployeeService {
	
	EmployeeDto createEmployee(EmployeeDto empUser);

	List<EmployeeDto> getEmployees();

 EmployeeDto updateEmployee(String id, EmployeeDto empDto) ;

void deleteEmplyoee(String empId);

List<EmployeeDto> getEmployees(int page, int limit, String sortBy);
 
}
