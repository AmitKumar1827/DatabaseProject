package com.example.demo.repository;

import com.example.demo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRedisRepository {
    private String STUDENT_COLLECTION = "student";
    private RedisTemplate<String ,Student> redisTemplate;
    private HashOperations hashOps;


    public StudentRedisRepository(RedisTemplate<String, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        this.hashOps = this.redisTemplate.opsForHash();
    }
    public List<Student>findAll(){
        Map<String , Student> allElement = hashOps.entries(STUDENT_COLLECTION);
        return allElement.values().stream().collect(Collectors.toList());
    }

    public void addStudent(Student student){
        hashOps.put(STUDENT_COLLECTION,student.getId(),student);
    }

    public void delete(Integer id){
        hashOps.delete(STUDENT_COLLECTION,id);

    }

}
