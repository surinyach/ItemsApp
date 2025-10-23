package com.items.itemsBackend.exceptions;

// ItemAlreadyExistsException.java

// Thrown when attempting to create a new item, but a item with the same identifier (name) already exists in the database.
public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
