package com.example.sellers.SellerProject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String error;
    private T data;

    public ApiResponse(boolean status, String message) {
        this.success = status;
        this.error = message;
    }

    public ApiResponse(String message, boolean status, T data) {
        this.error = message;
        this.success = status;
        this.data = data;
    }
}
