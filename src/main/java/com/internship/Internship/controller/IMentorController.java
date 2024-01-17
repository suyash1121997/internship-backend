package com.internship.Internship.controller;

import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.MentorStudentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMentorController {


    @GetMapping("/getAllInternshipRequests")
    ResponseEntity<MentorStudentDto> getAllInternships(@RequestParam String mentorEmail);
}
