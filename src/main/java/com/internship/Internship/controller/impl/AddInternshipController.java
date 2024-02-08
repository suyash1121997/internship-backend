package com.internship.Internship.controller.impl;

import com.internship.Internship.constants.Status;
import com.internship.Internship.controller.IAddInternshipController;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IAddInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AddInternshipController implements IAddInternshipController {
    @Autowired
    IAddInternshipService addInternshipService;
    @Autowired
    IStudentRepository studentRepository;

    @Override
    public ResponseEntity<ResponseModel<Internship>> addInternship(Internship internship) throws InternshipException {
        return new ResponseEntity<>(addInternshipService.addInternship(internship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseModel<List<Internship>>> getAllInternship (String studentEmail) {
        List<Internship> getAllInternship = addInternshipService.getAllInternship(studentEmail);
        var all = studentRepository.findAllByStudentEmail(studentEmail);
        List<String> ids = all.stream().filter(e -> Status.PENDING_FOR_APPROVAL.name().equals(e.getStatus())).
                map(StudentInternship::getInternshipId).toList();
        getAllInternship = getAllInternship.stream().filter(e -> !ids.contains(e.getId())).toList();
        ResponseModel<List<Internship>> responseModel = ResponseModel.<List<Internship>>builder()
                .details(getAllInternship).statusCode(200).message("Internships fetched successfully")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
