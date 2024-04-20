package org.warehouse.management;

import org.warehouse.exceptions.*;
import org.warehouse.model.material.Material;

import java.util.Map;

/**
 * An interface defining the common operations for managing materials in an inventory system.
 * This interface serves as a contract for inventory management, providing methods for
 * adding, updating, transferring, and querying material stock.
 */

public interface Inventory {
    /**
     * Adds a new material to the warehouse if it doesn't already exist.
     * @param material The material to add.
     * @param quantity The quantity of the material to add.
     * @return The material added.
     * @throws ExceedingCapacity If the quantity exceeds the material's maximum capacity.
     * @throws InvalidQuantity If the specified quantity is less than or equal to zero.
     * @throws MaterialAlreadyExists If the material already exists in the inventory.
     */
    Material addMaterial(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists;

    /**
     * Updates the quantity of an existing material in the warehouse.
     * @param material The material to update.
     * @param quantity The quantity to add to the existing material quantity.
     * @throws ExceedingCapacity If the update causes the quantity to exceed the maximum capacity.
     * @throws InvalidQuantity If the specified quantity is invalid.
     * @throws MaterialNotFound If the material is not found in the inventory.
     */
    void updateMaterialQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;

    /**
     * Removes a material completely from the warehouse.
     * @param material The material to remove.
     * @return The material removed.
     * @throws MaterialNotFound If the material is not found in the inventory.
     */
    Material removeMaterial(Material material) throws MaterialNotFound;

    /**
     * Reduces the quantity of a specified material in the warehouse by a given amount.
     * @param material The material whose quantity is to be reduced.
     * @param quantity The amount by which the material's quantity is to be reduced.
     * @return The amount by which the quantity was reduced.
     * @throws ExceedingCapacity If reducing the quantity would leave a negative inventory.
     * @throws InvalidQuantity If the specified quantity for reduction is invalid or exceeds the current stock.
     * @throws MaterialNotFound If the material is not found in the warehouse's inventory.
     */
    int dropSomeQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;

    /**
     * Transfers all quantity of a specified material to another warehouse.
     * The entire quantity of the material is removed from this warehouse and added to the destination warehouse.
     * @param toWarehouse The destination warehouse to which the material is to be transferred.
     * @param material The material to be transferred.
     * @throws ExceedingCapacity If the destination warehouse cannot accommodate the transferred quantity.
     * @throws MaterialAlreadyExists If the material already exists in the destination warehouse.
     * @throws InvalidQuantity If the quantity of the material is invalid.
     * @throws MaterialNotFound If the material is not found in this warehouse.
     */
    void transferFullMaterial(Inventory toWarehouse, Material material) throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound;

    /**
     * Transfers a specified quantity of a material to another warehouse.
     * If the specified quantity is valid and available, it is deducted from this warehouse and added to the destination warehouse.
     * @param toWarehouse The destination warehouse to which the material is to be transferred.
     * @param material The material to be transferred.
     * @param quantity The quantity of the material to be transferred.
     * @return The quantity that was transferred.
     * @throws ExceedingCapacity If the transfer exceeds the capacity limits of the destination warehouse.
     * @throws InvalidQuantity If the specified quantity is invalid or not available.
     * @throws MaterialAlreadyExists If the material already exists in the destination warehouse and cannot be added.
     * @throws MaterialNotFound If the material is not found in this warehouse.
     */
    int transferSomeQuantityOfMaterial(Inventory toWarehouse, Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists, MaterialNotFound;

    /**
     * Lists all materials and their quantities in the warehouse.
     * @return A map of all materials and their respective quantities.
     */
    Map<Material, Integer> listAllMaterials();

    /**
     * Retrieves the current quantity of a specified material in the warehouse.
     * @param warehouse The inventory from which to retrieve the material quantity.
     * @param material The material whose quantity is to be retrieved.
     * @return The current quantity of the material.
     * @throws MaterialNotFound If the material is not found in the warehouse's inventory.
     */
    int getMaterialQuantity(Inventory warehouse, Material material) throws MaterialNotFound;
}
