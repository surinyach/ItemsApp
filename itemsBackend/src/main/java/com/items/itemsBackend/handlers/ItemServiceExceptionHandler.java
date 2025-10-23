package com.items.itemsBackend.handlers;

// ItemServiceExceptionHandler.java

import com.items.itemsBackend.dto.ApiResponseDto;
import com.items.itemsBackend.dto.ApiResponseStatus;
import com.items.itemsBackend.exceptions.ItemAlreadyExistsException;
import com.items.itemsBackend.exceptions.ItemNotFoundException;
import com.items.itemsBackend.exceptions.ItemServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ItemServiceExceptionHandler {

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> ItemNotFoundExceptionHandler(ItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ItemAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<?>> ItemAlreadyExistsExceptionHandler(ItemAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ItemServiceLogicException.class)
    public ResponseEntity<ApiResponseDto<?>> ItemServiceLogicExceptionHandler(ItemServiceLogicException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), errorMessage.toString()));
    }
}
