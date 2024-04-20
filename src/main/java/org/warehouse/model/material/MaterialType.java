package org.warehouse.model.material;

/**
 * Represents a type of material in a warehouse system.
 * This class encapsulates the properties and characteristics of a material type,
 * such as its name, description, icon, and maximum capacity.
 */
public class MaterialType {
    private String name;             // The name of the material type.
    private String description;      // A brief description of the material type.
    private String icon;             // A visual representation of the material type (e.g., a URL or resource path to an image).
    private int maximumCapacity;     // The maximum capacity of this material type in a given context.

    /**
     * Constructs a new MaterialType with the specified attributes.
     *
     * @param name The name of the material type.
     * @param description A brief description of the material type.
     * @param icon The visual icon associated with the material type.
     * @param maximumCapacity The maximum capacity for this material type.
     */
    public MaterialType(String name, String description, String icon, int maximumCapacity) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maximumCapacity = maximumCapacity;
    }

    /**
     * Gets the name of the material type.
     *
     * @return The name of the material type.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the material type.
     *
     * @param name The new name for the material type.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the material type.
     *
     * @return The description of the material type.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for the material type.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the icon associated with the material type.
     *
     * @return The icon representing the material type.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets a new icon for the material type.
     *
     * @param icon The new icon to set.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the maximum capacity for this material type.
     *
     * @return The maximum capacity.
     */
    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    /**
     * Sets a new maximum capacity for the material type.
     *
     * @param maximumCapacity The new maximum capacity to set.
     */
    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
}
