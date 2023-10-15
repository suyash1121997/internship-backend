package com.internship.Internship.controller;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.model.InternshipModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public interface IAddInternshipController {

    @PostMapping("/addInternship")
    ResponseEntity<Integer> addInternship(@RequestBody Internship internship);

    @GetMapping("/getAllInternships")
    ResponseEntity<List<InternshipModel>> getAllInternship();
}
