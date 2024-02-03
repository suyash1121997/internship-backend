package com.internship.Internship.service;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;

import java.util.List;

public interface IStudentService {
    ResponseModel addInternshipInAccount(String email, List<String> id) throws InternshipException;

    ResponseModel cancelInternship(String email, String id) throws InternshipException;
}
