package Problems.SplitWise.App.Expense;

import Problems.SplitWise.App.Expense.Split.Split;
import Problems.SplitWise.App.User.User;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseId;
    private String description;
    private double amount;
    private User paidBy;
    private ExpenseSplitType splitType;
    private List<Split> splitDetails = new ArrayList<>();

    public Expense(String expenseId, String description, double amount, User paidBy, ExpenseSplitType splitType, List<Split> splitDetails) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitType = splitType;
        this.splitDetails = splitDetails;
    }
    public String getExpenseId() {
        return expenseId;
    }
    public String getDescription() {
        return description;
    }
    public double getAmount() {
        return amount;
    }
    public User getPaidBy() {
        return paidBy;
    }
    public ExpenseSplitType getSplitType() {
        return splitType;
    }
    public List<Split> getSplitDetails() {
        return splitDetails;
    }
}
