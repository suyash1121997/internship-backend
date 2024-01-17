package com.internship.Internship.ss;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class SupplierDetails {
    @Id
    private int supplierId;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressId;

}
