import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.warehouse.exceptions.ExceedingAmountOfGems;
import org.warehouse.exceptions.ExceedingAmountOfMoney;
import org.warehouse.exceptions.InvalidQuantityOfGems;
import org.warehouse.exceptions.InvalidQuantityOfMoney;
import org.warehouse.management.WareHouse;
import org.warehouse.model.user.User;
import org.warehouse.model.user.UserFunctionalities;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestUserFunctionality {

    // Class variables for UserFunctionalities and User instances
    private UserFunctionalities functionalities;
    private User user;

    // Set up the initial state before each test
    @BeforeEach
    void setUp() {
        // Initialize a warehouse for the user and add it to a map
        Map<Integer, WareHouse> wareHouses = new HashMap<>();
        WareHouse wareHouse = new WareHouse(new HashMap<>());
        wareHouses.put(1, wareHouse);

        // Create a user with initial gems, money, and level
        user = new User("User1", wareHouses, 5, 100, 45);

        // Initialize UserFunctionalities with the created user
        functionalities = new UserFunctionalities(user);
    }

    // Test the upgradeLevel method for UserFunctionalities
    @Test
    void testUpgradeLevel() {
        // Check that the initial level is as expected
        assertEquals(45, user.getLevel());

        // Call setLevel() to upgrade the user level
        user.setLevel();

        // Assert that the level has increased by one
        assertEquals(46, user.getLevel());
    }

    // Test updating money with an invalid quantity (should throw an exception)
    @Test
    void testUpdateMoneyFailed() {
        assertThrows(InvalidQuantityOfMoney.class, () -> functionalities.updateMoney(0));
    }

    // Test updating money with a valid quantity (should work correctly)
    @Test
    void testUpdateMoneySuccess() throws InvalidQuantityOfMoney {
        assertEquals(1100, functionalities.updateMoney(1000)); // Expected updated money after adding 1000
    }

    // Test updating gems with an invalid quantity (should throw an exception)
    @Test
    void testUpdateGemsFailed() {
        assertThrows(InvalidQuantityOfGems.class, () -> functionalities.updateGems(0));
    }

    // Test updating gems with a valid quantity (should work correctly)
    @Test
    void testUpdateGemsSuccess() throws InvalidQuantityOfGems {
        assertEquals(40, functionalities.updateGems(35)); // Expected total after adding 35 gems
    }

    // Test spending money with invalid or exceeding amounts (should throw exceptions)
    @Test
    void testSpendMoneyFailed() {
        Assertions.assertThrows(InvalidQuantityOfMoney.class, () -> functionalities.spendMoney(0));
        Assertions.assertThrows(ExceedingAmountOfMoney.class, () -> functionalities.spendMoney(150));
    }

    // Test spending money with valid amounts (should work correctly)
    @Test
    void testSpendMoneySuccess() throws InvalidQuantityOfMoney, ExceedingAmountOfMoney {
        functionalities.updateMoney(500); // Add 500 to the initial money
        int remaining = functionalities.spendMoney(10); // Spend 10 units
        assertEquals(590, remaining); // Check remaining balance
    }

    // Test spending gems with invalid or exceeding amounts (should throw exceptions)
    @Test
    void testSpendGemsFailed() {
        Assertions.assertThrows(InvalidQuantityOfGems.class, () -> functionalities.spendGems(0));
        Assertions.assertThrows(ExceedingAmountOfGems.class, () -> functionalities.spendGems(15000));
    }

    // Test spending gems with valid amounts (should work correctly)
    @Test
    void testSpendGemsSuccess() throws InvalidQuantityOfGems, ExceedingAmountOfGems {
        functionalities.updateGems(350); // Add 350 to the initial gems
        int remaining = functionalities.spendGems(150); // Spend 150 gems
        assertEquals(205, remaining); // Check remaining balance
    }
}
