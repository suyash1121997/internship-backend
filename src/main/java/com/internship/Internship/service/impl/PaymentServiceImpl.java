package com.internship.Internship.service.impl;

import com.internship.Internship.dto.PaymentModelDto;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.BankPaymentModel;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.model.PaymentMethod;
import com.internship.Internship.repository.IBankPaymentValidatorRepository;
import com.internship.Internship.repository.IPaymentRepository;
import com.internship.Internship.service.IPaymentService;
import com.internship.Internship.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    IPaymentRepository paymentRepository;
    @Autowired
    IBankPaymentValidatorRepository bankPaymentValidatorRepository;
    @Autowired
    IStudentService studentService;
    @Override
    public ResponseModel validatePayment(PaymentModelDto paymentModelDto) throws ParseException, InternshipException {
        Optional<PaymentMethod> paymentMethod = paymentRepository.findById(paymentModelDto.getCardNumber());
        if (paymentMethod.isPresent()) {
            PaymentMethod paymentMethod1 = paymentMethod.get();
            boolean isDateValid = isDateValid(paymentMethod1.getDate(), paymentModelDto.getDate());
            if (isDateValid && paymentMethod1.getCvv() == paymentModelDto.getCvv()) {
                validateAndDeductPayment(paymentModelDto);
                ResponseModel responseModel = studentService.addInternshipInAccount(paymentModelDto.getEmail(), paymentModelDto.getInternshipId());
                return ResponseModel.builder().message("Payment processed successfully" + "." + responseModel.getMessage())
                        .statusCode(200).build();
            }
        }
        throw new InternshipException(400, "Provided card details are not valid");
    }

    private void validateAndDeductPayment(PaymentModelDto paymentModelDto) throws InternshipException {
        Optional<BankPaymentModel> bankPaymentModel = bankPaymentValidatorRepository.findById(paymentModelDto.getCardNumber());
        if (bankPaymentModel.isPresent()) {
            BankPaymentModel bankData = bankPaymentModel.get();
            long balance = bankData.getBalance();
            if(balance>=paymentModelDto.getAmount()) {
                bankData.setBalance(balance - paymentModelDto.getAmount());
                bankPaymentValidatorRepository.save(bankData);
            }
            else {
                throw new InternshipException(400, "You don't have sufficient balance to process payment");
            }

        }
    }

    private boolean isDateValid(Date actualDate, Date givenDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = dateFormat.parse(String.valueOf(givenDate));
        return date1.compareTo(actualDate) == 0;
    }
}
