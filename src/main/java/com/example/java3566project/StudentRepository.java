package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Student;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentByStudentId(Integer studentId);

}
