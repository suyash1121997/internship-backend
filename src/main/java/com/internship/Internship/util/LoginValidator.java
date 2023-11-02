package com.internship.Internship.util;

import com.internship.Internship.constants.LoginMode;
import com.internship.Internship.dto.Login;
import com.internship.Internship.dto.RegistrationModel;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.repository.ISignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Slf4j
public class LoginValidator {
    private static final String NOT_AUTHORIZED_ERR_MSG = "You are not authorized person to login with current mode. Please choose ";
    private static final String LOGIN_SUCCESSFUL_MSG = "Login Successful";
    private static final String PASSWORD_MISMATCH_ERR_MSG = "Password doesn't matched";
    private static final String SIGNUP_ERR_MSG = "User does not exist. Kindly signup";

    public static ResponseModel validateUser(Login login, ISignUpRepository signUpRepository, PasswordEncoder passwordEncoder) {
        Optional<RegistrationModel> loginDetails = signUpRepository.findById(login.getEmail());

        if (loginDetails.isPresent()) {
            if (!loginDetails.get().getLoginMode().equals(login.getLoginMode())) {
                return ResponseModel.builder().message(NOT_AUTHORIZED_ERR_MSG
                                + loginDetails.get().getLoginMode())
                        .statusCode(400).isUserExist(true).build();
            }
            if (passwordEncoder.matches(login.getPassword(), loginDetails.get().getPassword())) {
                return ResponseModel.builder().message(LOGIN_SUCCESSFUL_MSG).statusCode(HttpStatus.OK.value()).
                        isUserExist(true).build();
            } else {
                return ResponseModel.builder().message(PASSWORD_MISMATCH_ERR_MSG).statusCode(HttpStatus.BAD_REQUEST.value())
                        .isUserExist(true).build();
            }
        } else {
            return ResponseModel.builder().message(SIGNUP_ERR_MSG).statusCode(HttpStatus.NOT_FOUND.value())
                    .isUserExist(false).build();
        }
    }
    public static void validateUserWithProfile(ISignUpRepository signUpRepository, String email, LoginMode loginMode) throws InternshipException {
        Optional<RegistrationModel> registrationModel = signUpRepository.findById(email);
        if(registrationModel.isPresent()){
            if(!registrationModel.get().getLoginMode().equals(loginMode)) {
                throw new InternshipException(400, "User is not valid to perform this operation");
            }
            return;
        }
        throw new InternshipException(404, "User does not exists");

    }
}
