package com.internship.Internship.service.impl;

import com.internship.Internship.dto.PaymentModelDto;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.*;
import com.internship.Internship.repository.*;
import com.internship.Internship.service.IPaymentService;
import com.internship.Internship.service.IStudentService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    IPaymentRepository paymentRepository;
    @Autowired
    IBankPaymentValidatorRepository bankPaymentValidatorRepository;
    @Autowired
    IMentorRepository mentorRepository;
    @Autowired
    ICartRepository cartRepository;

    @Autowired
    IAddInternshipRepository addInternshipRepository;
    @Autowired
    IStudentService studentService;
    @Override
    public ResponseModel validatePayment(PaymentModelDto paymentModelDto) throws ParseException, InternshipException {
        Optional<PaymentMethod> paymentMethod = paymentRepository.findById(paymentModelDto.getCardNumber());
        if (paymentMethod.isPresent()) {
            PaymentMethod paymentMethod1 = paymentMethod.get();
            boolean isDateValid = isDateValid(paymentMethod1.getDate(), paymentModelDto.getExpiryDate());
            if (isDateValid && paymentMethod1.getCvv() == paymentModelDto.getCvv()) {
                validateAndDeductPayment(paymentModelDto);
                ResponseModel responseModel = studentService.addInternshipInAccount(paymentModelDto.getEmail(), paymentModelDto.getInternshipId());
                updateMentorDatabaseWithInternship(paymentModelDto.getInternshipId(), paymentModelDto.getEmail());
                UpdateCartIfrequestIsComingFromCart(paymentModelDto.getEmail(), paymentModelDto.getInternshipId());
                return ResponseModel.builder().message("Payment processed successfully" + "." + responseModel.getMessage())
                        .statusCode(200).build();
            }
        }
        throw new InternshipException(400, "Provided card details are not valid");
    }

    private void UpdateCartIfrequestIsComingFromCart(String email, List<String> internshipId) {
        List<CartModel> cartModels = cartRepository.findAllByUserId(email);
        cartModels = cartModels.stream().filter(e -> internshipId.contains(e.getInternship().getId())).toList();
        if(cartModels.isEmpty()) {
            return;
        }
        cartRepository.deleteAll(cartModels);
    }

    private void updateMentorDatabaseWithInternship(List<String> internshipIds, String email) {
        List<MentorStudentModel> mentorStudentModel = new ArrayList<>();
        for (String internshipId: internshipIds
             ) {
            var byId = addInternshipRepository.findById(internshipId);
            mentorStudentModel.add(MentorStudentModel.builder()
                            .internshipId(internshipId)
                            .mentorEmail(byId.map(InternshipModel::getMentorEmail).orElse(null))
                            .studentEmail(email)
                    .build());
        }
        mentorRepository.saveAll(mentorStudentModel);
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

    private boolean isDateValid(String actualDate,  String givenDate) throws ParseException {
        return actualDate.equals(givenDate);
    }
}
