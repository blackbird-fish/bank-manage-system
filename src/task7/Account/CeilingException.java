package task7.Account;
public class  CeilingException extends Exception{
    public CeilingException(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}