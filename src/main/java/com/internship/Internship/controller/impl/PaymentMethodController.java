package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IPaymentController;
import com.internship.Internship.dto.PaymentModelDto;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j
public class PaymentMethodController implements IPaymentController {
    @Autowired
    IPaymentService paymentService;
    @Override
    public ResponseModel validatePayment(PaymentModelDto paymentModelDto) throws InternshipException, ParseException {
        log.info("cardNumber" + paymentModelDto.getCardNumber());
        log.info("date" + paymentModelDto.getExpiryDate());
        log.info("cvv" + paymentModelDto.getCvv());
        log.info("pay" + paymentModelDto.getAmount());
        log.info("name" +paymentModelDto.getName());
        log.info("internshipId" +paymentModelDto.getInternshipId());
        log.info("email" +paymentModelDto.getEmail());


        return paymentService.validatePayment(paymentModelDto);
    }
}
