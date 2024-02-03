package com.internship.Internship.service.impl;

import com.internship.Internship.dto.InternshipDetails;
import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.model.MentorStudentModel;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IMentorRepository;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IMentorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class MentorStudentServiceImpl implements IMentorStudentService {
    @Autowired
    IMentorRepository mentorRepository;
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IAddInternshipRepository addInternshipRepository;

    @Override
    public MentorStudentDto getAllRequests(String mentorEmail) {
        List<InternshipDetails> internshipDetails = new ArrayList<>();
        List<MentorStudentModel> internshipList = mentorRepository.findAllByMentorEmail(mentorEmail);
        List<String> listOfInternshipIds = internshipList.stream().map(MentorStudentModel::getInternshipId).toList();

        for (String internshipId: listOfInternshipIds) {
            var byId = addInternshipRepository.findById(internshipId);
            InternshipDetails internshipDetails1 = InternshipDetails.builder()
                    .internshipId(internshipId)
                    .internshipTitle(byId.map(InternshipModel::getTitle).orElse(null))
                    .studentEmails(getStudentEmailsBasedOnInternshipId(internshipId))
                    .build();
            internshipDetails.add(internshipDetails1);
        }
        return  MentorStudentDto.builder().internshipDetails(internshipDetails)
                .build();
    }

    private List<String> getStudentEmailsBasedOnInternshipId(String internshipId) {
         return studentRepository.findByInternshipId(internshipId).stream().map(StudentInternship::getStudentEmail)
                 .toList();
    }
}
