package org.warehouse.exceptions;

/**
 * An exception indicating that an invalid quantity of gems was provided.
 */
public class InvalidQuantityOfGems extends Exception {

    /**
     * Constructs a new InvalidQuantityOfGems with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidQuantityOfGems(String message) {
        super(message);
    }
}
