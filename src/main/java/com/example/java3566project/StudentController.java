package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewStudent (@RequestParam String firstName
            , @RequestParam String lastName, @RequestParam String email
            , @RequestParam String address, @RequestParam String city
            , @RequestParam String postal, @RequestParam String phone) {

        Student n = new Student();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setEmail(email);
        n.setAddress(address);
        n.setCity(city);
        n.setPostal(postal);
        n.setPhone(phone);
        StudentRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return StudentRepository.findAll();
    }

    @GetMapping(path="/view/{id}")
    public @ResponseBody Student getStudent(@PathVariable Integer id) {
        return StudentRepository.findStudentByStudentId(id);

    }
}

