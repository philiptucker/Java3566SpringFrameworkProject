package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

    Enrollment findEnrollmentByEid(Integer eid);
    Iterable<Enrollment> findAllByCourseId(Integer courseId);
    Iterable<Enrollment> findAllByStudentId(Integer studentId);

}

