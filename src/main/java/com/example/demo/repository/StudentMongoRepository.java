package com.example.demo.repository;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.StudentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMongoRepository extends CrudRepository<StudentMongo , Integer> {

}
