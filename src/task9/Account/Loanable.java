package task9.Account;
public interface Loanable{
    void requestLoan(double money) throws LoanException;
    void payLoan(double money) throws LoanException, BalanceNotEnoughException;
    double getLoan();
}

