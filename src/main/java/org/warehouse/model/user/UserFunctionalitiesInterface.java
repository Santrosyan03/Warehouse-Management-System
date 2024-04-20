package org.warehouse.model.user;

import org.warehouse.exceptions.ExceedingAmountOfMoney;
import org.warehouse.exceptions.InvalidQuantityOfGems;
import org.warehouse.exceptions.ExceedingAmountOfGems;
import org.warehouse.exceptions.InvalidQuantityOfMoney;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.Material;

/**
 * This interface defines user-related functionalities for a warehouse management system.
 * It includes operations for user level management, inventory checks, and money/gem transactions.
 */
public interface UserFunctionalitiesInterface {

    /**
     * Upgrades the user's level by incrementing it.
     */
    void upgradeLevel();

    /**
     * Checks and displays the inventory quantity of a specified material in a designated warehouse.
     *
     * @param assignedWarehouse The warehouse from which to check the inventory.
     *                          This warehouse object must contain the inventory data for various materials.
     * @param material The material for which the inventory needs to be checked.
     *             This specifies which material's stock level is being queried.
     */
    void checkInventory(WareHouse assignedWarehouse, Material material);

    /**
     * Increases the user's money by a specified amount.
     *
     * @param quantity The amount of money to be added to the user's current total.
     *                 Must be greater than 0 to be considered valid.
     * @return The new total amount of money the user has after the addition.
     * @throws InvalidQuantityOfMoney If the specified quantity is less than or equal to zero,
     *                                indicating an attempt to add a non-positive amount.
     */
    int updateMoney(int quantity) throws InvalidQuantityOfMoney;

    /**
     * Increases the user's gems by a specified amount.
     *
     * @param quantity The amount of gems to add to the user's current total.
     *                 The quantity must be greater than 0 to be valid.
     * @return The new total amount of gems the user has after the addition.
     * @throws InvalidQuantityOfGems If the specified quantity is less than or equal to zero,
     *                               indicating an attempt to add a non-positive amount of gems.
     */
    int updateGems(int quantity) throws InvalidQuantityOfGems;

    /**
     * Deducts a specified amount of money from the user's balance if valid.
     * @param quantity The amount of money to spend.
     * @return the remaining amount of money after the deduction.
     * @throws InvalidQuantityOfMoney if the specified quantity is zero or negative.
     * @throws ExceedingAmountOfMoney if the specified quantity exceeds the user's current balance.
     */
    int spendMoney(int quantity) throws InvalidQuantityOfMoney, ExceedingAmountOfMoney;

    /**
     * Deducts a specified amount of gems from the user's balance if valid.
     * @param quantity The amount of gems to spend.
     * @return the remaining amount of gems after the deduction.
     * @throws InvalidQuantityOfGems if the specified quantity is zero or negative.
     * @throws ExceedingAmountOfGems if the specified quantity exceeds the user's current gem balance.
     */
    int spendGems(int quantity) throws ExceedingAmountOfGems, InvalidQuantityOfGems;
}
