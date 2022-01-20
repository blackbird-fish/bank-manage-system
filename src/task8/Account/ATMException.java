package task8.Account;
public class ATMException extends Exception{
    public ATMException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}