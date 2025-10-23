package com.items.itemsBackend.controllers;

import com.items.itemsBackend.dto.ApiResponseDto;
import com.items.itemsBackend.dto.ItemRegistrationDto;
import com.items.itemsBackend.exceptions.ItemAlreadyExistsException;
import com.items.itemsBackend.exceptions.ItemNotFoundException;
import com.items.itemsBackend.exceptions.ItemServiceLogicException;
import com.items.itemsBackend.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ItemController.java

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    public ItemService itemService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> registerItem(@Valid @RequestBody ItemRegistrationDto itemRegistrationDto) throws ItemAlreadyExistsException, ItemServiceLogicException {
        return itemService.registerItem(itemRegistrationDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponseDto<?>> getAllItems() throws ItemServiceLogicException {
        return itemService.getAllItems();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateItem(@Valid @RequestBody ItemRegistrationDto itemRegistrationDto, @PathVariable int id)
            throws ItemNotFoundException, ItemServiceLogicException {
        return itemService.updateItem(itemRegistrationDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteItem(@PathVariable int id)
            throws ItemNotFoundException, ItemServiceLogicException {
        return itemService.deleteItem(id);
    }
}

