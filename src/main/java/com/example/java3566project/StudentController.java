package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add")
    public @ResponseBody Student addNewStudent (@RequestBody Student student) {
        return StudentRepository.save(student);
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return StudentRepository.findAll();
    }

    @GetMapping(path="/view/{id}")
    public @ResponseBody Student getStudent(@PathVariable Integer id) {
        Student n = StudentRepository.findStudentByStudentId(id);
        if (n == null){
            n = new Student();
            n.setFirstName("StudentId: " + id + " is not in our system");
        }
        return n;
    }

    @PutMapping(path="/modify/{id}")
    public @ResponseBody Student updateStudent(@PathVariable Integer id
            , @RequestBody Student student){
        Student n =  StudentRepository.findStudentByStudentId(id);
        if (n != null) {
            if(student.getFirstName() != null){
                n.setFirstName(student.getFirstName());
            }
            if(student.getLastName() != null) {
                n.setLastName(student.getLastName());
            }
            if(student.getEmail() != null) {
                n.setEmail(student.getEmail());
            }
            if(student.getAddress() != null) {
                n.setAddress(student.getAddress());
            }
            if(student.getCity() != null) {
                n.setCity(student.getCity());
            }
            if(student.getPostal() != null) {
                n.setPostal(student.getPostal());
            }
            if(student.getPhone() != null) {
                n.setPhone(student.getPhone());
            }
            return StudentRepository.save(n);
        } else {
            return StudentRepository.findStudentByStudentId(id);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteStudent(@PathVariable Integer id){
        Student n = StudentRepository.findStudentByStudentId(id);
        if (n != null){
            StudentRepository.delete(n);
            return "StudentId: " + id + " has been removed";
        } else {
            return "StudentId: " + id + " is not in our system";
        }
    }
}

