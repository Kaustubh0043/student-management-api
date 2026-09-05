package com.kaustubh.studentmanagementapi.controller;


import com.kaustubh.studentmanagementapi.entity.Student;
import com.kaustubh.studentmanagementapi.response.ApiResponse;
import com.kaustubh.studentmanagementapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.kaustubh.studentmanagementapi.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ApiResponse<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        return new ApiResponse<>(
                200,
                "Students fetched successfully",
                students
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<Student> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        return new ApiResponse<>(
                400,
                "Student fetched successfully",
                student
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(
            @Valid @RequestBody Student student) {

        Student createdStudent = studentService.createStudent(student);

        ApiResponse<Student> response = new ApiResponse<>(
                201,
                "Student created successfully",
                createdStudent
    );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student studentdetails){
        Student updatedStudent = studentService.updateStudent(id, studentdetails);

        ApiResponse<Student> response = new ApiResponse<>(
                200,
                "Student updated successfully",
                updatedStudent
    );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

