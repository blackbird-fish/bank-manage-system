package task10.Account;
import task10.Exception.*;
public interface Loanable{
    void requestLoan(double money) throws LoanException;
    void payLoan(double money) throws LoanException, BalanceNotEnoughException;
    double getLoan();
}

