package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {

    private static long counter = 0;
    ConcurrentHashMap<Long, Student> studentHashMap = new ConcurrentHashMap<>();
    Set<String> studentEmails = ConcurrentHashMap.newKeySet();

    private Long generateId(){
        return counter ++;
    }

    private boolean isStudentExistInDb(String email){

        if (studentEmails.contains(email)){
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
        studentEmails.add(student.getEmail());
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
        if (!isStudentExistInDb(id) || isStudentExistInDb(student.getEmail())){
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
        studentEmails.remove(studentHashMap.get(id).getEmail());
        studentHashMap.remove(id);
        return true;
    }
}
