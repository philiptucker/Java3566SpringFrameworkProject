package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentByStudentId(Integer studentId);

}
