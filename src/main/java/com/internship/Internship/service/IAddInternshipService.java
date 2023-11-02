package com.internship.Internship.service;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;

import java.util.List;

public interface IAddInternshipService {

    ResponseModel<Internship> addInternship(Internship internship) throws InternshipException;

    List<Internship> getAllInternship();
}
