package com.internship.Internship.service.impl;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.MentorStudentModel;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IMentorRepository;
import com.internship.Internship.service.IMentorStudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MentorStudentServiceImpl implements IMentorStudentService {
    @Autowired
    IMentorRepository mentorRepository;
    @Override
    public MentorStudentDto getAllRequests(String mentorEmail) {
        List<MentorStudentModel> internshipList = mentorRepository.findAllByMentorEmail(mentorEmail);
        MentorStudentDto mentorStudentDto = MentorStudentDto.builder()
                .mentorEmail(mentorEmail)
                .build();
        return null;
    }
}
