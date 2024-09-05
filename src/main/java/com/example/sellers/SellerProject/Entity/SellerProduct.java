package com.example.sellers.SellerProject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "seller_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerProduct {
    @Id
    private String id;

    private String productName;
    private Integer stock;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
