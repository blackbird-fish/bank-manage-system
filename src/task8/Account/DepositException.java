package task8.Account;
public class DepositException extends Exception{
    public DepositException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}