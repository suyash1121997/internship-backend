package com.internship.Internship.repository;

import com.internship.Internship.model.BankPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankPaymentValidatorRepository extends JpaRepository<BankPaymentModel, String> {
}
