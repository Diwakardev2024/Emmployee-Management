package com.employee_app.ws.ui.model.request;

import java.util.List;

import com.employee_app.ws.ui.sharedDto.EmpAddressDto;

import lombok.Data;

@Data
public class EmployeeRequestModel {

	private String firstName;
	private String lastName;
	private String email;
	private Long mobileNo;
	private String qualification;
	private Float currentExperience;
	private String gender;
	private String department;
	private String companyName;
	private List<EmpAddressDto> empAddresses;
}
