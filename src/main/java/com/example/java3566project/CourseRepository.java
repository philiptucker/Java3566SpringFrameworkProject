package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Course;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findCourseByCourseId(Integer courseId);

}
