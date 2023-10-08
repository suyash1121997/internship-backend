package com.internship.Internship.service;

import com.internship.Internship.dto.Login;
import com.internship.Internship.dto.ResponseModel;

public interface ILoginService {
    ResponseModel login(Login login);
}
