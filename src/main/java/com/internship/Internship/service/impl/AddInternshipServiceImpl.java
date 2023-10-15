package com.internship.Internship.service.impl;
import com.internship.Internship.dto.Internship;
import com.internship.Internship.model.InternshipModel;
import com.internship.Internship.repository.IAddInternshipRepository;
import com.internship.Internship.service.IAddInternshipService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
@Transactional
public class AddInternshipServiceImpl implements IAddInternshipService {
    @Autowired
    IAddInternshipRepository addInternshipRepository;
    Random random = new Random();
    @Override
    public Integer addInternship(Internship internship) {
        InternshipModel internshipModel = InternshipModel.builder().build();
        BeanUtils.copyProperties(internship, internshipModel);
        String id = String.format("%04d", random.nextInt(10000));
        internshipModel.setId(id);
        addInternshipRepository.save(internshipModel);
        return 200;
    }

    @Override
    public List<InternshipModel> getAllInternship() {
        return addInternshipRepository.findAll();
    }
}
