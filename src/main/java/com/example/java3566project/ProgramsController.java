package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/programs")
public class ProgramsController {
    @Autowired
    public ProgramsRepository ProgramsRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewProgram (@RequestParam String programName
            , @RequestParam String campus) {

        Programs n = new Programs();
        n.setProgramName(programName);
        n.setCampus(campus);
        ProgramsRepository.save(n);
        return "New Program: " + n.getProgramName() + " has been added to the system";
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Programs> getAllPrograms() {
        return ProgramsRepository.findAll();
    }

    @GetMapping(path="/view/{id}")
    public @ResponseBody Programs getPrograms(@PathVariable Integer id) {
        Programs n = ProgramsRepository.findProgramsByPid(id);
        if (n == null){
            n = new Programs();
            n.setProgramName("pid: " + id + " is not in our system");
        }
        return n;
    }

    @PutMapping(path="/modify/{id}")
    public @ResponseBody String updateProgram(@PathVariable Integer id
            , @RequestParam Optional<String> programName, @RequestParam Optional<String> campus){
        Programs n =  ProgramsRepository.findProgramsByPid(id);
        if (n != null) {
            if (programName.isPresent()){
                n.setProgramName(programName.toString().substring(9, programName.toString().length()-1));
            }
            if (campus.isPresent()){
                n.setCampus(campus.toString().substring(9, campus.toString().length()-1));
            }
            ProgramsRepository.save(n);
            return "pid: " + id + " has been updated";
        } else {
            return "pid: " + id + " is not in our system";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteProgram(@PathVariable Integer id){
        Programs n = ProgramsRepository.findProgramsByPid(id);
        if (n != null){
            ProgramsRepository.delete(n);
            return "pid: " + id + " has been removed";
        } else {
            return "pid: " + id + " is not in our system";
        }
    }
}