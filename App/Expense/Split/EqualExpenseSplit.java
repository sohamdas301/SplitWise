package Problems.SplitWise.App.Expense.Split;

import java.util.List;

public class EqualExpenseSplit implements ExpenseSplit{
    @Override
    public void validateSplitRequest(List<Split> splitDetails, double expenseAmount) {
        double amountShouldBePresent = expenseAmount/splitDetails.size();
        for(Split split: splitDetails) {
            if(split.getAmountOwe() != amountShouldBePresent) {
//                throw new Exception("Invalid Spilt Value");
            }
        }
    }
}
