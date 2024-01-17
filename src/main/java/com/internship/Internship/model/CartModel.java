package com.internship.Internship.model;

import com.internship.Internship.dto.Internship;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {

    @Id
    private String cartId;
    private String userId;
    @Type(JsonBinaryType.class)
    @Column(name = "internship", columnDefinition = "jsonb")
    private Internship internship;
}
