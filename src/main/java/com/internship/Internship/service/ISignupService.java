package com.internship.Internship.service;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.SignupDto;

public interface ISignupService {
    ResponseModel signUp(SignupDto signupDto);
}
