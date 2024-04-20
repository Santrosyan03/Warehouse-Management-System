package org.warehouse.model.user;

import org.warehouse.management.WareHouse;

import java.util.Map;

/**
 * Represents a user in a warehouse management system.
 * A user has a username, gems, money, level, and a collection of assigned warehouses.
 */
public class User {
    private String userName;  // The name of the user.
    private Map<Integer, WareHouse> assignedWarehouses;  // Warehouses assigned to this user.
    private int gems;  // The number of gems owned by the user.
    private int money;  // The amount of money owned by the user.
    private int level;  // The level of the user.

    /**
     * Constructs a User object with the specified attributes.
     *
     * @param userName The name of the user.
     * @param warehouses The warehouses assigned to the user.
     * @param gems The number of gems owned by the user.
     * @param money The amount of money owned by the user.
     * @param level The initial level of the user.
     */
    public User(String userName, Map<Integer, WareHouse> warehouses, int gems, int money, int level) {
        this.userName = userName;
        this.assignedWarehouses = warehouses;
        this.gems = gems;
        this.money = money;
        this.level = level;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return this.userName;
    }

    /**
     * Sets a new username for the user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.userName = username;
    }

    /**
     * Gets the number of gems owned by the user.
     *
     * @return The number of gems.
     */
    public int getGems() {
        return gems;
    }

    /**
     * Sets the number of gems for the user.
     *
     * @param gems The new number of gems.
     */
    public void setGems(int gems) {
        this.gems = gems;
    }

    /**
     * Gets the amount of money owned by the user.
     *
     * @return The amount of money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets a new amount of money for the user.
     *
     * @param money The new amount of money.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the current level of the user.
     *
     * @return The level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increments the user's level.
     */
    public void setLevel() {
        this.level += 1;
    }

    /**
     * Gets the map of warehouses assigned to the user.
     *
     * @return The map of assigned warehouses.
     */
    public Map<Integer, WareHouse> getAssignedWarehouses() {
        return assignedWarehouses;
    }

    /**
     * Sets the assigned warehouses for the user.
     *
     * @param assignedWarehouses The new map of assigned warehouses.
     */
    public void setAssignedWarehouses(Map<Integer, WareHouse> assignedWarehouses) {
        this.assignedWarehouses = assignedWarehouses;
    }
}
