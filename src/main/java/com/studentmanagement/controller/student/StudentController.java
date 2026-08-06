package com.studentmanagement.controller.student;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    StudentService studentService = new StudentService();

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/updatedStudent/{id}", method = RequestMethod.PUT)
    public String updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(Long id){
        return studentService.deleteStudent(id);
    }
}
