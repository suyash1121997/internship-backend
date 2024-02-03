package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IMentorController;
import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.MentorStudentModel;
import com.internship.Internship.service.IMentorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentorStudentController implements IMentorController {
    @Autowired
    IMentorStudentService mentorStudentService;
    @Override
    public ResponseEntity<MentorStudentDto> getAllInternships(String mentorEmail) {
        return new ResponseEntity<>(mentorStudentService.getAllRequests(mentorEmail), HttpStatus.OK);
    }
}
