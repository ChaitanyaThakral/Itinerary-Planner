package model;

public class Budget {
    private double currentExpenditure;
    private double budgetLimit;

    //EFFECTS: constructs a budget with its limit
    // and expenditure of what one has spent till now.
    public Budget(){
        //stub
    }

    //EFFETCS: return the amount of money spent till now.
    public double getCurrentExpenditure(){
        return 0.0;
    }

    //EFFETCS: retun the budget limit set for the activity.
    public double getBudgetLimit(){
        return 0.0;
    }

    //MODIFIES:this
    //EFFECTS: increase the budget limit by the given amount.
    public void increaseBudgetLimit(double amount){
        //stub
    }

    //MODIFIES:this
    //EFFECTS: increase the current expenditure by the given amount.
    public void increaseCurrentExpenditure(double amount){
        //stub
    }

    //EFFECTS: check if the user has exceeded the limit set for the activity.
    public Boolean checkBudget(){
        return false;
    }
}
