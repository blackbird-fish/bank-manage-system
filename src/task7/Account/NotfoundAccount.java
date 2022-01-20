package task7.Account;
public class NotfoundAccount extends Exception{
    public NotfoundAccount(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}