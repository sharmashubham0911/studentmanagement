package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private static final AtomicLong counter = new AtomicLong(0);
    ConcurrentHashMap<Long, Student> studentHashMap = new ConcurrentHashMap<>();

    private Long generateId(){
        return counter.getAndIncrement();
    }

    private boolean isStudentExistInDb(String email){

        for (Student student: studentHashMap.values()){
            if (student.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    private boolean isStudentExistInDbForId(String email, Long id){

        Student student = studentHashMap.get(id);
        if (student.getEmail().equals(email)){
            return true;
        }
        return false;
    }

    private boolean isStudentExistInDb(Long id){

        if (studentHashMap.containsKey(id)){
            return true;
        }
        return false;
    }

    public boolean createStudent(Student student){
        if (isStudentExistInDb(student.getEmail())){
            return false;
        }
        Long id = generateId();
        student.setId(id);
        studentHashMap.put(id, student);
        return true;
    }

    public Student getStudent(Long id){
        if (!isStudentExistInDb(id)){
            return null;
        }
        return studentHashMap.get(id);
    }

    public List<Student> getAllStudent(){

        return new ArrayList<>(studentHashMap.values());
    }

    public boolean updateStudent(Long id, Student student){
        if (!isStudentExistInDb(id)){
            return false;
        }
        if (isStudentExistInDb(student.getEmail()) && !isStudentExistInDbForId(student.getEmail(), id)){
            return false;
        }
        student.setId(id);
        studentHashMap.put(id, student);
        return true;
    }

    public boolean deleteStudent(Long id){
        if (!isStudentExistInDb(id)){
            return false;
        }
        studentHashMap.remove(id);
        return true;
    }
}
