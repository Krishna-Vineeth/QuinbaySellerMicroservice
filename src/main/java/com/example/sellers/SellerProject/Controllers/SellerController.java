package com.example.sellers.SellerProject.Controllers;

import com.example.sellers.SellerProject.ApiResponse;

import com.example.sellers.SellerProject.Dto.SellerDTO;
import com.example.sellers.SellerProject.Dto.SellerProductDTO;
import com.example.sellers.SellerProject.Entity.SellerObject;
import com.example.sellers.SellerProject.Services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    @Autowired
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<ApiResponse<SellerDTO>> createSeller(@RequestBody SellerDTO sellerDTO) {
        try {
            SellerDTO createdSeller = sellerService.createSeller(sellerDTO);
            return new ResponseEntity<>(new ApiResponse<>(true, "Seller created successfully.", createdSeller), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SellerDTO>> getSeller(@PathVariable String id) {
        try {
            SellerDTO sellerDTO = sellerService.getSellerById(id);
            return new ResponseEntity<>(new ApiResponse<>(true, "Seller retrieved successfully.", sellerDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{sellerId}/products")
    public ResponseEntity<ApiResponse<SellerProductDTO>> addProductToSeller(@PathVariable String sellerId, @RequestBody SellerProductDTO productDTO) {
        try {
            SellerProductDTO addedProduct = sellerService.addProductToSeller(sellerId, productDTO);
            return new ResponseEntity<>(new ApiResponse<>(true, "Product added to seller successfully.", addedProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{sellerId}/products/{productId}")
    public ResponseEntity<ApiResponse<SellerProductDTO>> updateProduct(@PathVariable String sellerId, @PathVariable String productId,
                                                                       @RequestBody SellerProductDTO productDTO) {
        try {
            SellerProductDTO updatedProduct = sellerService.updateProduct(sellerId, productId, productDTO);
            return new ResponseEntity<>(new ApiResponse<>(true, "Product updated successfully.", updatedProduct), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

