package com.employee_app.ws.ui.sharedDto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 4009578924559906000L;
	private String empId;
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
