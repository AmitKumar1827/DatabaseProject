package com.example.demo.entity;

import org.hibernate.mapping.Collection;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "Student")
public class StudentMongo {

    @Id
    private Integer id;

    private String Name;


    public StudentMongo() {
    }

    public StudentMongo(Integer id, String name) {
        this.id = id;
        Name = name;
    }

    public StudentMongo(Integer id) {
        this.id = id;
    }

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
