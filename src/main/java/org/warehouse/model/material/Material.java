package org.warehouse.model.material;

/**
 * Represents a material in a warehouse system, including its type and quantity.
 * This class encapsulates a specific material type and its associated quantity,
 * providing methods to get and set these properties.
 */
public class Material {
    private MaterialType materialType; // The type of the material.
    private int quantity; // The quantity of the material.

    /**
     * Constructs a new Material object with a given material type and quantity.
     *
     * @param materialType The type of the material.
     * @param quantity The initial quantity of the material.
     */
    public Material(MaterialType materialType, int quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
    }

    /**
     * Gets the type of the material.
     *
     * @return The material type.
     */
    public MaterialType getMaterialType() {
        return materialType;
    }

    /**
     * Sets the type of the material.
     *
     * @param materialType The new material type to set.
     */
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    /**
     * Gets the current quantity of the material.
     *
     * @return The quantity of the material.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets a new quantity for the material.
     *
     * @param quantity The new quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
