package com.employee_app.ws.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Employee")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEntity implements Serializable{
	
	
	private static final long serialVersionUID = 8781733454335208342L;

	@Id
	@Column(name = "employeeId", length=30,nullable=false)
	private String empId;
	
	@Column(length=30,nullable=false)
	private String firstName;
	
	@Column(length=30)
	private String lastName;
	
	@Column(length=10,nullable=false,unique = true)
	private Long mobileNo;
	
	@Column(length=40,nullable=false,unique=true)
	private String email;
	
	@Column(length=50,nullable=false)
	private String qualification;
	
	@Column(length=8,nullable=false)
	private Float currentExperience;
	
	@Column(length=10,nullable=false)
	private String gender;
	
	@Column(length=50)
	private String department;
	
	@Column(length=60)
	private String companyName;
	
	@Column(updatable = false)
	@CreatedDate
	private Instant createdOn;
	@LastModifiedDate 
	private Instant lastUpdatedOn;
	
	@OneToMany(mappedBy="empDetails",cascade = CascadeType.ALL)
	private List<EmpAddressEntity> empAddresses;
	

}
