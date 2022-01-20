package task9.Account;

public class  LoanException extends Exception{
    public LoanException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}