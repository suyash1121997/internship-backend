package com.internship.Internship.service;

import com.internship.Internship.dto.MentorStudentDto;

public interface IMentorStudentService {
    MentorStudentDto getAllRequests(String mentorEmail);
}
