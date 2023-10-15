package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IAddInternshipController;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.service.IAddInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AddInternshipController implements IAddInternshipController {
    @Autowired
    IAddInternshipService addInternshipService;

    @Override
    public ResponseEntity<Integer> addInternship(Internship internship) {
        return new ResponseEntity<>(HttpStatus.valueOf(addInternshipService.addInternship(internship)));
    }

    @Override
    public ResponseEntity<List<InternshipModel>> getAllInternship() {
        List<InternshipModel> getAllInternship = addInternshipService.getAllInternship();
        return null;
    }
}
