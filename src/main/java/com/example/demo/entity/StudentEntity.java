package com.example.demo.entity;

import io.swagger.models.auth.In;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "Student")
public class StudentEntity {

    public StudentEntity(Integer id){
        this.id = id;
    }

    public StudentEntity() {
    }

    public StudentEntity(@NotBlank(message = "Student name cant be empty") String name) {
        Name = name;
    }

    public StudentEntity(Integer id, @NotBlank(message = "Student name cant be empty") String name) {
        this.id = id;
        Name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Integer id;

    @NotBlank(message = "Student name cant be empty")
    private String Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
