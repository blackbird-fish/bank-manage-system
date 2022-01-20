package task7.Account;
public class CreditAccount extends Account{
    private double ceiling=10000.0;
    public CreditAccount() {
    }
    public CreditAccount(String password, String name, String personld, String email, double balance) {
        super (password, name, personld, email, balance);
    }
    public double getCeiling() {
        return ceiling;
    }
    public void setCeiling(double ceiling) throws CeilingException{
        if(ceiling>=0){
            this.ceiling = ceiling;
        }
        else{
            throw(new CeilingException("设置信用额度异常"));
        }
    }
    /* abstract 实现   信用账户可以透支但有透支额度*/
    public CreditAccount withdraw(double money) throws BalanceNotEnoughException{
        if(super.getBalance()-money+this.getCeiling()>=0){
            super.setBalance(super.getBalance()-money);
            
        }else{
                throw (new BalanceNotEnoughException("余额不足异常"));
        }
        return this;
     }
     @Override
    public String toString() {
        String id = Long.toString(super.getId());
        return "CreditAccoount  "+"id:"+id+"name"+super.getName();
    }
    @Override
    /*euqals    账户类型且id相同的视为相同对象*/
    public boolean equals(Object obj) {
        if(obj instanceof CreditAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
    }
