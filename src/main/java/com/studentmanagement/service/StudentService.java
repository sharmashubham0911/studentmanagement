package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentService {

    private static long counter = 0;
    HashMap<String, Student> studentHashMap = new HashMap<>();

    private boolean isStudentExistInDb(String email){

        if (studentHashMap.containsKey(email)){
            return true;
        }
        return false;
    }

    public String createStudent(Student student){
        if (isStudentExistInDb(student.getEmail())){
            return "Duplicate Student";
        }
        studentHashMap.put(student.getEmail(), student);
        return "Student created succesfully";
    }

    public Student getStudent(String email){
        if (!isStudentExistInDb(email)){
            return new Student();
        }
        return studentHashMap.get(email);
    }

    public List<Student> getAllStudent(){

        return new ArrayList<>(studentHashMap.values());
    }

    public String updateStudent(String email, Student student){
        if (!isStudentExistInDb(email)){
            return "Student with given id: " + email + " does not exist in the db";
        }
        studentHashMap.put(email, student);
        return "Student with given id: " + email + " has been updated succesfully";
    }

    public String deleteStudent(String email){
        if (!isStudentExistInDb(email)){
            return "Student with given id: " + email + " does not exist in the db";
        }
        studentHashMap.remove(email);
        return "Student with given id: " + email + " has been removed succesfully";
    }
}
