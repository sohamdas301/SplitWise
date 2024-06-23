package Problems.SplitWise.App.Expense.Split;

import Problems.SplitWise.App.Expense.ExpenseSplitType;

public class SplitFactory {
    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualExpenseSplit();
            case UNEQUAL -> new UnequalExpenseSplit();
            case PERCENTAGE -> new PercentageExpenseSplit();
            default -> null;
        };
    }

}
