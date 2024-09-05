package com.example.sellers.SellerProject.Dto;

import lombok.Data;

@Data
public class SellerProductDTO {
    private String id;
    private String productName;
    private Integer stock;
    private Double price;
}
