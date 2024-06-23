package Problems.SplitWise.App.User;

import Problems.SplitWise.App.Expense.UserExpenseBalanceSheet;

public class User {
    String userId;
    String userName;
    UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String id, String userName) {
        this.userId = id;
        this.userName = userName;
        userExpenseBalanceSheet = new UserExpenseBalanceSheet();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserExpenseBalanceSheet(UserExpenseBalanceSheet userExpenseBalanceSheet) {
        this.userExpenseBalanceSheet = userExpenseBalanceSheet;
    }

    public UserExpenseBalanceSheet getUserExpenseBalanceSheet() {
        return userExpenseBalanceSheet;
    }

}
