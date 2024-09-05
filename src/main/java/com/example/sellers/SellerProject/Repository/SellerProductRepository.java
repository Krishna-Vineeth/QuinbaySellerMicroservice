package com.example.sellers.SellerProject.Repository;


import com.example.sellers.SellerProject.Entity.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerProductRepository extends JpaRepository<SellerProduct, String> {
}
