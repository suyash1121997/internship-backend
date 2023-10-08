package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.ISignupController;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.SignupDto;
import com.internship.Internship.service.ISignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController implements ISignupController {

    @Autowired
    ISignupService signupService;
    @Override
    public ResponseModel signup(SignupDto signupDto) {
        ResponseModel responseModel = signupService.signUp(signupDto);
        return responseModel;
    }
}
