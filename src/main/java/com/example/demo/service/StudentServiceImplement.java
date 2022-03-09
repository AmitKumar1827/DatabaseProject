package com.example.demo.service;

import com.example.demo.dto.Student;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.StudentMongo;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImplement implements StudentServices {

    @Autowired
    StudentDb studentDb;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMongoRepository studentMongoRepository;

    @Autowired
    StudentRedisRepository studentRedisRepository;

//    @Autowired
//     CombinedDb combinedDb;


    @Override
    public List<Student> getAllStudents() {
        Iterable<StudentMongo> iterable = studentMongoRepository.findAll();
        List<Student> students = new ArrayList<>();
        for (StudentMongo i : iterable) {
            Student s = new Student();
            s.setId(i.getId());
            s.setFname(i.getName());
            students.add(s);
        }

        Iterable<StudentEntity> iterator = studentRepository.findAll();
        for (StudentEntity i : iterator) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            students.add(student);
        }
        List<Student> s = studentDb.getStudentList();
        students.addAll(s);
        List<Student> s1 = studentRedisRepository.findAll();
        students.addAll(s1);
        return students;


//        return studentRedisRepository.findAll();


        // return student;
    }

    @Override
    public Student getStudent(Integer id) {
        boolean isRedis = false, isPostgres = false, isListDb = false, isMongo = false;


        List<Student> student1 = studentDb.getStudentList();

        List<Student> redisStudent = studentRedisRepository.findAll();
        Iterable<StudentMongo> mongoStudent = studentMongoRepository.findAll();
        Iterable<StudentEntity> iterator = studentRepository.findAll();
        List<Student> studentEntities = new ArrayList<>();
        List<Student> studentsMongo = new ArrayList<>();
        for (StudentEntity i : iterator) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentEntities.add(student);
        }

        for (StudentMongo i : mongoStudent) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentsMongo.add(student);
        }




        for (Student s : student1) {
            if (s.getId() == id) {
                isListDb = true;
                break;
            }
        }

        for (Student s : studentEntities) {
            if (s.getId() == id) {
                isPostgres = true;
                break;
            }
        }

        for (Student s : redisStudent) {
            if (s.getId() == id) {
                isRedis = true;
                break;
            }
        }
        for (Student s : studentsMongo) {
            if (s.getId() == id) {
                isMongo = true;
            }
        }

        if (isListDb) {

            return studentDb.getStudentById(id);

        } else if (isPostgres) {
            System.out.println("hello in postgres");
            for (Student s : studentEntities) {
                if (s.getId() == id) {
                    return s;
                }
            }

        } else if (isRedis) {
            for (Student s : redisStudent) {
                if (s.getId() == id) {
                    return s;
                }
            }
        } else if (isMongo) {
            for (Student s : studentsMongo) {
                if (s.getId() == id) {
                    return s;
                }
            }
        }

        return null;
    }

    @Override
    public Student getStudents(Integer id, String Name) {
        if (Name != null) {
            for (Student student : studentDb.getStudentList()) {

                if (student.getId() == id && student.getFname().equals(Name)) {
                    return student;
                }
            }
        } else {
            for (Student student : studentDb.getStudentList()) {
                if (student.getId() == id) {
                    return student;
                }
            }
        }

        return null;

    }

    @Override
    public void addStudent(Student student) {
//        studentRepository.save(new StudentEntity(student.getFname()));
//        studentDb.addStudent(student);



        if (student.getDeptId() == 1) {
            studentDb.addStudent(student);
        } else if (student.getDeptId() == 2) {
            studentRepository.save(new StudentEntity(student.getId(), student.getFname()));
        } else if (student.getDeptId() == 3) {
            studentRedisRepository.addStudent(student);
        } else if (student.getDeptId() == 4) {

            studentMongoRepository.save(new StudentMongo(student.getId(), student.getFname()));
        }


    }

    @Override
    public Student updateStudents(Student stud) {
        boolean isRedis = false, isPostgres = false, isListDb = false, isMongo = false;

        List<Student> student1 = studentDb.getStudentList();
        List<Student> redisStudent = studentRedisRepository.findAll();
        Iterable<StudentMongo> mongoStudent = studentMongoRepository.findAll();
        Iterable<StudentEntity> iterator = studentRepository.findAll();
        List<Student> studentEntities = new ArrayList<>();
        List<Student> studentsMongo = new ArrayList<>();
        for (StudentEntity i : iterator) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentEntities.add(student);
        }
        for (StudentMongo i : mongoStudent) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentsMongo.add(student);
        }


        for (Student s : student1) {
            if (s.getId() == stud.getId()) {
                isListDb = true;
                break;
            }
        }

        for (Student s : studentEntities) {
            if (s.getId() == stud.getId()) {
                isPostgres = true;
                break;
            }
        }

        for (Student s : redisStudent) {
            if (s.getId() == stud.getId()) {
                isRedis = true;
                break;
            }
        }
        for (Student s : studentsMongo) {
            if (s.getId() == stud.getId()) {
                isMongo = true;
            }
        }


        if (isListDb) {

            studentDb.updateStudent(stud);

        } else if (isPostgres) {
            studentRepository.save(new StudentEntity(stud.getId(), stud.getFname()));


        } else if (isRedis) {
            studentRedisRepository.addStudent(stud);

        } else if (isMongo) {
            studentMongoRepository.save(new StudentMongo(stud.getId(), stud.getFname()));
        }

        return stud;

    }

    @Override
    public void deleteStudent(Integer id) {

        boolean isRedis = false, isPostgres = false, isListDb = false, isMongo = false;

        List<Student> student1 = studentDb.getStudentList();
        List<Student> redisStudent = studentRedisRepository.findAll();
        Iterable<StudentMongo> mongoStudent = studentMongoRepository.findAll();
        Iterable<StudentEntity> iterator = studentRepository.findAll();
        List<Student> studentEntities = new ArrayList<>();
        List<Student> studentsMongo = new ArrayList<>();
        for (StudentEntity i : iterator) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentEntities.add(student);
        }
        for (StudentMongo i : mongoStudent) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getName());
            studentsMongo.add(student);
        }


        for (Student s : student1) {
            if (s.getId() == id) {
                isListDb = true;
                break;
            }
        }

        for (Student s : studentEntities) {
            if (s.getId() == id) {
                isPostgres = true;
                break;
            }
        }

        for (Student s : redisStudent) {
            if (s.getId() == id) {
                isRedis = true;
                break;
            }
        }
        for (Student s : studentsMongo) {
            if (s.getId() == id) {
                isMongo = true;
            }
        }

        if (isListDb) {

            studentDb.deleteStudent(id);

        } else if (isPostgres) {
            studentRepository.delete(new StudentEntity(id));


        } else if (isRedis) {
            studentRedisRepository.delete(id);

        } else if (isMongo) {

            studentMongoRepository.delete(new StudentMongo(id));
        }

    }

    @Override
    public void addStudentList(List<Student> s) {
        List<StudentEntity> studentEntities = new ArrayList<>();
        for (Student i : s) {
            studentEntities.add(new StudentEntity(i.getFname()));
        }
        studentRepository.saveAll(studentEntities);
    }


}
