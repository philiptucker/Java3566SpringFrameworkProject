package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository EnrollmentRepository;
    @Autowired
    private CourseRepository CourseRepository;
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add")
    public @ResponseBody Enrollment addNewEnrollment (@RequestBody Enrollment enrollment) {
        Course c =  CourseRepository.findCourseByCourseId(enrollment.getCourseId());
        if (c != null) {
            Student s = StudentRepository.findStudentByStudentId(enrollment.getStudentId());
            if (s != null){
                return EnrollmentRepository.save(enrollment);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping(path="/list/course/{courseId}")
    public @ResponseBody Iterable<Enrollment> getCourseEnrollment(@PathVariable Integer courseId) {
        return EnrollmentRepository.findAllByCourseId(courseId);
    }

    @GetMapping(path="/list/student/{studentId}")
    public @ResponseBody Iterable<Enrollment> getStudentEnrollment(@PathVariable Integer studentId) {
        return EnrollmentRepository.findAllByStudentId(studentId);
    }

    @PutMapping(path="/modify/{id}")
    public @ResponseBody Enrollment updateEnrollment(@PathVariable Integer id
            , @RequestBody Enrollment enrollment){
        Course c =  CourseRepository.findCourseByCourseId(enrollment.getCourseId());
        Student s = StudentRepository.findStudentByStudentId(enrollment.getStudentId());
        if (c != null) {
            if (s != null){
                Enrollment n =  EnrollmentRepository.findEnrollmentByEid(id);
                if (n != null) {
                    n.setCourseId(c.getCourseId());
                    n.setStudentId(s.getStudentId());
                    return EnrollmentRepository.save(n);
                } else {
                    return  EnrollmentRepository.findEnrollmentByEid(id);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteEnrollment(@PathVariable Integer id){
        Enrollment n = EnrollmentRepository.findEnrollmentByEid(id);
        if (n != null){
            EnrollmentRepository.delete(n);
            return "eid: " + id + " has been removed";
        } else {
            return "eid: " + id + " is not in our system";
        }
    }
}
