package com.items.itemsBackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

// ApiResponseDto.java

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String status;
    private T response;
}