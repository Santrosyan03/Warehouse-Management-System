package org.warehouse.exceptions;

/**
 * An exception indicating that an invalid quantity was provided.
 */
public class InvalidQuantity extends Exception {

    /**
     * Constructs a new InvalidQuantity with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidQuantity(String message) {
        super(message);
    }
}
