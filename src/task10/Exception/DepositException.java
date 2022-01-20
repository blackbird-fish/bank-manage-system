package task10.Exception;
public class DepositException extends Exception{
	private static final long serialVersionUID = 1L;
	public DepositException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}