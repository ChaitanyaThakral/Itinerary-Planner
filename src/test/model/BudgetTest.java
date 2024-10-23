package model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

public class BudgetTest {
    private Budget budget;

    @BeforeEach
    void runBefore() {
        budget = new Budget(1000.0, 0.0);
    }

    @Test
    public void testIncreaseBudgetLimit() {
        budget.increaseBudgetLimit(100);
        assertEquals(1100, budget.getBudgetLimit());
    }

    @Test
    public void testIncreaseCurrentExpenditure() {
        budget.increaseCurrentExpenditure(100);
        assertEquals(100, budget.getCurrentExpenditure());
    }

    @Test
    public void testbudgetExceed() {
        assertFalse(budget.budgetExceed());
        budget.increaseCurrentExpenditure(2000);
        assertTrue(budget.budgetExceed());
    }


    @Test
    public void testToJson(){
        JSONObject budgetJsonObject = budget.toJson();

       
        
        assertEquals(1000.0,budgetJsonObject.getDouble("budgetLimit"));
        assertEquals(0.0,budgetJsonObject.getDouble("currentExpenditure"));

    }
}
