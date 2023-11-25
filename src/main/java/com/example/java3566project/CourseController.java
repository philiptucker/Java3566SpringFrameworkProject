package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping(path="/course")
public class CourseController {
    @Autowired
    private CourseRepository CourseRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewCourse (@RequestParam String courseName
            , @RequestParam Integer courseNumber, @RequestParam Integer capacity) {

        Course n = new Course();
        n.setCourseName(courseName);
        n.setCourseNumber(courseNumber);
        n.setCapacity(capacity);
        CourseRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Course> getAllCourses() {
        return CourseRepository.findAll();
    }

    @GetMapping(path="/view/{id}")
    public @ResponseBody Course getCourse(@PathVariable Integer id) {
        return CourseRepository.findCourseByCourseId(id);
    }
}

