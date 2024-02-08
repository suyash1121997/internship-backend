package com.internship.Internship.service.impl;

import com.internship.Internship.constants.Status;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.UpdateInternship;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
    @Override
    public ResponseModel addInternshipInAccount(String email, List<String> id, Status status) throws InternshipException {
        List<InternshipModel> internshipModel = internshipRepository.findAllById(id);
        if(!internshipModel.isEmpty()) {
            internshipModel.forEach(e ->  e.setSeats(e.getSeats() - 1));
            internshipRepository.saveAll(internshipModel);
        }
        else {
            throw new InternshipException(404, "Internship does not exist");
        }
        List<StudentInternship> studentInternships = new ArrayList<>();
        internshipModel.forEach(e -> studentInternships.add(StudentInternship.builder()
                        .internshipId(e.getInternshipId())
                        .date(Date.from(Instant.now()))
                        .internshipList(e)
                        .studentEmail(email)
                        .status(String.valueOf(status))
                .build()));
        studentRepository.saveAll(studentInternships);
        return ResponseModel.builder().statusCode(200).message("You have successfully applied for internship")
                .build();
    }

    @Override
    public ResponseModel cancelInternship(String email, String id) throws InternshipException {
        Optional<StudentInternship> studentInternships = Optional.ofNullable(studentRepository.findByStudentEmailAndInternshipId(email, id));
        if(studentInternships.isPresent()) {
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
    public ResponseModel updateStatus(UpdateInternship updateInternship) {
        var byStudentEmailAndInternshipId = studentRepository.findByStudentEmailAndInternshipId(updateInternship.getStudentEmail(), updateInternship.getInternshipId());
        byStudentEmailAndInternshipId.setStatus(Status.valueOf(updateInternship.getStatus().toUpperCase()).name());
        studentRepository.save(byStudentEmailAndInternshipId);
        return ResponseModel.builder().message("Status updated successfully").build();
    }
}
