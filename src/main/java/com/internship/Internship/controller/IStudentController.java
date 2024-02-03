package com.internship.Internship.controller;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public interface IStudentController {
    @PostMapping("/addToStudent")
    ResponseEntity<ResponseModel> addInternshipInAccount(@RequestParam String email, @RequestParam List<String> id) throws InternshipException;

    @PostMapping("/cancelInternship")
    ResponseEntity<ResponseModel> cancelInternship(@RequestParam String email, @RequestParam String id) throws InternshipException;
}
