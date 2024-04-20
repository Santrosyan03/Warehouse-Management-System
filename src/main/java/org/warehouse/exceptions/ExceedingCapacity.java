package org.warehouse.exceptions;

/**
 * An exception indicating that an operation exceeded the capacity limit.
 */
public class ExceedingCapacity extends Exception {

    /**
     * Constructs a new ExceedingCapacity with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ExceedingCapacity(String message) {
        super(message);
    }
}
