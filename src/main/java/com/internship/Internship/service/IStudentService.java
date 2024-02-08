package com.internship.Internship.service;

import com.internship.Internship.constants.Status;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.UpdateInternship;
import com.internship.Internship.exception.InternshipException;

import java.util.List;

public interface IStudentService {
    ResponseModel addInternshipInAccount(String email, List<String> id, Status pendingForApproval) throws InternshipException;

    ResponseModel cancelInternship(String email, String id) throws InternshipException;

    ResponseModel updateStatus(UpdateInternship updateInternship);
}
