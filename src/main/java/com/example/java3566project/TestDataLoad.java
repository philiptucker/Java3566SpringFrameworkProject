package com.example.java3566project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TestDataLoad {

    @Autowired
    private StudentRepository StudentRepository;
    @Autowired
    private CourseRepository CourseRepository;
    @Autowired
    private GradesRepository GradesRepository;
    @Autowired
    private ProgramsRepository ProgramsRepository;
    @Autowired
    private EnrollmentRepository EnrollmentRepository;

    @PostConstruct
    public void createTestData(){

        // create students
        for (int i = 1; i <= 5; i++){
            Student student = new Student(
                    "Some"+i,
                    "Person"+i,
                    "fakeEmail"+i+"@gmail.com",
                    i + " Some St.",
                    "Townsville",
                    "A1B 2E"+i,
                    "(586)135-835"+i
            );
            StudentRepository.save(student);
        }

        // create courses
        for (int i = 1; i <= 2; i++){
            Course course = new Course(
                    "Java"+i,
                    3000+i,
                    23,
                    2023,
                    "Fall",
                    1
            );
            CourseRepository.save(course);
        }
        // create programs
        Programs program1 = new Programs(
                "Accelerated Software Development",
                "Prince Philip Drive"
        );
        Programs program2 = new Programs(
                "Software Development",
                "Prince Philip Drive"
        );
        ProgramsRepository.save(program1);
        ProgramsRepository.save(program2);

        // create enrollments
        for (int i = 1; i <= 5; i++){
            Enrollment enrollment = new Enrollment(
                    1,
                    i
            );
            EnrollmentRepository.save(enrollment);
        }
        // create grades
        Random r = new Random();
        for (int i = 1; i <= 5; i++){
            Grades grade = new Grades(
                    i,
                    1,
                    Math.round(0 + r.nextDouble()*(100) * 10.0)/ 10.0
            );
            GradesRepository.save(grade);
        }
    }
}
