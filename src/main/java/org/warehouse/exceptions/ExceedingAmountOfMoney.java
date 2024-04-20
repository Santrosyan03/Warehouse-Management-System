package org.warehouse.exceptions;

/**
 * An exception indicating that the specified amount of money exceeds the allowed limit.
 */
public class ExceedingAmountOfMoney extends Exception {

    /**
     * Constructs a new ExceedingAmountOfMoney with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ExceedingAmountOfMoney(String message) {
        super(message);
    }
}
