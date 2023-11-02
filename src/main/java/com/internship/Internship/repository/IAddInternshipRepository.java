package com.internship.Internship.repository;

import com.internship.Internship.model.InternshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddInternshipRepository extends JpaRepository<InternshipModel, String> {
    List<InternshipModel> findAllBySeatsNot(int i);
}
