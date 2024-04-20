package org.warehouse.exceptions;

/**
 * An exception indicating that a material was not found.
 */
public class MaterialNotFound extends Exception {

    /**
     * Constructs a new MaterialNotFound with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MaterialNotFound(String message) {
        super(message);
    }
}
