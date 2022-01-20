package task6;
public class ATMException extends Exception{
    public ATMException() {
    }
}
class BalanceNotEnoughException extends ATMException{
    public BalanceNotEnoughException() {
        System.out.println("BanlanceNotEnoough");
    }
}
class RegisterException extends ATMException{
    public RegisterException() {
    }
}
class LoginException extends ATMException{
    public LoginException() {
    }
}
class LoanException extends ATMException{
    public LoanException() {
    }
}