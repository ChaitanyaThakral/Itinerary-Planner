package model;

/* Represents a budget with a budget limit the amount the person has decided to
spend on a particualr activity and current Expenditure the amount the user has already spent on the activity.
Budget has currentExpenditure: the total amount of money user has spent on the activity so far,
 *  budgetLimit: the maximum amount of money limit for a activity. */
public class Budget {
    private double currentExpenditure;
    private double budgetLimit;

    //EFFECTS: constructs a budget with its limit
    // and expenditure of what one has spent till now(intial value at 0.0).
    public Budget(double budgetLimit,double currentExpenditure) {
        this.budgetLimit = budgetLimit;          
        this.currentExpenditure = currentExpenditure;  
    }

    //EFFETCS: return the amount of money spent till now.
    public double getCurrentExpenditure() {
        return this.currentExpenditure;
    }

    //EFFETCS: retun the budget limit set for the activity.
    public double getBudgetLimit() {
        return this.budgetLimit;
    }

    //MODIFIES:this
    //EFFECTS: increase the budget limit by the given amount.
    public void increaseBudgetLimit(double amount) {
        this.budgetLimit = this.budgetLimit + amount;
    }

    //MODIFIES:this
    //EFFECTS: increase the current expenditure by the given amount.
    public void increaseCurrentExpenditure(double amount) {
        this.currentExpenditure = this.currentExpenditure + amount;
    }

    //EFFECTS: check if the user has exceeded the limit set for the activity.
    //If budget is exceeded it returns true, otherwise returns false. 
    public Boolean budgetExceed() {
        if (this.currentExpenditure > this.budgetLimit) {
            return true; 
        } else {
            return false;
        }
    }
}
