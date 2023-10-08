package com.internship.Internship.service.impl;

import com.internship.Internship.dto.Login;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.repository.ISignUpRepository;
import com.internship.Internship.service.ILoginService;
import com.internship.Internship.util.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private ISignUpRepository signUpRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ResponseModel login(Login login) {
       return LoginValidator.validateUser(login, signUpRepository, passwordEncoder);
    }
}
