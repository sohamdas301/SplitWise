package Problems.SplitWise.App.Expense.Split;

import java.util.List;

public interface ExpenseSplit {
    void validateSplitRequest(List<Split> splitDetails, double expenseAmount);
}
