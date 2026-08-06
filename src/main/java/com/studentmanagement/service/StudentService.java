package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import java.util.HashMap;

public class StudentService {

    private static long counter = 0;
    HashMap<Long, Student> studentHashMap = new HashMap<>();

    private long generateId(){
        counter ++;
        return counter;
    }

    public String createStudent(Student student){
        Long id = generateId();
        studentHashMap.put(id, student);
        return "Student created succesfully";
    }

    public Student getStudent(Long id){
        if (!studentHashMap.containsKey(id)){
            return new Student();
        }
        return studentHashMap.get(id);
    }

    public String updateStudent(Long id, Student student){
        if (!studentHashMap.containsKey(id)){
            return "Student with given id: " + id + " does not exist in the db";
        }
        studentHashMap.put(id, student);
        return "Student with given id: " + id + " has been updated succesfully";
    }

    public String deleteStudent(Long id){
        if (!studentHashMap.containsKey(id)){
            return "Student with given id: " + id + " does not exist in the db";
        }
        studentHashMap.remove(id);
        return "Student with given id: " + id + " has been removed succesfully";
    }
}
