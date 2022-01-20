package task10.Exception;
public class NotfoundAccount extends Exception{
	private static final long serialVersionUID = 1L;
	public NotfoundAccount(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}