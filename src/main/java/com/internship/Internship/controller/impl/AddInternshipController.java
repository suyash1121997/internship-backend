package com.internship.Internship.controller.impl;

import com.internship.Internship.constants.Status;
import com.internship.Internship.controller.IAddInternshipController;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.StudentInternship;
import com.internship.Internship.model.compositekeys.StudentInternShipCompositeKey;
import com.internship.Internship.model.compositekeys.StudentInternshipHistoryCompositeKey;
import com.internship.Internship.repository.IStudentInternshipHistory;
import com.internship.Internship.repository.IStudentRepository;
import com.internship.Internship.service.IAddInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class AddInternshipController implements IAddInternshipController {
    @Autowired
    IAddInternshipService addInternshipService;
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IStudentInternshipHistory studentInternshipHistory;

    @Override
    public ResponseEntity<ResponseModel<Internship>> addInternship(Internship internship) throws InternshipException {
        return new ResponseEntity<>(addInternshipService.addInternship(internship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseModel<List<Internship>>> getAllInternship (String studentEmail) {
        List<Internship> getAllInternship = addInternshipService.getAllInternship(studentEmail);

        var all = studentRepository.findByStudentInternShipCompositeKeyStudentEmail(studentEmail);
                List<String> ids = all.stream()
                        .filter(e->
                            studentInternshipHistory.findByStudentInternshipHistoryCompositeKeyAndStudentInternshipHistoryCompositeKeyStatusNotInOrderByCreatedDateDesc(
                                    StudentInternshipHistoryCompositeKey.builder().studentEmail(studentEmail).internshipId(e.getInternshipList().getInternshipId())
                                            .build(), Arrays.asList(Status.PENDING_FOR_APPROVAL, Status.ACCEPTED, Status.REJECTED)).isEmpty()).
                map(StudentInternship::getStudentInternShipCompositeKey).map(StudentInternShipCompositeKey::getInternshipId).toList();
        getAllInternship = getAllInternship.stream().filter(e -> !ids.contains(e.getId())).toList();
        ResponseModel<List<Internship>> responseModel = ResponseModel.<List<Internship>>builder()
                .details(getAllInternship).statusCode(200).message("Internships fetched successfully")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
