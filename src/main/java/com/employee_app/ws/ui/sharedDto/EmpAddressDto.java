package com.employee_app.ws.ui.sharedDto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpAddressDto implements Serializable {

	private static final long serialVersionUID = 3757423798365094882L;
	private  String addressId;
	private String type;
	private String city;
	private Long postalCode;
	private String streetNo;
	private String HouseNo;
	private String country;
	private String state;
	private EmployeeDto empDatails;
}
