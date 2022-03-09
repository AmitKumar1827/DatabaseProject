package com.example.demo.controller;


import com.example.demo.dto.Student;
import com.example.demo.response.Error;
import com.example.demo.response.Response;
import com.example.demo.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Students")
public class StudentController {
    @Autowired
    @Qualifier("studentServiceImplement")
    StudentServices studentServices;

//    @Autowired
//    @Qualifier("studentServiceImplement")
//    CombinedService combinedService;

    @GetMapping(value = "/students", consumes = "application/json", produces = "application/json"
    )
    public List<Student> getStudent() {
        return studentServices.getAllStudents();
    }

//    @GetMapping(value="GetIdByValue/{id}" , produces = "application/xml")
//    public Student getStudentsById(@PathVariable int id){
//        return studentServices.getStudent(id);
//    }

//    @GetMapping
//    public Student getStudentsById(@RequestParam(required = true) int id, @RequestParam(required = false) String Name) {
//        return studentServices.getStudents(id,Name);
//    }

    @GetMapping
    public Student getStudentsById(@RequestParam(required = true) Integer id) {
        return studentServices.getStudent(id);
    }


    @PostMapping(consumes = "application/json")
    public void addStudent(@RequestBody @Valid Student student) {
        studentServices.addStudent(student);
    }

    public void addListStudent(@RequestBody @Valid List<Student> s) {
        studentServices.addStudentList(s);
    }

    @PutMapping
    public Response<Student> updateStudent(@RequestBody @Valid Student student) {
        Student student1 = studentServices.updateStudents(student);
        if (student1 == null) {
            return new Response<Student>(new Error("101", "couldn" +
                    "t save "));
        } else {
            return new Response<Student>(student1);
        }
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam int i) {
        studentServices.deleteStudent(i);
    }
//    @DeleteMapping
//    public void Student deleteStudent( Student student){
//
//    }
//    @GetMapping
//    public Combined getCombined(@RequestParam int id){
//        return combinedService.getAllCombined(id);
//    }


}
