package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.ILoginController;
import com.internship.Internship.dto.Login;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController implements ILoginController {
    @Autowired
    private ILoginService loginService;
    @Override
    public ResponseModel login(Login login) {
        return loginService.login(login);
    }
}
