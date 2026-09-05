package com.kaustubh.studentmanagementapi.service;

import com.kaustubh.studentmanagementapi.dto.StudentRequestDTO;
import com.kaustubh.studentmanagementapi.dto.StudentResponseDTO;
import com.kaustubh.studentmanagementapi.entity.Student;
import com.kaustubh.studentmanagementapi.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.kaustubh.studentmanagementapi.exception.StudentNotFoundException;
import com.kaustubh.studentmanagementapi.dto.StudentRequestDTO;

import java.util.List;
@Service
public class StudentService {

        private final StudentRepository studentRepository;

        public StudentService(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        public List<Student> getAllStudents(){
            return studentRepository.findAll();
        }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public Student updateStudent(Long id, StudentRequestDTO studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
            Student student = studentRepository.findById(id)
        .orElseThrow(() -> new  StudentNotFoundException("Student not found"));

        studentRepository.delete(student);
    }

        public Student createStudent(StudentRequestDTO studentRequest){

            Student student = new Student();

            student.setName(studentRequest.getName());
            student.setEmail(studentRequest.getEmail());

            return studentRepository.save(student);
        }

}
