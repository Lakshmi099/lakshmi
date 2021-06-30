package com.student.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.model.Student;
import com.student.demo.model.StudentInfo;
import com.student.demo.service.StudentService;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student/api/")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;

	/*
	 * @GetMapping(path = "marks{hallticketNumber}") public ResponseEntity<Student>
	 * getStudentMarks(@PathVariable String hallticketNumber) { Student response =
	 * studentService.getStudentMarks(hallticketNumber); return new
	 * ResponseEntity<>(response, HttpStatus.OK);
	 * 
	 * }
	 */

	@GetMapping(path = "info/{studentId}")
	public ResponseEntity<StudentInfo> getStudentInfo(@PathVariable String studentId) {

		try {
			StudentInfo response = studentService.getStudenInfo(studentId);
			if (!ObjectUtils.isEmpty(response) && StringUtils.isNotBlank(response.getStudentId())) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "add/student")
	public ResponseEntity<String> saveStudentInfo(@RequestBody StudentInfo student) {
		log.info("inside saveStudentInfo  method");
		try {
			StudentInfo info = studentService.saveOrUpdateStudentInfo(student);
			if (info != null && null != info.getStudentId()) {
				return new ResponseEntity<String>("Success-  Student id : " + info.getStudentId(), HttpStatus.OK);
			} else {

				return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed...", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
