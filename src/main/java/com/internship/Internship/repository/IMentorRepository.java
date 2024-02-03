package com.internship.Internship.repository;

import com.internship.Internship.model.MentorStudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface IMentorRepository extends JpaRepository<MentorStudentModel, Integer> {
    List<MentorStudentModel> findAllByMentorEmail(String mentorEmail);
}
