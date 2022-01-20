package task10.Exception;
public class LoginException extends Exception{
	private static final long serialVersionUID = 1L;
	public LoginException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}