package com.internship.Internship.service.impl;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;
@Autowired
    IAddInternshipRepository internshipRepository;
    @Override
    public ResponseModel addInternshipInAccount(String email, String id) throws InternshipException {
        Optional<InternshipModel> internshipModel = internshipRepository.findById(id);
        if(internshipModel.isPresent()) {
            int seats = internshipModel.get().getSeats();
            internshipModel.get().setSeats(seats-1);
            internshipRepository.save(internshipModel.get());
        }
        else {
            throw new InternshipException(404, "Internship does not exist");
        }
        StudentInternship studentInternship = StudentInternship.builder().internshipId(id)
                .internshipList(internshipModel.get()).studentEmail(email).build();
        studentRepository.save(studentInternship);
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
}
