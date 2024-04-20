package org.warehouse.management;

import org.warehouse.exceptions.*;
import org.warehouse.model.material.Material;

import java.util.Map;

public interface Inventory {
    Material addMaterial(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists;
    void updateMaterialQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;
    Material removeMaterial(Material material) throws MaterialNotFound;
    int dropSomeQuantity(Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;
    void transferFullMaterial(Inventory toWarehouse, Material material) throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound;
    int transferSomeQuantityOfMaterial(Inventory toWarehouse, Material material, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists, MaterialNotFound;
    Map<Material, Integer> listAllMaterials();
    int getMaterialQuantity(Inventory warehouse, Material material) throws MaterialNotFound;
}
