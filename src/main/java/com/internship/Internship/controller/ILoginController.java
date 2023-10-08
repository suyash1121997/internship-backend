package com.internship.Internship.controller;

import com.internship.Internship.dto.Login;
import com.internship.Internship.dto.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public interface ILoginController {

    @PostMapping("/login")
     ResponseModel login(@Valid @RequestBody Login login);
}
