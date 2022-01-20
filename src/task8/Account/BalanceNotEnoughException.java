package task8.Account;
public class BalanceNotEnoughException extends Exception{
    public BalanceNotEnoughException (String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}