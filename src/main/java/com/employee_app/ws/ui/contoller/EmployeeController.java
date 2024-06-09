package com.employee_app.ws.ui.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee_app.ws.exceptions.ErrorMessages;
import com.employee_app.ws.service.EmployeeService;
import com.employee_app.ws.ui.model.request.EmployeeRequestModel;
import com.employee_app.ws.ui.model.response.EmployeeResponseModel;
import com.employee_app.ws.ui.model.response.OperationStatusModel;
import com.employee_app.ws.ui.model.response.RequestOperationStatus;
import com.employee_app.ws.ui.sharedDto.EmployeeDto;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	EmployeeService empService;

	@PostMapping
	public EmployeeResponseModel createEmployee(@Valid @RequestBody EmployeeRequestModel employeeDetails)
			throws Exception {

		EmployeeResponseModel returnValue = new EmployeeResponseModel();
		if (employeeDetails.getFirstName().isEmpty()) {
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

		EmployeeDto empDto = mapper.map(employeeDetails, EmployeeDto.class);

		EmployeeDto empUser = empService.createEmployee(empDto);

		returnValue = mapper.map(empUser, EmployeeResponseModel.class);

		return returnValue;
	}

	@GetMapping("/employeeList")
	public List<EmployeeResponseModel> getEmployeeList() {
		
		List<EmployeeResponseModel> listValue=new ArrayList<>();
		List<EmployeeDto> employeesDto=empService.getEmployees();
		for(EmployeeDto empDto :employeesDto) {
			
			EmployeeResponseModel empModel=new EmployeeResponseModel();
			BeanUtils.copyProperties(empDto, empModel);
			listValue.add(empModel);
		}
		return listValue;
	}
	
	@PutMapping(path="/{id}")
	public EmployeeResponseModel updateEmployeeDetails(@PathVariable String id,@RequestBody EmployeeRequestModel empDetails) throws Exception {
		
		if(empDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		EmployeeDto empDto=new EmployeeDto();
		BeanUtils.copyProperties(empDetails, empDto);
		EmployeeDto updateEmployee=empService.updateEmployee(id,empDto);
		return mapper.map(updateEmployee,EmployeeResponseModel.class );
	}
	
	@DeleteMapping(path="/{id}")
	public OperationStatusModel deleteEmplyoee(@PathVariable String id) {
		
		OperationStatusModel returnValue=new OperationStatusModel();
		returnValue.setOperationName(RequestOperationalName.DELETE.name());
		empService.deleteEmplyoee(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	// pagination 
	@GetMapping
	public List<EmployeeResponseModel> getEmployees(@RequestParam(name = "page",defaultValue="0") int pageNumber,@RequestParam(value="limit",defaultValue="25") int limit,  @RequestParam(defaultValue = "companyName") String sortBy ){
		
		List<EmployeeResponseModel> returnValue=new ArrayList<>();
		List<EmployeeDto> employees=empService.getEmployees(pageNumber,limit,sortBy);
		for(EmployeeDto empDto:employees) {
			
			returnValue.add(mapper.map(empDto, EmployeeResponseModel.class));
		}
		return returnValue;
		
	}
	
	

}