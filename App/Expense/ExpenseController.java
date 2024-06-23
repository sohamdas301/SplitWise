package Problems.SplitWise.App.Expense;

import Problems.SplitWise.App.Balance.BalanceSheetController;
import Problems.SplitWise.App.Expense.Split.ExpenseSplit;
import Problems.SplitWise.App.Expense.Split.Split;
import Problems.SplitWise.App.Expense.Split.SplitFactory;
import Problems.SplitWise.App.User.User;

import java.util.List;

public class ExpenseController {
//    BalanceSheetController balanceSheetController;
//
//    public ExpenseController() {
//        balanceSheetController = new BalanceSheetController();
//    }

    public Expense createExpense(String expenseId, String description, double expenseAmount,
                                 List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser) {

        ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
        expenseSplit.validateSplitRequest(splitDetails, expenseAmount);
        Expense expense = new Expense(expenseId, description, expenseAmount, paidByUser, splitType, splitDetails);
        BalanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);
        return expense;
    }
}
