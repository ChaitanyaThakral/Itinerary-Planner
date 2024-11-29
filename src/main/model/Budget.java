package model;

import org.json.JSONObject;

import persistence.Writeable;

/* Represents a budget with a budget limit the amount the person has decided to
spend on a particualr activity and current Expenditure the amount the user has already spent on the activity.
Budget has currentExpenditure: the total amount of money user has spent on the activity so far,
 *  budgetLimit: the maximum amount of money limit for a activity. */

public class Budget implements Writeable {
    private double currentExpenditure;
    private double budgetLimit;

    // EFFECTS: constructs a budget with its limit
    // and expenditure of what one has spent till now(intial value at 0.0).
    public Budget(double budgetLimit, double currentExpenditure) {
        this.budgetLimit = budgetLimit;
        this.currentExpenditure = currentExpenditure;
    }

    // EFFETCS: return the amount of money spent till now.
    public double getCurrentExpenditure() {

        return this.currentExpenditure;

    }

    // EFFETCS: retun the budget limit set for the activity.
    public double getBudgetLimit() {
        return this.budgetLimit;
    }

    // MODIFIES:this
    // EFFECTS: increase the budget limit by the given amount.
    public void increaseBudgetLimit(double amount) {
        this.budgetLimit = this.budgetLimit + amount;
    }

    // MODIFIES:this
    // EFFECTS: increase the current expenditure by the given amount.
    public void increaseCurrentExpenditure(double amount) {
        this.currentExpenditure = this.currentExpenditure + amount;
    }

    // EFFECTS: check if the user has exceeded the limit set for the activity.
    // If budget is exceeded it returns true, otherwise returns false.
    public Boolean budgetExceed() {
        loggingBudgetAnalysis();
        if (this.currentExpenditure > this.budgetLimit) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: the Budget instance should not be null.
    // EFFECTS: returns a JSONObject representation of this Budget instance
    // with budgetLimit and CurrentExpenditure.
    @Override
    public JSONObject toJson() {
        JSONObject budgetObject = new JSONObject();

        budgetObject.put("budgetLimit", this.budgetLimit);
        budgetObject.put("currentExpenditure", this.currentExpenditure);

        return budgetObject;
    }

    // REQUIRES: a budget has already been set up with appropriate inputs.
    // MODIFIES:EventLog instance by adding event.
    // EFFECTS: Log an event to the event log about the Budget analysis with an
    // appropriate detailed message conveying the same
    public void loggingBudgetAnalysis() {
        String logDetailBudget = "The Budget Analysis for the requested Day with Budget limit and Current Expenditure was displayed ";
        EventLog.getInstance().logEvent(new Event(logDetailBudget));
    }

}
