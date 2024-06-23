package Problems.SplitWise.App.Group;

import Problems.SplitWise.App.Expense.Expense;
import Problems.SplitWise.App.Expense.ExpenseController;
import Problems.SplitWise.App.Expense.ExpenseSplitType;
import Problems.SplitWise.App.Expense.Split.Split;
import Problems.SplitWise.App.User.User;
import Problems.SplitWise.SimplifyAlgorithm.Debt;
import Problems.SplitWise.SimplifyAlgorithm.SplitwiseDebtSimplificationMaxFlow;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private List<User> groupMembers;
    private List<Expense> expenseList;
    private ExpenseController expenseController;
    private List<Debt> debts;

    Group() {
        groupMembers = new ArrayList<>();
        expenseList = new ArrayList<>();
        expenseController = new ExpenseController();
    }

    public void addMember(User member) {
        groupMembers.add(member);
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount,
                                 List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser) {

        Expense expense = expenseController.createExpense(expenseId, description, expenseAmount, splitDetails, splitType, paidByUser);
        expenseList.add(expense);
        for(Split split : splitDetails)
            debts.add(new Debt(paidByUser.getUserName(), split.getUser().getUserName(), split.getAmountOwe()));
        return expense;
    }

    public void displaySimplifiedDebts() {
        List<Debt> simplifiedDebts = SplitwiseDebtSimplificationMaxFlow.simplifyDebts(debts);

        System.out.println("Simplified debts:");
        for (Debt debt : simplifiedDebts) {
            System.out.println(debt.getDebtor() + " owes " + debt.getCreditor() + " " + debt.getAmount() + " Rupees");
        }
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public ExpenseController getExpenseController() {
        return expenseController;
    }

    public void setExpenseController(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }
}
