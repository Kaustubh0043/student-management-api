package com.kaustubh.studentmanagementapi.service;

import com.kaustubh.studentmanagementapi.entity.Student;
import com.kaustubh.studentmanagementapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

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

        public Student createStudent(Student student){
            return studentRepository.save(student);
        }
}
