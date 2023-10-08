package com.internship.Internship.service.impl;

import com.internship.Internship.dto.RegistrationModel;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.SignupDto;
import com.internship.Internship.repository.ISignUpRepository;
import com.internship.Internship.service.ISignupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SignupServiceImpl implements ISignupService {
    @Autowired
    private ISignUpRepository signUpRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseModel signUp(SignupDto signupDto) {
        System.out.println(signupDto.getLoginMode());
        boolean isUserExist = isUserExists(signupDto);
        if(isUserExist) {
            return  ResponseModel
                    .builder()
                    .isUserExist(true)
                    .statusCode(200)
                    .message("User already exist. Kindly proceed for login")
                    .build();
        }
        String password = passwordEncoder.encode(signupDto.getPassword());
        RegistrationModel registrationModel = RegistrationModel
                .builder().email(signupDto.getEmail()).firstName(signupDto.getFirstName()).middleName(signupDto.getMiddleName())
                        .familyName(signupDto.getFamilyName()).password(password).loginMode(signupDto.getLoginMode()).build();
        signUpRepository.save(registrationModel);

        return ResponseModel
                .builder().statusCode(200).message("Registration is successful. Kindly proceed for login")
                .isUserExist(false).build();
    }

    private boolean isUserExists(SignupDto signupDto) {
       Optional<RegistrationModel> userDetails = signUpRepository.findById(signupDto.getEmail());
       return  userDetails.isPresent();
    }
}
