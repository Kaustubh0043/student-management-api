package com.kaustubh.studentmanagementapi.controller;


import com.kaustubh.studentmanagementapi.dto.StudentRequestDTO;
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
import com.kaustubh.studentmanagementapi.dto.StudentRequestDTO;
import com.kaustubh.studentmanagementapi.dto.StudentResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        List<StudentResponseDTO> responseDTOS = students.stream()
            .map(student -> new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail()
        ))
                .toList();

        return new ApiResponse<>(
                200,
                "Students fetched successfully",
                responseDTOS
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<StudentResponseDTO> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail()
        );

        return new ApiResponse<>(
                200,
                "Student fetched successfully",
                responseDTO
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(
            @Valid @RequestBody StudentRequestDTO studentRequest) {

        Student createdStudent = studentService.createStudent(studentRequest);

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                createdStudent.getId(),
                createdStudent.getName(),
                createdStudent.getEmail()
        );


        ApiResponse<StudentResponseDTO> response = new ApiResponse<>(
                201,
                "Student created successfully",
                responseDTO
    );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentRequest){

        Student updatedStudent = studentService.updateStudent(id, studentRequest);

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail()
        );

        ApiResponse<StudentResponseDTO> response = new ApiResponse<>(
                200,
                "Student updated successfully",
                responseDTO
    );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

