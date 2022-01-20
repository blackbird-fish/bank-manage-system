package task7.Account;

public class SavingAccount extends Account{
    public SavingAccount() {
    }
    public SavingAccount(String password, String name, String personld, String email, double balance) {
        super(password, name, personld, email, balance);
    }
    /* abstract 实现    如果取款金额大于余额返回null*/
    public Account withdraw(double balance) throws BalanceNotEnoughException{
        if(super.getBalance()>=balance){
            super.setBalance(super.getBalance()-balance);    
        }else{
                throw (new BalanceNotEnoughException("余额不足异常"));
        }
        return this;
    }
    @Override
    public String toString() {
        String id = Long.toString(super.getId());
        return "SavingAccoount  "+"id:"+id+"name"+super.getName();
    }
     /*euqals    账户类型且id相同的视为相同对象*/
     public boolean equals(Object obj) {
        if(obj instanceof SavingAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
    
}
