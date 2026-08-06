package com.studentmanagement.controller.student;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        boolean isStudentCreated = studentService.createStudent(student);
        if (isStudentCreated){
            return ResponseEntity.ok("Student created successfully");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student student = studentService.getStudent(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student){
        boolean isStudentUpdated = studentService.updateStudent(id, student);
        if (isStudentUpdated){
            return ResponseEntity.ok("Student with given id " + id + " is updated sucesfully");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean isStudentDeleted = studentService.deleteStudent(id);
        if (isStudentDeleted){
           return ResponseEntity.ok("Student with id: " + id + " deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
