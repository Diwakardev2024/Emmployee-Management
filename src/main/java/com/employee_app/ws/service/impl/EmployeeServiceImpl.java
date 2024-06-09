package com.employee_app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.employee_app.ws.entity.EmployeeEntity;
import com.employee_app.ws.exceptions.ErrorMessages;
import com.employee_app.ws.exceptions.UserServiceException;
import com.employee_app.ws.repositories.EmployeeRepository;
import com.employee_app.ws.service.EmployeeService;
import com.employee_app.ws.shared.Utils;
import com.employee_app.ws.ui.sharedDto.EmpAddressDto;
import com.employee_app.ws.ui.sharedDto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper mapper;
	
	

	@Override
	public EmployeeDto createEmployee(EmployeeDto empUser) {

		Optional<EmployeeEntity> empOpt = empRepository.findByEmail(empUser.getEmail());
		if (empOpt.isPresent()) {
			throw new RuntimeException("Records already exits");
		}

		List<EmpAddressDto> empAddressList = empUser.getEmpAddresses();

		for (int i = 0; i < empAddressList.size(); i++) {
			empAddressList.get(i).setAddressId(utils.generatedEmpAddressId(20));
		}
		EmployeeEntity empEntity = mapper.map(empUser, EmployeeEntity.class);
		String publicEmpId = utils.generatedEmployeeId(30);
		empEntity.setEmpId(publicEmpId);
		EmployeeEntity storedEmployeeDetails = empRepository.save(empEntity);
		mapper.map(empUser, empEntity);
		return mapper.map(storedEmployeeDetails, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getEmployees() {

		List<EmployeeDto> returnValue =new ArrayList<>();
		List<EmployeeEntity> entities=empRepository.findAll();
		for(EmployeeEntity empEntity:entities) {
			returnValue.add(mapper.map(empEntity,EmployeeDto.class));
		}
		return returnValue;
	}

	@Override
	public EmployeeDto updateEmployee(String empId, EmployeeDto empDto) {

		EmployeeDto returnValue=new EmployeeDto();
		EmployeeEntity empEntity=empRepository.findByEmployeeId(empId);
		if(empEntity==null)
			throw new RuntimeException("empId is not found");
		empEntity.setEmail(empDto.getEmail());
		empEntity.setDepartment(empDto.getDepartment());
		
		EmployeeEntity updateEmployeeDetails=empRepository.save(empEntity);
		BeanUtils.copyProperties(updateEmployeeDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteEmplyoee(String empId) {
		EmployeeEntity empEntity=empRepository.findByEmployeeId(empId);
		if (empEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		empRepository.delete(empEntity);
	}

	@Override
	public List<EmployeeDto> getEmployees(int page, int limit,String sortBy) {
		List<EmployeeDto> returnValue = new ArrayList<>();
		if (page > 0)
			page = page - 1;
		Pageable pageableRequest = PageRequest.of(page, limit,Sort.by(sortBy));
		Page<EmployeeEntity> empPage = empRepository.findAll(pageableRequest);
		List<EmployeeEntity> employees = empPage.getContent();
		for(EmployeeEntity en:employees) {
			returnValue.add(mapper.map(en,EmployeeDto.class));
		}
		return returnValue;
	}
}
