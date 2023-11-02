package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IStudentController;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController implements IStudentController {
    @Autowired
    IStudentService studentService;
    @Override
    public ResponseEntity<ResponseModel> addInternshipInAccount(String email, String id) throws InternshipException {
        return new ResponseEntity<>(studentService.addInternshipInAccount(email, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseModel> cancelInternship(String email, String id) throws InternshipException {
        return new ResponseEntity<>(studentService.cancelInternship(email, id), HttpStatus.OK);
    }
}
