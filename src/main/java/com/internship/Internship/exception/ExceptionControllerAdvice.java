package com.internship.Internship.exception;

import com.internship.Internship.dto.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {

        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.badRequest().body(ex.getBindingResult().getFieldError().getField() + " " + errorMessage);
    }
    @ExceptionHandler(InternshipException.class)
    public ResponseEntity<ResponseModel> internshipException(InternshipException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseModel.builder().statusCode(ex.getErrorCode()).message(ex.getMessage()).isUserExist(ex.isUserExists()).build(),
                HttpStatus.valueOf(ex.getErrorCode()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> exception(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseModel.builder().statusCode(500).message(ex.getMessage())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
