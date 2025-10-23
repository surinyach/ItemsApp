package com.items.itemsBackend.exceptions;

// ItemNotFoundException.java

// Thrown when trying to retrieve an item from the database, but that item does not exist.
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
