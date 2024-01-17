package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IMentorController;
import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.MentorStudentModel;
import com.internship.Internship.service.IMentorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class MentorStudentController implements IMentorController {
    @Autowired
    IMentorStudentService mentorStudentService;
    @Override
    public ResponseEntity<MentorStudentDto> getAllInternships(String mentorEmail) {
        return new ResponseEntity<MentorStudentDto>(mentorStudentService.getAllRequests(mentorEmail), HttpStatus.OK);
    }
}
