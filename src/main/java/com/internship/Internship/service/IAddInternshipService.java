package com.internship.Internship.service;

import com.internship.Internship.dto.Internship;
import com.internship.Internship.model.InternshipModel;

import java.util.List;

public interface IAddInternshipService {

    Integer addInternship(Internship internship);

    List<InternshipModel> getAllInternship();
}
