package com.example.demo.repository;

import com.example.demo.dto.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDb {
    List<Student> llist=new ArrayList<>();
    Student obj1 = new Student(1,"Amit0","hegde1","ece1",1);
    Student obj2 = new Student(5,"Amit1","hegde2","ece2",2);
    Student obj3 = new Student(7,"Amit2","hegde3","ece3", 3);
    Student obj4 = new Student(8,"Amit3","hegde4","ece4" , 4);
    Student obj5 = new Student(9,"Amit4","hegde5","ece5" , 5);

    {
        llist.add(obj1);
        llist.add(obj2);
        llist.add(obj3);
        llist.add(obj4);
        llist.add(obj5);
    }

    public List<Student> getStudentList(){
        return llist;
    }
    public Student getStudentById(int id){
        for(Student s : llist){
            if(s.getId() == id){
                return  s;
            }
        }
        return null;
    }

    public void addStudent(Student student){
        llist.add(student);
    }

    public Student updateStudent(Student student){
//        llist.remove(student.getId());
//        llist.add(student);


        for(Student i : llist){
            if(i.getId() == student.getId()){
                i.setFname(student.getFname());
                i.setLname(student.getLname());
                i.setBranch(student.getBranch());
                i.setDeptId(student.getDeptId());

                return new Student(i.getId() , i.getFname() , i.getLname() , i.getBranch(), i.getDeptId());
            }
        }
        return null;
    }

    public void deleteStudent(int i){
//        llist.remove(llist.get(i));


        for(Student student : llist){
            if(student.getId() == i){
                llist.remove(student);
                break;
            }
        }
    }
}