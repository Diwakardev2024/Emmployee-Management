package com.employee_app.ws.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Company")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CompanyEntity {
	
	@Id
	@Column(length=30,nullable=false)
	private String registrationId;
	
	@Column(length=60,nullable=false)
	private String companyName;
	
	@Column(length=60,nullable=false)
	private String email;
	
	@Column(length=12,nullable=false)
	private Long phoneNumber;
	
	@Column(length=30, nullable=false)
	private String country;

}
