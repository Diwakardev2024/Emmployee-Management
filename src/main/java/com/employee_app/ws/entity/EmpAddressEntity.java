package com.employee_app.ws.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="EmpAddress")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmpAddressEntity implements Serializable{
	
	
	private static final long serialVersionUID = 4523497019640379051L;

	@Id
	
	@Column(length=30, nullable=false)
	private  String addressId;
	
	@Column(length=40,nullable=false)
	private String type;
	
	@Column(length=40, nullable=false)
	private String city;
	
	@Column(length=40, nullable=false)
	private Long postalCode;
	
	@Column(length=30, nullable=false)
	private String streetNo;
	
	@Column(length=30)
	private String HouseNo;
	
	@Column(length=30, nullable=false)
	private String country;
	
	@Column(length=30, nullable=false)
	private String state;
	
	@ManyToOne
	@JoinColumn(name="Employee_id")
	private EmployeeEntity empDetails;

	
}
