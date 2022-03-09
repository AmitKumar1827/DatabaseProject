package com.example.demo.service;

import com.example.demo.dto.Student;

import java.util.List;

public interface StudentServices {


    List<Student> getAllStudents();
    Student getStudent(Integer id);
    Student getStudents(Integer id , String Name);
    void addStudent(Student student);
    Student updateStudents(Student student);
    void deleteStudent(Integer id);

    void addStudentList(List<Student> s);
}
