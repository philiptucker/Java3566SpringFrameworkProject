package com.example.java3566project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Programs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer pid;
    private String programName;
    private String campus;

    public Programs(){

    }

    public Programs(String programName, String campus) {
        this.programName = programName;
        this.campus = campus;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
