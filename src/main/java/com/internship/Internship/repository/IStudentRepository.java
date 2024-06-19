package com.internship.Internship.repository;

import com.internship.Internship.model.StudentInternship;
import java.util.List;

import com.internship.Internship.model.compositekeys.StudentInternShipCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IStudentRepository extends JpaRepository<StudentInternship, StudentInternShipCompositeKey> {
    StudentInternship findByStudentInternShipCompositeKey(StudentInternShipCompositeKey studentInternShipCompositeKey);

    List<StudentInternship> findByStudentInternShipCompositeKeyStudentEmail(String studentEmail);
}
