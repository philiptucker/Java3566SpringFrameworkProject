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
    public @ResponseBody String addNewEnrollment (@RequestParam Integer courseId
            , @RequestParam Integer studentId) {
        Course c =  CourseRepository.findCourseByCourseId(courseId);
        if (c != null) {
            Student s = StudentRepository.findStudentByStudentId(studentId);
            if (s != null){
                Enrollment n = new Enrollment();
                n.setCourseId(courseId);
                n.setStudentId(studentId);
                EnrollmentRepository.save(n);
                return "New Enrollment: " + s.getFirstName() + " " + s.getLastName() +
                        " has been enrolled into " + c.getCourseName();
            } else {
                return "StudentId: " + studentId + " is not in our system";
            }
        } else {
            return "CourseId: " + courseId + " is not in our system";
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
    public @ResponseBody String updateEnrollment(@PathVariable Integer id
            , @RequestParam Integer courseId, @RequestParam Integer studentId){
        Course c =  CourseRepository.findCourseByCourseId(courseId);
        Student s = StudentRepository.findStudentByStudentId(studentId);
        if (c != null) {
            if (s != null){
                Enrollment n =  EnrollmentRepository.findEnrollmentByEid(id);
                if (n != null) {
                    n.setCourseId(c.getCourseId());
                    n.setStudentId(s.getStudentId());
                    EnrollmentRepository.save(n);
                    return "eid: " + id + " has been updated";
                } else {
                    return "eid: " + id + " is not in our system";
                }
            } else {
                return "StudentId: " + studentId + " is not in our system";
            }
        } else {
            return "CourseId: " + courseId + " is not in our system";
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
