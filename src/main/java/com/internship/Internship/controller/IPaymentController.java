package com.internship.Internship.controller;

import com.internship.Internship.dto.PaymentModelDto;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:4200/")
public interface IPaymentController {
    @PostMapping("/makePayment")
    ResponseModel validatePayment(@RequestBody  PaymentModelDto paymentModelDto) throws InternshipException, ParseException;

}
