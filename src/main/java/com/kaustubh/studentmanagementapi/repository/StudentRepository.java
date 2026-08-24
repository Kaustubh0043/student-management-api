package com.kaustubh.studentmanagementapi.repository;

import com.kaustubh.studentmanagementapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}