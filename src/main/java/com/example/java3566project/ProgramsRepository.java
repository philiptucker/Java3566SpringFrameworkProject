package com.example.java3566project;

import org.springframework.data.repository.CrudRepository;
import com.example.java3566project.Programs;

public interface ProgramsRepository extends CrudRepository<Programs, Integer> {

    Programs findProgramsByPid(Integer pid);

}
