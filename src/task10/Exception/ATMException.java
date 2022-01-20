package task10.Exception;
public class ATMException extends Exception{
	private static final long serialVersionUID = 1L;
	public ATMException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}