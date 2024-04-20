package org.warehouse.exceptions;

/**
 * An exception indicating that an invalid quantity of money was provided.
 */
public class InvalidQuantityOfMoney extends Exception {

    /**
     * Constructs a new InvalidQuantityOfMoney with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidQuantityOfMoney(String message) {
        super(message);
    }
}
