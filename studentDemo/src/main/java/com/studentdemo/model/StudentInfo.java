package com.studentdemo.model;

import java.util.List;

import lombok.Data;

@Data
public class StudentInfo {
	private int studentId;
	private String name;
	private List<Marks> marks;

	public StudentInfo(int studentId, String name, List<Marks> marks) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.marks = marks;
	}

	public StudentInfo() {
	};
}
