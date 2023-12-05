package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/course")
public class CourseController {
    @Autowired
    private CourseRepository CourseRepository;
    @Autowired
    private ProgramsRepository ProgramsCourseRepository;

    @PostMapping(path="/add")
    public @ResponseBody Course addNewCourse (@RequestBody Course course) {
        Programs p = ProgramsCourseRepository.findProgramsByPid(course.getPid());
        if (p != null){
            return CourseRepository.save(course);
        } else {
            course.setCourseName("PID " + course.getPid() + " is not in our system.");
            return course;
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
    public @ResponseBody Course updateCourse(@PathVariable Integer id
            , @RequestBody Course course) {
        Course n =  CourseRepository.findCourseByCourseId(id);
        if (n != null) {
            if (course.getCourseName() != null){
                n.setCourseName(course.getCourseName());
            }
            if (course.getCourseNumber() != null){
                n.setCourseNumber(course.getCourseNumber());
            }
            if (course.getCapacity() != null){
                n.setCapacity(course.getCapacity());
            }
            if (course.getYear() != null){
                n.setYear(course.getYear());
            }
            if (course.getSemester() != null){
                n.setSemester(course.getSemester());
            }
            if (course.getPid() != null){
                n.setPid(course.getPid());
            }
            return CourseRepository.save(n);
        } else {
            return CourseRepository.findCourseByCourseId(id);
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

