import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.warehouse.exceptions.ExceedingCapacity;
import org.warehouse.exceptions.InvalidQuantity;
import org.warehouse.exceptions.MaterialAlreadyExists;
import org.warehouse.exceptions.MaterialNotFound;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.Material;
import org.warehouse.model.material.MaterialType;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWarehouseFunctionality {
    private WareHouse warehouse;  // The warehouse instance to be tested
    private Material iron;        // Test material of type iron
    private Material coal;        // Test material of type coal

    // Set up initial conditions for the tests
    @BeforeEach
    void setUp() {
        warehouse = new WareHouse(new HashMap<>());  // Initialize an empty warehouse

        // Initialize material types with specific maximum capacities
        MaterialType ironType = new MaterialType("Iron", "Used for construction", "src/main/resources/materials/iron.png", 500);
        MaterialType coalType = new MaterialType("Coal", "Used for energy production", "src/main/resources/materials/coal.png", 200);

        // Initialize materials with a type and zero quantity initially
        iron = new Material(ironType, 0);
        coal = new Material(coalType, 0);
    }

    // Test adding material with a scenario that should fail (already exists)
    @Test
    void testAddMaterialFailed() {
        assertThrows(MaterialAlreadyExists.class, () -> {
            warehouse.addMaterial(iron, 100);  // First add should succeed
            warehouse.addMaterial(iron, 100);  // Second add should fail (material already exists)
        });
    }

    // Test successful addition of material to the warehouse
    @Test
    void testAddMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);  // Add iron with a quantity of 100
        assertEquals(100, warehouse.listAllMaterials().get(iron));  // Check if iron has the correct quantity
    }

    // Test updating the quantity of a material that doesn't exist (should fail)
    @Test
    void testUpdateMaterialQuantityFailed() {
        assertThrows(MaterialNotFound.class, () -> warehouse.updateMaterialQuantity(iron, 100));
    }

    // Test updating the quantity of a material that exists
    @Test
    void testUpdateMaterialQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(coal, 100);  // Add coal with a quantity of 100
        warehouse.updateMaterialQuantity(coal, 200);  // Increase the quantity of coal by 200
        assertEquals(300, warehouse.listAllMaterials().get(coal));  // Verify the updated quantity
    }

    // Test for invalid quantities (should throw appropriate exceptions)
    @Test
    void testCheckInvalidQuantity() {
        // Check for a zero quantity, which should be invalid
        assertThrows(InvalidQuantity.class, () -> warehouse.checkInvalidQuantity(iron, 0));
        // Check for exceeding the capacity
        assertThrows(ExceedingCapacity.class, () -> warehouse.checkInvalidQuantity(iron, 1000)); // Exceeding iron's capacity
    }

    // Test removing a material that doesn't exist (should throw MaterialNotFound)
    @Test
    void testRemoveMaterialFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.removeMaterial(iron));
    }

    // Test successfully removing a material from the warehouse
    @Test
    void testRemoveMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);  // Add iron with 100 quantity
        warehouse.removeMaterial(iron);  // Remove iron from the warehouse
    }

    // Test dropping some quantity of a material that doesn't exist (should throw MaterialNotFound)
    @Test
    void testDropSomeQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.dropSomeQuantity(coal, 12));  // Coal not yet added
    }

    // Test dropping some quantity of an existing material
    @Test
    void testDropSomeQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);  // Add iron with 100 quantity
        warehouse.dropSomeQuantity(iron, 50);  // Drop 50 units of iron
        assertEquals(50, warehouse.listAllMaterials().get(iron));  // Verify the remaining quantity
    }

    // Test transferring a full quantity of material to another warehouse
    @Test
    void testTransferFullQuantityMaterialV1() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());  // Create another warehouse
        warehouse.addMaterial(coal, 100);  // Add coal to the first warehouse
        warehouse.transferFullMaterial(otherWarehouse, coal);  // Transfer all coal to the other warehouse
        assertEquals(100, otherWarehouse.listAllMaterials().get(coal));  // Check if coal was transferred successfully
    }

    // Test transferring full material when the other warehouse already has the same material
    @Test
    void testTransferFullQuantityMaterialV2() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());  // Create another warehouse
        warehouse.addMaterial(coal, 99);  // Add coal to the first warehouse
        otherWarehouse.addMaterial(coal, 75);  // Add coal to the other warehouse
        warehouse.transferFullMaterial(otherWarehouse, coal);  // Transfer all coal to the other warehouse
        assertEquals(174, otherWarehouse.listAllMaterials().get(coal));  // Check if the quantities are correctly combined
    }

    // Test transferring some quantity of a material to another warehouse
    @Test
    void testTransferSomeQuantityOfMaterial() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());  // Create another warehouse
        warehouse.addMaterial(iron, 100);  // Add iron to the first warehouse
        warehouse.transferSomeQuantityOfMaterial(otherWarehouse, iron, 50);  // Transfer 50 units to the other warehouse
        assertEquals(50, warehouse.listAllMaterials().get(iron));  // Verify the remaining quantity
        assertEquals(50, otherWarehouse.listAllMaterials().get(iron));  // Verify the transferred quantity
    }

    // Test getting the quantity of a material that doesn't exist (should throw MaterialNotFound)
    @Test
    void testGetMaterialMaximumQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.getMaterialQuantity(warehouse, coal));  // Coal not yet added
    }

    // Test getting the maximum quantity of an existing material
    @Test
    void testGetMaterialMaximumQuantitySuccess() throws MaterialNotFound, ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        warehouse.addMaterial(iron, 345);  // Add 345 units of iron
        assertEquals(345, warehouse.getMaterialQuantity(warehouse, iron));  // Check if the quantity is correct
    }

    // Test listing all materials in an empty warehouse and a warehouse with materials
    @Test
    void testListAllMaterials() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        Assertions.assertTrue(warehouse.listAllMaterials().isEmpty());  // Should be empty initially
        warehouse.addMaterial(iron, 100);  // Add iron with 100 quantity
        Assertions.assertFalse(warehouse.listAllMaterials().isEmpty());  // Now it should not be empty
    }
}
