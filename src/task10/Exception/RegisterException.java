package task10.Exception;
public class RegisterException extends Exception{
	private static final long serialVersionUID = 1L;
	public RegisterException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}