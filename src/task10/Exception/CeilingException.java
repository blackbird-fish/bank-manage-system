package task10.Exception;
public class  CeilingException extends Exception{
	private static final long serialVersionUID = 1L;
	public CeilingException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}