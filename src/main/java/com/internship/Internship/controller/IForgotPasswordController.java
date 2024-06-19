package com.internship.Internship.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "*")
public interface IForgotPasswordController {
    @GetMapping("/reset")
    ResponseEntity<String> resetPassword(@RequestParam String email) throws MessagingException;
}
