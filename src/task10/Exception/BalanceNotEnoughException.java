package task10.Exception;
public class BalanceNotEnoughException extends Exception{
	private static final long serialVersionUID = 1L;
	public BalanceNotEnoughException (String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}