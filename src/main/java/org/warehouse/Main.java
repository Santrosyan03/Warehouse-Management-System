package org.warehouse;

import org.warehouse.exceptions.*;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.Material;
import org.warehouse.model.material.MaterialType;
import org.warehouse.model.user.User;
import org.warehouse.model.user.UserFunctionalities;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MaterialType ironType = new MaterialType("Iron", "Metal used in construction", "src/main/resources/materials/iron.png", 1000);
        MaterialType coalType = new MaterialType("Coal", "Used for energy production", "src/main/resources/materials/coal.png", 1000);
        Material iron = new Material(ironType, 100);
        Material coal = new Material(coalType, 100);
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
        User user1 = new User("User1", user1_warehouses, 5, 100, 1);
        User user2 = new User("User2", user2_warehouses, 10, 200, 3);
        UserFunctionalities userFunctionalities1 = new UserFunctionalities(user1);
        UserFunctionalities userFunctionalities2 = new UserFunctionalities(user2);

        try {
            // Warehouse 1
            user1_warehouses.get(1).addMaterial(iron, 10);
            user1_warehouses.get(1).addMaterial(coal, 15);

            user1_warehouses.get(1).updateMaterialQuantity(iron, 20);
            user1_warehouses.get(1).updateMaterialQuantity(coal, 40);

            user1_warehouses.get(1).removeMaterial(iron);
            user1_warehouses.get(1).removeMaterial(coal);

            user1_warehouses.get(1).addMaterial(iron, 10);
            user1_warehouses.get(1).addMaterial(coal, 15);

            user1_warehouses.get(1).dropSomeQuantity(iron, 1);
            user1_warehouses.get(1).dropSomeQuantity(coal, 4);

            user1_warehouses.get(1).transferFullMaterial(user1_warehouses.get(2), iron);
            user1_warehouses.get(1).transferFullMaterial(user1_warehouses.get(2), coal);

            user1_warehouses.get(2).transferSomeQuantityOfMaterial(user1_warehouses.get(1), iron, 1);
            user1_warehouses.get(2).transferSomeQuantityOfMaterial(user1_warehouses.get(1), coal, 1);

            user1_warehouses.get(1).listAllMaterials();
            user1_warehouses.get(2).listAllMaterials();

            user1_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), iron);
            user1_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), coal);

            user1_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), iron);
            user1_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), coal);

            System.out.println("---------------------------------------------");

            // Warehouse 2

            user2_warehouses.get(1).addMaterial(iron, 10);
            user2_warehouses.get(1).addMaterial(coal, 15);

            user2_warehouses.get(1).updateMaterialQuantity(iron, 20);
            user2_warehouses.get(1).updateMaterialQuantity(coal, 40);

            user2_warehouses.get(1).removeMaterial(iron);
            user2_warehouses.get(1).removeMaterial(coal);

            user2_warehouses.get(1).addMaterial(iron, 10);
            user2_warehouses.get(1).addMaterial(coal, 15);

            user2_warehouses.get(1).dropSomeQuantity(iron, 1);
            user2_warehouses.get(1).dropSomeQuantity(coal, 4);

            user2_warehouses.get(1).transferFullMaterial(user2_warehouses.get(2), iron);
            user2_warehouses.get(1).transferFullMaterial(user2_warehouses.get(2), coal);

            user2_warehouses.get(2).transferSomeQuantityOfMaterial(user2_warehouses.get(1), iron, 1);
            user2_warehouses.get(2).transferSomeQuantityOfMaterial(user2_warehouses.get(1), coal, 1);

            user2_warehouses.get(1).listAllMaterials();
            user2_warehouses.get(2).listAllMaterials();

            user2_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), iron);
            user2_warehouses.get(1).getMaterialQuantity(user1_warehouses.get(1), coal);

            user2_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), iron);
            user2_warehouses.get(2).getMaterialQuantity(user1_warehouses.get(2), coal);

            System.out.println("---------------------------------------------");


            // User 1
            userFunctionalities1.updateMoney(100);
            System.out.println("User1's money: " + user1.getMoney());
            userFunctionalities1.updateGems(10);
            System.out.println("User1's gems: " + user1.getGems());
            userFunctionalities1.upgradeLevel();
            System.out.println("User1's level: " + user1.getLevel());

            userFunctionalities1.checkInventory(user1_warehouses.get(1), iron);
            userFunctionalities1.checkInventory(user1_warehouses.get(1), coal);
            userFunctionalities1.checkInventory(user1_warehouses.get(2), iron);
            userFunctionalities1.checkInventory(user1_warehouses.get(2), coal);

            userFunctionalities1.spendMoney(50);
            System.out.println("User1's money: " + user1.getMoney());
            userFunctionalities1.spendGems(5);
            System.out.println("User1's gems: " + user1.getGems());

            // User 2
            userFunctionalities2.updateMoney(432);
            System.out.println("User2's money: " + user2.getMoney());
            userFunctionalities2.updateGems(27);
            System.out.println("User2's gems: " + user2.getGems());
            userFunctionalities2.upgradeLevel();
            System.out.println("User2's level: " + user2.getLevel());

            userFunctionalities2.checkInventory(user2_warehouses.get(1), iron);
            userFunctionalities2.checkInventory(user2_warehouses.get(1), coal);
            userFunctionalities2.checkInventory(user2_warehouses.get(2), iron);
            userFunctionalities2.checkInventory(user2_warehouses.get(2), coal);

            userFunctionalities2.spendMoney(40);
            System.out.println("User2's money: " + user2.getMoney());
            userFunctionalities2.spendGems(18);
            System.out.println("User2's gems: " + user2.getGems());

        } catch (InvalidQuantityOfMoney |
                 InvalidQuantityOfGems |
                 ExceedingAmountOfMoney |
                 ExceedingAmountOfGems |
                 ExceedingCapacity |
                 MaterialAlreadyExists |
                 InvalidQuantity |
                 MaterialNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
