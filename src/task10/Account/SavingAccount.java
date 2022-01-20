package task10.Account;

import java.io.IOException;
import task10.Exception.*;

public class SavingAccount extends Account {
	private static final long serialVersionUID = 1L;
	public SavingAccount() {
    }
    public SavingAccount(String password, String name, String personld, String email, double balance) throws IOException {
        super(password, name, personld, email, balance);
    }
    /* abstract */
    public Account withdraw(double balance) throws BalanceNotEnoughException{
        if(balance>0&&super.getBalance()>=balance){
            super.setBalance(super.getBalance()-balance);    
        }else{
                throw (new BalanceNotEnoughException("Óà¶î²»×ã"));
        }
        return this;
    }
    @Override
   /* public String toString() {
        String id = Long.toString(super.getId());
        return "SavingAccoount  "+"id:"+id+"name"+super.getName();
    }*/
     /*euqals*/
     public boolean equals(Object obj) {
        if(obj instanceof SavingAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
    
}
