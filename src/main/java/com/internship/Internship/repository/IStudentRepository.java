package com.internship.Internship.repository;

import com.internship.Internship.model.StudentInternship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentInternship, Integer> {
    StudentInternship findByStudentEmailAndInternshipId(String email, String id);
}
