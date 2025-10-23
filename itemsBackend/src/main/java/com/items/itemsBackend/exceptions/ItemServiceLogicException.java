package com.items.itemsBackend.exceptions;

// ItemServiceLogicException.java

public class ItemServiceLogicException extends Exception {
    public ItemServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}
