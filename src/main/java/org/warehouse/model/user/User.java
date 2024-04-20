package org.warehouse.model.user;

import org.warehouse.management.WareHouse;

import java.util.Map;

public class User {
    private String userName;
    private Map<Integer, WareHouse> assignedWarehouses;
    private int gems;
    private int money;
    private int level;

    public User(String userName, Map<Integer, WareHouse> warehouses, int gems, int money, int level) {
        this.userName = userName;
        this.assignedWarehouses = warehouses;
        this.gems = gems;
        this.money = money;
        this.level = level;
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        this.level += 1;
    }

    public Map<Integer, WareHouse> getAssignedWarehouses() {
        return assignedWarehouses;
    }

    public void setAssignedWarehouses(Map<Integer, WareHouse> assignedWarehouses) {
        this.assignedWarehouses = assignedWarehouses;
    }
}
