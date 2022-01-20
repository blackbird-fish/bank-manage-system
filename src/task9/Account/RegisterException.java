package task9.Account;
public class RegisterException extends Exception{
    public RegisterException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}