package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findCourseByCourseId(Integer courseId);

}
