package com.example.sellers.SellerProject.Config;

import com.example.sellers.SellerProject.ApiResponse;
import com.example.sellers.SellerProject.Entity.SellerObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://10.20.3.216:8090/product") // Replace with actual IP and port
public interface ProductClient {

    @PostMapping("/addSeller")
    ResponseEntity<ApiResponse<String>> addSeller(@RequestParam("pId") String pId, @RequestBody SellerObject seller);
}
