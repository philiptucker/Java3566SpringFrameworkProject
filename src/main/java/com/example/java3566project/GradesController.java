package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/grades")
public class GradesController {
    @Autowired
    private GradesRepository GradesRepository;
    @Autowired
    private CourseRepository CourseRepository;
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewGrades (@RequestParam Integer courseId
            , @RequestParam Integer studentId, @RequestParam Double grade) {
        Course c =  CourseRepository.findCourseByCourseId(courseId);
        if (c != null) {
            Student s = StudentRepository.findStudentByStudentId(studentId);
            if (s != null){
                Grades n = new Grades();
                n.setCourseId(courseId);
                n.setStudentId(studentId);
                n.setGrade(grade);
                GradesRepository.save(n);
                return "New Grades: " + s.getFirstName() + " " + s.getLastName() +
                        " has been graded " + grade + "% in " + c.getCourseName();
            } else {
                return "StudentId: " + studentId + " is not in our system";
            }
        } else {
            return "CourseId: " + courseId + " is not in our system";
        }
    }

    @GetMapping(path="/list/course/{courseId}")
    public @ResponseBody Iterable<Grades> getCourseGrades(@PathVariable Integer courseId) {
        return GradesRepository.findAllByCourseId(courseId);
    }

    @GetMapping(path="/list/student/{studentId}")
    public @ResponseBody Iterable<Grades> getStudentGrades(@PathVariable Integer studentId) {
        return GradesRepository.findAllByStudentId(studentId);
    }

    @PutMapping(path="/modify/{id}")
    public @ResponseBody String updateEnrollment(@PathVariable Integer id
            , @RequestParam Integer courseId, @RequestParam Integer studentId
            , @RequestParam Double grade){
        Course c =  CourseRepository.findCourseByCourseId(courseId);
        Student s = StudentRepository.findStudentByStudentId(studentId);
        if (c != null) {
            if (s != null){
                Grades n =  GradesRepository.findGradesByGid(id);
                if (n != null) {
                    n.setCourseId(c.getCourseId());
                    n.setStudentId(s.getStudentId());
                    n.setGrade(grade);
                    GradesRepository.save(n);
                    return "gid: " + id + " has been updated";
                } else {
                    return "gid: " + id + " is not in our system";
                }
            } else {
                return "StudentId: " + studentId + " is not in our system";
            }
        } else {
            return "CourseId: " + courseId + " is not in our system";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteGrades(@PathVariable Integer id){
        Grades n = GradesRepository.findGradesByGid(id);
        if (n != null){
            GradesRepository.delete(n);
            return "gid: " + id + " has been removed";
        } else {
            return "gid: " + id + " is not in our system";
        }
    }
}

