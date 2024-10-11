package model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

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

}
