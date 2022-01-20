package task7.Account;
public class LoginException extends Exception{
    public LoginException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}