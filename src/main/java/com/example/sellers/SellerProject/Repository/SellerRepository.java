package com.example.sellers.SellerProject.Repository;

import com.example.sellers.SellerProject.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {
}
