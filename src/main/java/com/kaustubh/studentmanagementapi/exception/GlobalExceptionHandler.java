package com.kaustubh.studentmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    404,
                    ex.getMessage()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

}
