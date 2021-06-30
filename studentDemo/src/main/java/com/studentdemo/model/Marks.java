package com.studentdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Marks {

	@Id
	@Column
	private String hallTicketNumber;

	@Column
	private float avg;
	@Column
	private int totalMarks;
	@Column
	private int physics;
	@Column
	private int chemistry;
	@Column
	private int maths;

	public Marks(String hallTicketNumber, int physics, int chemistry, int maths) {
		super();
		this.hallTicketNumber = hallTicketNumber;
		this.physics = physics;
		this.chemistry = chemistry;
		this.maths = maths;
	}

}
