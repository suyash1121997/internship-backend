package com.internship.Internship.exception;

import com.internship.Internship.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.badRequest().body(ex.getBindingResult().getFieldError().getField() + " " + errorMessage);
    }
    @ExceptionHandler(InternshipException.class)
    public ResponseEntity<ResponseModel> InternshipException(InternshipException ex) {
        return new ResponseEntity<>( ResponseModel.builder().statusCode(ex.getErrorCode()).message(ex.getMessage()).isUserExist(ex.isUserExists()).build(),
                HttpStatusCode.valueOf(ex.getErrorCode()));
    }
}
