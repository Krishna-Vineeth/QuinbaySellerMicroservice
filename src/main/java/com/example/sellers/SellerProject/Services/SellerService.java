package com.example.sellers.SellerProject.Services;

import com.example.sellers.SellerProject.ApiResponse;
import com.example.sellers.SellerProject.Config.ProductClient;
import com.example.sellers.SellerProject.Dto.SellerDTO;
import com.example.sellers.SellerProject.Dto.SellerProductDTO;
import com.example.sellers.SellerProject.Entity.Seller;
import com.example.sellers.SellerProject.Entity.SellerObject;
import com.example.sellers.SellerProject.Entity.SellerProduct;
import com.example.sellers.SellerProject.Repository.SellerProductRepository;
import com.example.sellers.SellerProject.Repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
//
    @Autowired
    private ProductClient productClient;

    @Autowired
    private SellerProductRepository sellerProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SellerDTO createSeller(SellerDTO sellerDTO) {
        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        seller = sellerRepository.save(seller);
        return modelMapper.map(seller, SellerDTO.class);
    }

    public SellerDTO getSellerById(String id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        return modelMapper.map(seller, SellerDTO.class);
    }

    public SellerProductDTO addProductToSeller(String sellerId, SellerProductDTO productDTO) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        SellerProduct product = modelMapper.map(productDTO, SellerProduct.class);
        product.setSeller(seller);
        seller.getProducts().add(product);
        sellerRepository.save(seller);
        return modelMapper.map(product, SellerProductDTO.class);
    }

    public SellerProductDTO updateProduct(String sellerId, String productId, SellerProductDTO productDTO) {

        sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        SellerProduct product = sellerProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

//        modelMapper.map(productDTO, product);
//        sellerProductRepository.save(product);

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        SellerObject sellerObject = new SellerObject();
        sellerObject.setSId(sellerId);
        sellerObject.setStock(productDTO.getStock());
        sellerObject.setPrice(productDTO.getPrice());
        sellerObject.setSName(seller.getName());

        // Call Product Microservice

        ResponseEntity<ApiResponse<String>> response = productClient.addSeller(productId, sellerObject);

        if (response.getBody().isSuccess()) {
            return modelMapper.map(product, SellerProductDTO.class);
        } else {
            throw new RuntimeException("Failed to update product in Product Microservice");
        }
    }



}
