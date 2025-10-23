package com.items.itemsBackend.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ItemRegistrationDto.java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRegistrationDto {

    @NotBlank(message="Item name is required!")
    @Size(min=3, message= "Item name must have at least 3 characters!")
    @Size(max=20, message= "Item name must have less than 20 characters!")
    private String itemName;

    @NotBlank(message="Section is required!")
    @Size(min=3, message= "Section must have at least 3 characters!")
    @Size(max=20, message= "Section must have less than 20 characters!")
    private String section;

    @DecimalMax(value = "200.0", message = "The maximum price is 200€!")
    @DecimalMin(value="0.0", message= "The price must be greater than 0€!")
    private double price;

    @Max(value=100, message = "The maximum stock of a product is 100 units!")
    @Min(value=0, message="The stock of a product can not be negative!")
    private int stock;
}
