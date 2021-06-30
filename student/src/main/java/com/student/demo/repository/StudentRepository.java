package com.student.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.student.demo.model.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}