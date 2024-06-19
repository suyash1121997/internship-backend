package com.internship.Internship.service.impl;

import com.internship.Internship.constants.Status;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.UpdateInternship;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.model.StudentInternshipHistory;
import com.internship.Internship.model.compositekeys.StudentInternShipCompositeKey;
import com.internship.Internship.model.compositekeys.StudentInternshipHistoryCompositeKey;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IStudentInternshipHistory;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IAddInternshipRepository internshipRepository;
    @Autowired
    IStudentInternshipHistory studentInternshipHistory;

    @Override
    public ResponseModel<?> addInternshipInAccount(String email, List<String> id, Status status) throws InternshipException {
        List<InternshipModel> internshipModel = internshipRepository.findAllById(id);
        List<StudentInternshipHistory> studentInternshipHistoryList = new ArrayList<>();
        if (!internshipModel.isEmpty()) {
            internshipModel.forEach(e -> e.setSeats(e.getSeats() - 1));
            internshipRepository.saveAll(internshipModel);
        } else {
            throw new InternshipException(404, "Internship does not exist");
        }
        List<StudentInternship> studentInternships = new ArrayList<>();
        internshipModel.forEach(e ->
                studentInternships.add(StudentInternship.builder()
                .studentInternShipCompositeKey(StudentInternShipCompositeKey.builder()
                        .internshipId(e.getInternshipId())
                        .studentEmail(email)
                        .build())
                .date(Date.from(Instant.now()))
                .internshipList(e)
                .build()));
        id.forEach(e-> {
           studentInternshipHistoryList.add(StudentInternshipHistory.builder()
                           .studentInternshipHistoryCompositeKey(
                                   StudentInternshipHistoryCompositeKey
                                           .builder().status(Status.PENDING_FOR_APPROVAL.name())
                                           .internshipId(e)
                                           .studentEmail(email).build()
                           )
                    .createdDate(LocalDateTime.now()).build());
        });
        studentRepository.saveAll(studentInternships);
        studentInternshipHistory.saveAll(studentInternshipHistoryList);
        return ResponseModel.builder().statusCode(200).message("You have successfully applied for internship")
                .build();
    }

    @Override
    public ResponseModel<?> cancelInternship(String email, String id) throws InternshipException {
        Optional<StudentInternship> studentInternships = Optional.ofNullable(studentRepository.findByStudentInternShipCompositeKey(
                StudentInternShipCompositeKey.builder().studentEmail(email).internshipId(id).build()));
        if (studentInternships.isPresent()) {
            studentRepository.delete(studentInternships.get());
            Optional<InternshipModel> internshipModel = internshipRepository.findById(id);
            if (internshipModel.isPresent()) {
                int seats = internshipModel.get().getSeats();
                internshipModel.get().setSeats(seats + 1);
                internshipRepository.save(internshipModel.get());
            } else {
                throw new InternshipException(404, "Internship does not exist");
            }
            return ResponseModel.builder().statusCode(200).message("You have successfully applied for internship")
                    .build();
        }
        throw new InternshipException(404, "Student does not exist");
    }

    @Override
    public ResponseModel<?> updateStatus(UpdateInternship updateInternship) {
        String status = Status.getValue(updateInternship.getStatus());
        var optionalStudentHistory = studentInternshipHistory.findByStudentInternshipHistoryCompositeKeyStudentEmailAndStudentInternshipHistoryCompositeKeyInternshipId(
               updateInternship.getStudentEmail(), updateInternship.getInternshipId());
              optionalStudentHistory.ifPresent((e)-> {
                    e.setCreatedDate(LocalDateTime.now());
                    e.getStudentInternshipHistoryCompositeKey().setStatus(status);
                  studentInternshipHistory.save(e.clone());
              });
        return ResponseModel.builder().message("Status updated successfully").build();
    }
}
