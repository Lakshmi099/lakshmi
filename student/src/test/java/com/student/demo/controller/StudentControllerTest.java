package com.student.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.demo.model.StudentInfo;
import com.student.demo.service.StudentService;
import com.student.demo.start.StudentApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = StudentApplication.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public StudentService studentService;

	@Before
	public void init() {
		assertNotNull(studentService);
		assertNotNull(mockMvc);
	}

	@Test
	public void testGetStudenInfo() throws Exception {
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setStudentId("1");
		studentInfo.setName("Local Test");
		when(studentService.getStudenInfo("1")).thenReturn(studentInfo);
		mockMvc.perform(get("/student/api/info/{studentId}", "1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.studentId", is(studentInfo.getStudentId())))
				.andExpect(jsonPath("$.name", is(studentInfo.getName())));
	}

	@Test
	public void testGetStudenInfoWithEmptyResponse() throws Exception {
		StudentInfo studentInfo = new StudentInfo();
		// studentInfo.setStudentId("1");
		// studentInfo.setName("Local Test");
		when(studentService.getStudenInfo("1")).thenReturn(studentInfo);
		mockMvc.perform(get("/student/api/info/{studentId}", "1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.studentId", is(studentInfo.getStudentId())));

	}

	@Test
	public void testGetStudenInfoWithEmptyStudentId() throws Exception {
		StudentInfo studentInfo = new StudentInfo();

		studentInfo.setName("Local Test");
		when(studentService.getStudenInfo("1")).thenReturn(studentInfo);
		mockMvc.perform(get("/student/api/info/{studentId}", "1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testGetStudenInfoWithNullResponse() throws Exception {

		when(studentService.getStudenInfo("1")).thenReturn(null);
		mockMvc.perform(get("/student/api/info/{studentId}", "1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testGetStudenInfoWithInternalServerError() throws Exception {

		when(studentService.getStudenInfo("1")).thenThrow(new RuntimeException());
		mockMvc.perform(get("/student/api/info/{studentId}", "1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isInternalServerError());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
