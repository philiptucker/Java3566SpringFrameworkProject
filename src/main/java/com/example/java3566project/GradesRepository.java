package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Grades;

public interface GradesRepository extends CrudRepository<Grades, Integer> {

    Grades findGradesByGid(Integer gid);
    Iterable<Grades> findAllByCourseId(Integer courseId);
    Iterable<Grades> findAllByStudentId(Integer studentId);
}

