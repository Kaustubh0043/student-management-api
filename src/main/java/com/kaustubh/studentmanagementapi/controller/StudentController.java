package com.kaustubh.studentmanagementapi.controller;


import com.kaustubh.studentmanagementapi.entity.Student;
import com.kaustubh.studentmanagementapi.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


        private final StudentService studentService;

        public StudentController(StudentService studentService){
            this.studentService = studentService;
        }

        @GetMapping
        public List<Student> getAllStudents(){
            return studentService.getAllStudents();
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student){
            return studentService.createStudent(student);
        }
}
