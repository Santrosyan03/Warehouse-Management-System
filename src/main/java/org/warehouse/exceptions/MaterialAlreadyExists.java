package org.warehouse.exceptions;

/**
 * An exception indicating that a material already exists.
 */
public class MaterialAlreadyExists extends Exception {

    /**
     * Constructs a new MaterialAlreadyExists with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MaterialAlreadyExists(String message) {
        super(message);
    }
}
