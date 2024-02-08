package com.internship.Internship.service.impl;
import com.internship.Internship.constants.LoginMode;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.dto.ResponseModel;
import com.internship.Internship.exception.InternshipException;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.repository.ISignUpRepository;
import com.internship.Internship.service.IAddInternshipService;
import com.internship.Internship.service.IStudentService;
import com.internship.Internship.util.LoginValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddInternshipServiceImpl implements IAddInternshipService {
    @Autowired
    IAddInternshipRepository addInternshipRepository;
    @Autowired
    ISignUpRepository signUpRepository;

    Random random = new Random();
    @Override
    public ResponseModel<Internship> addInternship(Internship internship) throws InternshipException {
        LoginValidator.validateUserWithProfile(signUpRepository, internship.getMentorEmail(), LoginMode.MENTOR);
        InternshipModel internshipModel = InternshipModel.builder().build();
        BeanUtils.copyProperties(internship, internshipModel);
        String id = String.format("%04d", random.nextInt(10000));
        internshipModel.setInternshipId(id);
        internship.setId(id);
        addInternshipRepository.save(internshipModel);
        return ResponseModel.<Internship>builder().message("Internship added successfully")
                .isUserExist(true).statusCode(200).details(internship).build();
    }

    @Override
    public List<Internship> getAllInternship(String email) {
        List<InternshipModel> internshipModelList = addInternshipRepository.findAllBySeatsNot(0);

        return internshipModelList.stream()
                .map(internshipModel -> Internship.builder()
                        .seats(internshipModel.getSeats()).mentorEmail(internshipModel.getMentorEmail())
                        .price(internshipModel.getPrice()).title(internshipModel.getTitle())
                        .id(internshipModel.getInternshipId()).duration(internshipModel.getDuration())
                        .requirements(internshipModel.getRequirements())
                        .description(internshipModel.getDescription()).location(internshipModel.getLocation())
                        .build()
                )
                .toList();
    }
}
