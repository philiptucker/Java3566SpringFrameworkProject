package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/course")
public class CourseController {
    @Autowired
    private CourseRepository CourseRepository;
    @Autowired
    private ProgramsRepository ProgramsCourseRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewCourse (@RequestParam String courseName
            , @RequestParam Integer courseNumber, @RequestParam Integer capacity
            , @RequestParam Integer year, @RequestParam String semester
            , @RequestParam Integer pid) {

        Programs p = ProgramsCourseRepository.findProgramsByPid(pid);
        if (p != null){
            Course n = new Course();
            n.setCourseName(courseName);
            n.setCourseNumber(courseNumber);
            n.setCapacity(capacity);
            n.setYear(year);
            n.setSemester(semester);
            n.setPid(pid);
            CourseRepository.save(n);
            return "New Program: " + n.getCourseName() + " has been added to the system";
        } else {
            return "pid: " + pid + " is not in our system";
        }

    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Course> getAllCourses() {
        return CourseRepository.findAll();
    }

    @GetMapping(path="/view/{id}")
    public @ResponseBody Course getCourse(@PathVariable Integer id) {
        return CourseRepository.findCourseByCourseId(id);
    }

    @PutMapping(path="/modify/{id}")
    public @ResponseBody String updateCourse(@PathVariable Integer id
            , @RequestParam Optional<String> courseName, @RequestParam Optional<String> courseNumber
            , @RequestParam Optional<String> capacity, @RequestParam Optional<String> year
            , @RequestParam Optional<String> semester, @RequestParam Optional<String> pid){
        Course n =  CourseRepository.findCourseByCourseId(id);
        if (n != null) {
            if (courseName.isPresent()){
                n.setCourseName(courseName.toString().substring(9, courseName.toString().length()-1));
            }
            if (courseNumber.isPresent()){
                n.setCourseNumber(Integer.parseInt(courseNumber.toString().substring(9, courseNumber.toString().length()-1)));
            }
            if (capacity.isPresent()){
                n.setCapacity(Integer.parseInt(capacity.toString().substring(9, capacity.toString().length()-1)));
            }
            if (year.isPresent()){
                n.setYear(Integer.parseInt(year.toString().substring(9, year.toString().length()-1)));
            }
            if (semester.isPresent()){
                n.setSemester(semester.toString().substring(9, semester.toString().length()-1));
            }
            if (pid.isPresent()){
                n.setPid(Integer.parseInt(pid.toString().substring(9, pid.toString().length()-1)));
            }
            CourseRepository.save(n);
            return "CourseId: " + id + " has been updated";
        } else {
            return "CourseId: " + id + " is not in our system";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteCourse(@PathVariable Integer id){
        Course n = CourseRepository.findCourseByCourseId(id);
        if (n != null){
            CourseRepository.delete(n);
            return "CourseId: " + id + " has been removed";
        } else {
            return "CourseId: " + id + " is not in our system";
        }
    }
}

