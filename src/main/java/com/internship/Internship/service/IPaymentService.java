package com.internship.Internship.service;

import com.internship.Internship.dto.PaymentModelDto;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;

import java.text.ParseException;

public interface IPaymentService {
    ResponseModel validatePayment(PaymentModelDto paymentModelDto) throws ParseException, InternshipException;
}
