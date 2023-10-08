package com.internship.Internship.controller;

import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.dto.SignupDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public interface ISignupController {
    @PostMapping("/signup")
     ResponseModel signup(@RequestBody @Valid  SignupDto signupDto);
}
