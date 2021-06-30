package com.student.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.student.demo.enums.Constent;
import com.student.demo.model.Student;
import com.student.demo.model.StudentInfo;
import com.student.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

	@Autowired
	private RestTemplate restTemplate;

	
	@Autowired
    private EurekaClient eurekaClient;
	
	

	
	

	/*
	 * public Student getStudentMarks(final String studentId) {
	 * log.info("inside getStudentMarks() method ,Student Id : {} ", studentId);
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * HttpEntity<String> entity = new HttpEntity<String>(headers);
	 * System.out.println("studentGetMarksEndPoint : " + studentGetMarksEndPoint);
	 * String url = String.format(studentGetMarksEndPoint, "123", studentId);
	 * ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.GET,
	 * entity, Student.class); Student studentResponse = response.getBody();
	 * System.out.println("studentResponse : " + studentResponse); return
	 * studentResponse;
	 * 
	 * }
	 */
	
	
	public StudentInfo getStudenInfo(final String studentId) {
		
		 Application application = eurekaClient.getApplication(Constent.STUDENT_DEMO_APP_NAME.toString());
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        log.info("instanceInfo :{} ",instanceInfo);
	        
	        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "studentInfo/api/student/" + studentId;
	       log.info("url : {}",url);
	        
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<StudentInfo> studentInfo = restTemplate.exchange(url, HttpMethod.GET, entity, StudentInfo.class);

		log.info("studentInfo : {}", studentInfo.getBody());
		log.info("inside getStudenInfo method end");

		return studentInfo.getBody();

	}

	public List<StudentInfo> getAllStudents() {
		log.info("inside getAllStudents() method start");
		
		try {
			
			
		}catch(Exception ex) {}
		return null;
	}

	public StudentInfo saveOrUpdateStudentInfo(StudentInfo student) {
		log.info("inside saveOrUpdateStudentInfo method start");
		
		 Application application = eurekaClient.getApplication(Constent.STUDENT_DEMO_APP_NAME.toString());
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        log.info("instanceInfo :{} ",instanceInfo);
	        
	        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "studentInfo/api/save";
	       log.info("url : {}",url);
		
		//String uri = "http://localhost:8080/studentInfo/api/save";
		
		StudentInfo studentInfo = restTemplate.postForEntity(url, student, StudentInfo.class).getBody();
		log.info("studentInfo : {}", studentInfo);
		log.info("inside saveOrUpdateStudentInfo method start");
		return studentInfo;
	}

	public void deleteStudent(final String studentId) {
	}

}
