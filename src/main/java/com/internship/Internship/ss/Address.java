package com.internship.Internship.ss;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private int addressId;
    private String city;
}
