package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Student implements Serializable {

    static final long serialVersionUID=42L;

    private Integer id;

    @NotBlank(message = "student details cant be empty")
    private String fname;

    private String lname;
    private String branch;
    private int deptId;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", branch='" + branch + '\'' +
                ", deptId=" + deptId +
                '}';
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Student() {
    }

    public Student(int id, String fname, String lname, String branch, int deptId) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.branch = branch;
        this.deptId = deptId;
    }
}