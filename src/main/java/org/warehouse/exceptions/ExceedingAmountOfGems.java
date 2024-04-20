package org.warehouse.exceptions;

/**
 * An exception indicating that the specified quantity of gems exceeds the allowed limit.
 */
public class ExceedingAmountOfGems extends Exception {

    /**
     * Constructs a new ExceedingAmountOfGems with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ExceedingAmountOfGems(String message) {
        super(message);
    }
}
