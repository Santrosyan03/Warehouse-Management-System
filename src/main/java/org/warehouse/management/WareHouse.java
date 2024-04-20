package org.warehouse.management;

import org.warehouse.exceptions.*;
import org.warehouse.model.material.Material;

import java.util.Map;

/**
 * Represents a warehouse for managing inventory.
 * This class handles operations on the inventory of materials, providing functionalities
 * such as adding, updating, and querying the stock of various materials.
 * The inventory is managed using a map where each material is associated with its quantity.
 */

public class WareHouse implements Inventory {

    // Map to hold the association between materials and their respective quantities.
    // The Material object acts as a key, and the associated Integer value represents the stock quantity of that material.
    private final Map<Material, Integer> warehouseMaterials;

    /**
     * Constructs a new WareHouse object with the provided inventory mapping.
     * This constructor initializes the warehouse with a pre-defined set of materials and their quantities.
     * It is typically used when creating a WareHouse instance with an initial stock list, or when restoring state
     * from a persisted data source.
     *
     * @param warehouseMaterials A map containing initial materials and their corresponding quantities.
     *                           This parameter should not be null; an empty map should be provided if no initial stock is available.
     */
    public WareHouse(Map<Material, Integer> warehouseMaterials) {
        this.warehouseMaterials = warehouseMaterials;
    }

    /**
     * Adds a new material to the warehouse if it doesn't already exist.
     * @param material The material to add.
     * @param quantity The quantity of the material to add.
     * @return The material added.
     * @throws ExceedingCapacity If the quantity exceeds the material's maximum capacity.
     * @throws InvalidQuantity If the specified quantity is less than or equal to zero.
     * @throws MaterialAlreadyExists If the material already exists in the inventory.
     */
    @Override
    public Material addMaterial(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists {
        checkInvalidQuantity(material, quantity);
        if (warehouseMaterials.containsKey(material)) {
            throw new MaterialAlreadyExists("The material you want to add already exists");
        } else {
            warehouseMaterials.put(material, quantity);
        }

        return material;
    }

    /**
     * Updates the quantity of an existing material in the warehouse.
     * @param material The material to update.
     * @param quantity The quantity to add to the existing material quantity.
     * @throws ExceedingCapacity If the update causes the quantity to exceed the maximum capacity.
     * @throws InvalidQuantity If the specified quantity is invalid.
     * @throws MaterialNotFound If the material is not found in the inventory.
     */
    @Override
    public void updateMaterialQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound {
        checkInvalidQuantity(material, quantity);

        if (!warehouseMaterials.containsKey(material)) {
            throw new MaterialNotFound("The material you want to update is not found");
        } else {
            warehouseMaterials.put(material, warehouseMaterials.get(material) + quantity);
        }
    }

    /**
     * Helper method to check if the quantity is valid.
     * @param material The material to check.
     * @param quantity The quantity to check.
     * @throws InvalidQuantity If the quantity is less than or equal to zero.
     * @throws ExceedingCapacity If the quantity exceeds the maximum capacity of the material.
     */
    public void checkInvalidQuantity(Material material, int quantity) throws InvalidQuantity, ExceedingCapacity {
        if (quantity <= 0) {
            throw new InvalidQuantity("The quantity must be greater than 0");
        }

        if (quantity > material.getMaterialType().getMaximumCapacity()) {
            throw new ExceedingCapacity("Adding " + quantity + " units of " + material.getMaterialType().getName() + " would exceed the max capacity of " + material.getMaterialType().getMaximumCapacity());
        }
    }

    /**
     * Removes a material completely from the warehouse.
     * @param material The material to remove.
     * @return The material removed.
     * @throws MaterialNotFound If the material is not found in the inventory.
     */
    @Override
    public Material removeMaterial(Material material) throws MaterialNotFound {
        Material materialToBeRemoved;
        if (!warehouseMaterials.containsKey(material)) {
            throw new MaterialNotFound("The material you want to delete is not found");
        } else {
            materialToBeRemoved = material;
            warehouseMaterials.remove(materialToBeRemoved);
        }
        return materialToBeRemoved;
    }

    /**
     * Reduces the quantity of a specified material in the warehouse by a given amount.
     * @param material The material whose quantity is to be reduced.
     * @param quantity The amount by which the material's quantity is to be reduced.
     * @return The amount by which the quantity was reduced.
     * @throws ExceedingCapacity If reducing the quantity would leave a negative inventory.
     * @throws InvalidQuantity If the specified quantity for reduction is invalid or exceeds the current stock.
     * @throws MaterialNotFound If the material is not found in the warehouse's inventory.
     */
    @Override
    public int dropSomeQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound {
        checkInvalidQuantity(material, quantity);
        if (!warehouseMaterials.containsKey(material)) {
            throw new MaterialNotFound("The material you want to transfer is not found");
        } else {
            warehouseMaterials.put(material, warehouseMaterials.get(material) - quantity);
        }
        return quantity;
    }

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
    @Override
    public void transferFullMaterial(Inventory toWarehouse, Material material) throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        if (!toWarehouse.listAllMaterials().containsKey(material)) {
            toWarehouse.addMaterial(material, warehouseMaterials.get(material));
        } else {
            toWarehouse.updateMaterialQuantity(material, warehouseMaterials.get(material));
        }
        this.removeMaterial(material);
    }

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
    @Override
    public int transferSomeQuantityOfMaterial(Inventory toWarehouse, Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists, MaterialNotFound {
        checkInvalidQuantity(material, quantity);
        if (!toWarehouse.listAllMaterials().containsKey(material)) {
            toWarehouse.addMaterial(material, quantity);
        } else {
            toWarehouse.updateMaterialQuantity(material, quantity);
        }
        this.dropSomeQuantity(material, quantity);
        return quantity;
    }

    /**
     * Lists all materials and their quantities in the warehouse.
     * @return A map of all materials and their respective quantities.
     */
    @Override
    public Map<Material, Integer> listAllMaterials() {
        if (warehouseMaterials.isEmpty()) {
            System.out.println();
        }

        for (Material material : warehouseMaterials.keySet()) {
            System.out.println(material);
        }
        return warehouseMaterials;
    }

    /**
     * Retrieves the current quantity of a specified material in the warehouse.
     * @param warehouse The inventory from which to retrieve the material quantity.
     * @param material The material whose quantity is to be retrieved.
     * @return The current quantity of the material.
     * @throws MaterialNotFound If the material is not found in the warehouse's inventory.
     */
    @Override
    public int getMaterialQuantity(Inventory warehouse, Material material) throws MaterialNotFound {
        if (!warehouseMaterials.containsKey(material)) {
            throw new MaterialNotFound("The material's quantity you want to see is not found");
        }
        return warehouse.listAllMaterials().get(material);
    }
}
