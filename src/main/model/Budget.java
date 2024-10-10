package model;

public class Budget {
    private double currentExpenditure;
    private double budgetLimit;

    //EFFECTS: constructs a budget with its limit
    // and expenditure of what one has spent till now(intial value at 0.0).
    public Budget(double budgetLimit,double currentExpenditure){
        this.budgetLimit=budgetLimit;
        this.currentExpenditure=currentExpenditure;
    }

    //EFFETCS: return the amount of money spent till now.
    public double getCurrentExpenditure(){
        return this.currentExpenditure;
    }

    //EFFETCS: retun the budget limit set for the activity.
    public double getBudgetLimit(){
        return this.budgetLimit;
    }

    //MODIFIES:this
    //EFFECTS: increase the budget limit by the given amount.
    public void increaseBudgetLimit(double amount){
        this.budgetLimit=this.budgetLimit+amount;
    }

    //MODIFIES:this
    //EFFECTS: increase the current expenditure by the given amount.
    public void increaseCurrentExpenditure(double amount){
       this.currentExpenditure=this.currentExpenditure+amount;
    }

    //EFFECTS: check if the user has exceeded the limit set for the activity.
    //If budget is exceeded it returns true.
    public Boolean budgetExceed(){
        if (this.currentExpenditure>this.budgetLimit){
            return true;
        }
        else{
            return false;
        }
    }
}
