package com.internship.Internship.controller;

import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.MentorStudentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CrossOrigin(origins = "http://localhost:4200/")
public interface IMentorController {

    @GetMapping("/getAllInternshipRequests")
    ResponseEntity<MentorStudentDto> getAllInternships(@RequestParam String mentorEmail);
}
