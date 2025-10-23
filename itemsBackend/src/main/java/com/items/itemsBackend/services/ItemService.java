package com.items.itemsBackend.services;
import com.items.itemsBackend.dto.ApiResponseDto;
import com.items.itemsBackend.dto.ItemRegistrationDto;
import com.items.itemsBackend.exceptions.ItemAlreadyExistsException;
import com.items.itemsBackend.exceptions.ItemNotFoundException;
import com.items.itemsBackend.exceptions.ItemServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// ItemService.java

@Service
public interface ItemService {
    ResponseEntity<ApiResponseDto<?>> registerItem(ItemRegistrationDto newItemDetails)
        throws ItemAlreadyExistsException, ItemServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllItems()
        throws ItemServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateItem(ItemRegistrationDto newItemDetails, int id)
        throws ItemNotFoundException, ItemServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteItem(int id)
        throws ItemNotFoundException, ItemServiceLogicException;
}