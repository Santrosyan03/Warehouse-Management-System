package org.warehouse;

import org.warehouse.exceptions.*;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.Material;
import org.warehouse.model.material.MaterialType;
import org.warehouse.model.user.User;
import org.warehouse.model.user.UserFunctionalities;

import java.util.HashMap;
import java.util.Map;

/**
 * The main class that demonstrates warehouse and user functionalities.
 * It simulates operations with two types of materials and multiple warehouses for different users.
 */
public class Main {
    public static void main(String[] args) {
        // Create two types of materials: Iron and Coal
        MaterialType ironType = new MaterialType("Iron", "Metal used in construction", "src/main/resources/materials/iron.png", 1000);
        MaterialType coalType = new MaterialType("Coal", "Used for energy production", "src/main/resources/materials/coal.png", 1000);

        // Create Material instances for Iron and Coal with initial quantities
        Material iron = new Material(ironType, 100);
        Material coal = new Material(coalType, 100);

        // Create Warehouses for two users
        WareHouse user1_wareHouse1 = new WareHouse(new HashMap<>());
        WareHouse user1_wareHouse2 = new WareHouse(new HashMap<>());
        Map<Integer, WareHouse> user1_warehouses = new HashMap<>();
        user1_warehouses.put(1, user1_wareHouse1);
        user1_warehouses.put(2, user1_wareHouse2);

        WareHouse user2_wareHouse1 = new WareHouse(new HashMap<>());
        WareHouse user2_wareHouse2 = new WareHouse(new HashMap<>());
        Map<Integer, WareHouse> user2_warehouses = new HashMap<>();
        user2_warehouses.put(1, user2_wareHouse1);
        user2_warehouses.put(2, user2_wareHouse2);

        // Create User instances with initial gems, money, and levels
        User user1 = new User("User1", user1_warehouses, 5, 100, 1);
        User user2 = new User("User2", user2_warehouses, 10, 200, 3);

        // Create UserFunctionalities instances for managing user-specific operations
        UserFunctionalities userFunctionalities1 = new UserFunctionalities(user1);
        UserFunctionalities userFunctionalities2 = new UserFunctionalities(user2);

        try {
            // Warehouse operations for User1's Warehouses
            user1_warehouses.get(1).addMaterial(iron, 10); // Add 10 units of iron
            user1_warehouses.get(1).addMaterial(coal, 15); // Add 15 units of coal

            user1_warehouses.get(1).updateMaterialQuantity(iron, 20); // Update iron quantity by 20
            user1_warehouses.get(1).updateMaterialQuantity(coal, 40); // Update coal quantity by 40

            user1_warehouses.get(1).removeMaterial(iron); // Remove iron material
            user1_warehouses.get(1).removeMaterial(coal); // Remove coal material

            user1_warehouses.get(1).addMaterial(iron, 10); // Add 10 iron
            user1_warehouses.get(1).addMaterial(coal, 15); // Add 15 coal

            user1_warehouses.get(1).dropSomeQuantity(iron, 1); // Drop 1 unit of iron
            user1_warehouses.get(1).dropSomeQuantity(coal, 4); // Drop 4 units of coal

            user1_warehouses.get(1).transferFullMaterial(user1_warehouses.get(2), iron); // Transfer all iron to Warehouse 2
            user1_warehouses.get(1).transferFullMaterial(user1_warehouses.get(2), coal); // Transfer all coal to Warehouse 2

            user1_warehouses.get(2).transferSomeQuantityOfMaterial(user1_warehouses.get(1), iron, 1); // Transfer 1 iron back to Warehouse 1
            user1_warehouses.get(2).transferSomeQuantityOfMaterial(user1_warehouses.get(1), coal, 1); // Transfer 1 coal back to Warehouse 1

            // List all materials in both warehouses
            user1_warehouses.get(1).listAllMaterials();
            user1_warehouses.get(2).listAllMaterials();

            // Check material quantity in the warehouses
            user1_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), iron);
            user1_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), coal);

            user1_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), iron);
            user1_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), coal);

            System.out.println("---------------------------------------------");

            // Warehouse operations for User2's Warehouses
            user2_warehouses.get(1).addMaterial(iron, 10); // Add 10 units of iron
            user2_warehouses.get(1).addMaterial(coal, 15); // Add 15 units of coal

            user2_warehouses.get(1).updateMaterialQuantity(iron, 20); // Update iron quantity by 20
            user2_warehouses.get(1).updateMaterialQuantity(coal, 40); // Update coal quantity by 40

            user2_warehouses.get(1).removeMaterial(iron); // Remove iron material
            user2_warehouses.get(1).removeMaterial(coal); // Remove coal material

            user2_warehouses.get(1).addMaterial(iron, 10); // Add 10 iron
            user2_warehouses.get(1).addMaterial(coal, 15); // Add 15 coal

            user2_warehouses.get(1).dropSomeQuantity(iron, 1); // Drop 1 unit of iron
            user2_warehouses.get(1).dropSomeQuantity(coal, 4); // Drop 4 unit of coal

            user2_warehouses.get(1).transferFullMaterial(user2_warehouses.get(2), iron); // Transfer all iron to Warehouse 2
            user2_warehouses.get(1).transferFullMaterial(user2_warehouses.get(2), coal); // Transfer all coal to Warehouse 2

            user2_warehouses.get(2).transferSomeQuantityOfMaterial(user2_warehouses.get(1), iron, 1); // Transfer 1 iron back to Warehouse 1
            user2_warehouses.get(2).transferSomeQuantityOfMaterial(user2_warehouses.get(1), coal, 1); // Transfer 1 coal back to Warehouse 1

            // List all materials and check inventory
            user2_warehouses.get(1).listAllMaterials();
            user2_warehouses.get(2).listAllMaterials();

            user2_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), iron);
            user2_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), coal);

            user2_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), iron);
            user2_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), coal);

            System.out.println("---------------------------------------------");

            // User1 operations
            userFunctionalities1.updateMoney(100); // Update user1's money by 100
            System.out.println("User1's money: " + user1.getMoney());
            userFunctionalities1.updateGems(10); // Update user1's gems by 10
            System.out.println("User1's gems: " + user1.getGems());
            userFunctionalities1.upgradeLevel(); // Upgrade user1's level
            System.out.println("User1's level: " + user1.getLevel());

            // Check User1's inventory
            userFunctionalities1.checkInventory(user1_warehouses.get(1), iron);
            userFunctionalities1.checkInventory(user1_warehouses.get(1), coal);
            userFunctionalities1.checkInventory(user1_warehouses.get(2), iron);
            userFunctionalities1.checkInventory(user1_warehouses.get(2), coal);

            userFunctionalities1.spendMoney(50); // Spend 50 money
            System.out.println("User1's money: " + user1.getMoney());
            userFunctionalities1.spendGems(5); // Spend 5 gems
            System.out.println("User1's gems: " + user1.getGems());

            // User2 operations
            userFunctionalities2.updateMoney(432); // Update user2's money by 432
            System.out.println("User2's money: " + user2.getMoney());
            userFunctionalities2.updateGems(27); // Update user2's gems by 27
            System.out.println("User2's gems: " + user2.getGems());
            userFunctionalities2.upgradeLevel(); // Upgrade user2's level
            System.out.println("User2's level: " + user2.getLevel());

            // Check User2's inventory
            userFunctionalities2.checkInventory(user2_warehouses.get(1), iron);
            userFunctionalities2.checkInventory(user2_warehouses.get(1), coal);
            userFunctionalities2.checkInventory(user2_warehouses.get(2), iron);
            userFunctionalities2.checkInventory(user2_warehouses.get(2), coal);

            userFunctionalities2.spendMoney(40); // Spend 40 money
            System.out.println("User2's money: " + user2.getMoney());
            userFunctionalities2.spendGems(18); // Spend 18 gems
            System.out.println("User2's gems: " + user2.getGems());

        } catch (InvalidQuantityOfMoney |
                 InvalidQuantityOfGems |
                 ExceedingAmountOfMoney |
                 ExceedingAmountOfGems |
                 ExceedingCapacity |
                 MaterialAlreadyExists |
                 InvalidQuantity |
                 MaterialNotFound e) {
            // Handle exceptions that might arise during operations
            throw new RuntimeException(e);
        }
    }
}
