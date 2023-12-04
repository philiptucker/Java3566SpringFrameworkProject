package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add")
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
        return "New Student: " + n.getFirstName() + " " + n.getLastName() + " has been added to the system";
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
    public @ResponseBody String updateStudent(@PathVariable Integer id
            , @RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName
            , @RequestParam Optional<String> email, @RequestParam Optional<String> address
            , @RequestParam Optional<String> city, @RequestParam Optional<String> postal
            , @RequestParam Optional<String> phone){
        Student n =  StudentRepository.findStudentByStudentId(id);
        if (n != null) {
            if (firstName.isPresent()){
                n.setFirstName(firstName.toString().substring(9, firstName.toString().length()-1));
            }
            if (lastName.isPresent()){
                n.setLastName(lastName.toString().substring(9, lastName.toString().length()-1));
            }
            if (email.isPresent()){
                n.setEmail(email.toString().substring(9, email.toString().length()-1));
            }
            if (address.isPresent()){
                n.setAddress(address.toString().substring(9, address.toString().length()-1));
            }
            if (city.isPresent()){
                n.setCity(city.toString().substring(9, city.toString().length()-1));
            }
            if (postal.isPresent()){
                n.setPostal(postal.toString().substring(9, postal.toString().length()-1));
            }
            if (phone.isPresent()){
                n.setPhone(phone.toString().substring(9, phone.toString().length()-1));
            }
            StudentRepository.save(n);
            return "StudentId: " + id + " has been updated";
        } else {
            return "StudentId: " + id + " is not in our system";
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

