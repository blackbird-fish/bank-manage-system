package task10.Exception;

public class  LoanException extends Exception{
	private static final long serialVersionUID = 1L;
	public LoanException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}