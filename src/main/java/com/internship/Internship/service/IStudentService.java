package com.internship.Internship.service;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;

public interface IStudentService {
    ResponseModel addInternshipInAccount(String email, String id) throws InternshipException;

    ResponseModel cancelInternship(String email, String id) throws InternshipException;
}
