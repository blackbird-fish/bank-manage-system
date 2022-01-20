package task8.Account;
public class NotfoundAccount extends Exception{
    public NotfoundAccount(String message){
		super(message) ;
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}