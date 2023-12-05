package com.example.java3566project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/programs")
public class ProgramsController {
    @Autowired
    public ProgramsRepository ProgramsRepository;

    @PostMapping(path="/add")
    public @ResponseBody Programs addNewProgram (@RequestBody Programs program) {
        return ProgramsRepository.save(program);
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
    public @ResponseBody Programs updateProgram(@PathVariable Integer id
            , @RequestBody Programs program){
        Programs n =  ProgramsRepository.findProgramsByPid(id);
        if (n != null) {
            if (program.getProgramName() != null){
                n.setProgramName(program.getProgramName());
            }
            if (program.getCampus() != null){
                n.setCampus(program.getCampus());
            }
            return ProgramsRepository.save(n);
        } else {
            return ProgramsRepository.findProgramsByPid(id);
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