package com.student.demo.enums;

public enum Constent {

	STUDENT_DEMO_APP_NAME("STUDENTDEMO"), STUDENT_APP_NAME("STUDENT");

	private final String name;

	private Constent(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}

}
