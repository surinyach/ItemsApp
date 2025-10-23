package com.items.itemsBackend.services;
import com.items.itemsBackend.dto.ApiResponseDto;
import com.items.itemsBackend.dto.ApiResponseStatus;
import com.items.itemsBackend.dto.ItemRegistrationDto;
import com.items.itemsBackend.exceptions.ItemNotFoundException;
import com.items.itemsBackend.models.Item;
import com.items.itemsBackend.exceptions.ItemAlreadyExistsException;
import com.items.itemsBackend.exceptions.ItemServiceLogicException;
import com.items.itemsBackend.repositories.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

// ItemServiceImpl

@Service
@Slf4j // Simple Logging Facade for Java. Automatically creates a logger
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerItem(ItemRegistrationDto newItemDetails)
            throws ItemAlreadyExistsException, ItemServiceLogicException {

        try {
            // Check if item exists
            if (itemRepository.findByName(newItemDetails.getItemName()) != null) {
                throw new ItemAlreadyExistsException(
                        "Registration Failed: Item already exists with name: " + newItemDetails.getItemName());
            }

            // Create new item
            Item newItem = new Item();
            newItem.setName(newItemDetails.getItemName());
            newItem.setSection(newItemDetails.getSection());
            newItem.setPrice(newItemDetails.getPrice());
            newItem.setStock(newItemDetails.getStock());

            itemRepository.save(newItem);

            // Success response
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(
                            ApiResponseStatus.SUCCESS.name(),
                            "New item has been successfully created."
                    ));

        } catch (ItemAlreadyExistsException e) {
            throw e; // let it propagate
        } catch (Exception e) {
            log.error("Failed to create a new item: {}", e.getMessage());
            throw new ItemServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllItems() throws ItemServiceLogicException {
        try {
            List<Item> items = itemRepository.findAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), items));
        } catch(Exception e) {
            log.error("Failed to fetch all items: " + e.getMessage());
            throw new ItemServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateItem(ItemRegistrationDto newItemDetails, int id)
        throws ItemNotFoundException, ItemServiceLogicException {
        try {
            Item item = itemRepository.findById(id);
            if (item == null) throw new ItemNotFoundException("Item not found with id: " + id);

            item.setName(newItemDetails.getItemName());
            item.setPrice(newItemDetails.getPrice());
            item.setSection(newItemDetails.getSection());
            item.setStock(newItemDetails.getStock());

            itemRepository.save(item);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Item information successfully updated!"));
        } catch(ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update item: " + e.getMessage());
            throw new ItemServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteItem(int id) throws ItemNotFoundException, ItemServiceLogicException {
        try {
            Item item = itemRepository.findById(id);
            if (item == null) throw new ItemNotFoundException("Item not found with id: " + id);
            itemRepository.delete(item);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account deleted successfully!"));
        } catch(ItemNotFoundException e) {
            throw e;
        } catch(Exception e) {
            log.error("Error erasing an item: " +  e.getMessage());
            throw new ItemServiceLogicException();
        }
    }
}