package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActivityTest {
    private Activity a1;
    private Activity a2;
    private Budget b1;

    @BeforeEach
    void runBefore() {
        b1 = new Budget(1000, 0);
        a1 = new Activity("Surfing", "Vancouver", "2024-09-10",
                60, "10:00 AM", "Did surfing with family", 100.0, true, b1);
        a2 = new Activity("Eating", "Vancouver", "2024-09-10",
                20, "11:00 AM", "Ate Noodles with family", 30.0, true, new Budget(10, 0));
    }

    @Test
    public void testSetActivityName() {
        a1.setActivityName("Swimming");
        assertEquals("Swimming", a1.getActivityName());
        a1.setActivityName("Boating");
        assertEquals("Boating", a1.getActivityName());
    }

    @Test
    public void testSetLocation() {
        a1.setLocation("Delhi");
        assertEquals("Delhi", a1.getLocation());
    }

    @Test
    public void testSetDate() {
        a1.setDate("2024-09-11");
        assertEquals("2024-09-11", a1.getDate());
    }

    @Test
    public void testSetDuration() {
        a1.setDuration(100);
        assertEquals(100, a1.getDuration());
    }

    @Test
    public void testSetTime() {
        a1.setTime("11:00 AM");
        assertEquals("11:00 AM", a1.getTime());
    }

    @Test
    public void testSetDescription() {
        a1.setDescription("Went to Surf");
        assertEquals("Went to Surf", a1.getDescription());
    }

    @Test
    public void testSetCost() {
        a1.setCost(90.00);
        assertEquals(90.00, a1.getCost());
    }

    @Test
    public void testSetStatus() {
        a1.setStatus(false);
        assertFalse(a1.getStatus());

        a1.setStatus(true);
        assertTrue(a1.getStatus());
    }

    @Test
    public void testBudgetCheck() {
        assertTrue(a1.budgetCheck());
        assertFalse(a2.budgetCheck());
    }

    @Test
    public void testGetBudget() {
        assertEquals(b1, a1.getBudget());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testToJson(){
        JSONObject aJsonObject = a1.toJson();
        
        assertEquals("Surfing",aJsonObject.getString("activityName"));
        assertEquals("Vancouver",aJsonObject.getString("location"));
        assertEquals("2024-09-10",aJsonObject.getString("date"));
        assertEquals(60,aJsonObject.getInt("duration"));
        assertEquals("10:00 AM",aJsonObject.getString("time"));
        assertEquals("Did surfing with family",aJsonObject.getString("description"));
        assertEquals(100.0, aJsonObject.getDouble("cost"));
        assertTrue(aJsonObject.getBoolean("status"));

        JSONObject budgetObject = aJsonObject.getJSONObject("budget");
        assertEquals(1000,budgetObject.getDouble("budgetLimit"));
        assertEquals(0,budgetObject.getDouble("currentExpenditure"));

    }

}
