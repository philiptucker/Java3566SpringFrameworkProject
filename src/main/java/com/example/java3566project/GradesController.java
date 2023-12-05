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
    public @ResponseBody Grades addNewGrades (@RequestBody Grades grade) {
        Course c =  CourseRepository.findCourseByCourseId(grade.getCourseId());
        if (c != null) {
            Student s = StudentRepository.findStudentByStudentId(grade.getStudentId());
            if (s != null){
                return GradesRepository.save(grade);
            } else {
                return null;
            }
        } else {
            return null;
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
    public @ResponseBody Grades updateEnrollment(@PathVariable Integer id
            , @RequestBody Grades grade){
        Course c =  CourseRepository.findCourseByCourseId(grade.getCourseId());
        Student s = StudentRepository.findStudentByStudentId(grade.getStudentId());
        if (c != null) {
            if (s != null){
                Grades n =  GradesRepository.findGradesByGid(id);
                if (n != null) {
                    if (grade.getGrade() != null){
                        n.setCourseId(c.getCourseId());
                        n.setStudentId(s.getStudentId());
                        n.setGrade(grade.getGrade());
                    }
                    return GradesRepository.save(n);
                } else {
                    return GradesRepository.findGradesByGid(id);
                }
            } else {
                return null;
            }
        } else {
            return null;
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

