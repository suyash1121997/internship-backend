package com.internship.Internship.controller;

import com.internship.Internship.constants.Status;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "*")
public interface IAddInternshipController{
    @PostMapping("/addInternship")
    ResponseEntity<ResponseModel<Internship>> addInternship(@RequestBody Internship internship) throws InternshipException;

    @GetMapping("/getAllInternships")
    ResponseEntity<ResponseModel<List<Internship>>> getAllInternship(@RequestParam(name = "studentEmail") String email);
}
