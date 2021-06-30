package com.student.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Student {

	@Id
	@Column
	private int studentId;
	
	@Column
	private String name ;
	@Column
	private String hallTicketNumber;
	
}
