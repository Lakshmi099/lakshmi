package com.student.demo.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Marks {
	private float avg;
	private int totalMarks;
	private int physics;
	private int chemistry;
	private int maths;
	private String hallTicketNumber;
	

}
