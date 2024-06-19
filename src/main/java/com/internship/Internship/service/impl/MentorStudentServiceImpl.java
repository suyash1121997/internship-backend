package com.internship.Internship.service.impl;

import com.internship.Internship.constants.Status;
import com.internship.Internship.dto.InternshipDetails;
import com.internship.Internship.dto.MentorStudentDto;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.model.MentorStudentModel;
import com.internship.Internship.model.StudentInternshipHistory;
import com.internship.Internship.model.compositekeys.StudentInternshipHistoryCompositeKey;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IMentorRepository;
import com.internship.Internship.repository.IStudentInternshipHistory;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IMentorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MentorStudentServiceImpl implements IMentorStudentService {

    private final IMentorRepository mentorRepository;

    private final IStudentRepository studentRepository;

    private final IAddInternshipRepository addInternshipRepository;
    private final IStudentInternshipHistory studentInternshipHistory;

    @Autowired
    public MentorStudentServiceImpl(IMentorRepository mentorRepository, IStudentRepository studentRepository,
                                    IAddInternshipRepository internshipRepository, final IStudentInternshipHistory studentInternshipHistory) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.addInternshipRepository = internshipRepository;
        this.studentInternshipHistory = studentInternshipHistory;
    }

    @Override
    public MentorStudentDto getAllRequests(String mentorEmail) {
        List<InternshipDetails> internshipDetails = new ArrayList<>();
        List<MentorStudentModel> internshipList = mentorRepository.findAllByMentorEmail(mentorEmail);
        List<String> listOfInternshipIds = internshipList.stream().map(MentorStudentModel::getInternshipId).toList();

        for (String internshipId : listOfInternshipIds) {
            var byId = addInternshipRepository.findById(internshipId);
            InternshipDetails internshipDetails1 = InternshipDetails.builder()
                    .internshipId(internshipId)
                    .internshipTitle(byId.map(InternshipModel::getTitle).orElse(null))
                    .studentEmails(getStudentEmailsBasedOnInternshipId(internshipId))
                    .build();
            internshipDetails.add(internshipDetails1);
        }
        return MentorStudentDto.builder().internshipDetails(internshipDetails)
                .build();
    }

    private List<String> getStudentEmailsBasedOnInternshipId(String internshipId) {
        return studentInternshipHistory.findByStudentInternshipHistoryCompositeKeyInternshipIdAndStudentInternshipHistoryCompositeKeyStatus(internshipId, Status.PENDING_FOR_APPROVAL.name()).stream()
                .map(StudentInternshipHistory::getStudentInternshipHistoryCompositeKey)
                .map(StudentInternshipHistoryCompositeKey::getStudentEmail)
                .toList();
    }
}
