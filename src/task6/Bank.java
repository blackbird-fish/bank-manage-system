package task6;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Bank {
    Set<Account> set=new HashSet<Account>();
    /* 单例模式*/
    private final static Bank bank=new Bank();
    private Bank(){}
    public static Bank getBank() {
		return bank;
	}
    /*功能 register*/
    public Account register(String password, String name, String personld, String email, int accounttype) throws RegisterException{
        switch(accounttype){
           case 1:Account account1=new SavingAccount(password, name, personld, email, 0.0);set.add(account1);return account1;
           case 2:Account account2=new CreditAccount(password, name, personld, email, 0.0);set.add(account2);return account2;
           case 3:Account account3=new LoanSavingAccount(password, name, personld, email, 0.0);set.add(account3);return account3;
           case 4:Account account4=new LoanCreditAccount(password, name, personld, email, 0.0);set.add(account4);return account4;
        }
        return null;
              
    }
    /* 登录  login*/
    public Account login (long id,String password)throws LoginException{
       Iterator<Account>it=set.iterator();
       while(it.hasNext()){
           Account account=(Account)it.next();
           if(account.getId()==id&&account.getPassword().equals(password)){
               return account;
           }
           else{
               throw new LoginException();
           }
       }
       return null;
    }
    /* bank  deposit 存款*/
    public Account deposit(long id,double money){
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            Account account=it.next();
            if(account.getId()==id){
                account.deposit(money);
                return account;
            }
        }
        return null;
    }
    /*bank withdraw 取款 */
    public Account withdraw(long id,double money){
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            Account account=it.next();
            if(account.getId()==id){
                try {
                    account.withdraw(money);
                } catch (BalanceNotEnoughException e) {
                    e.printStackTrace();
                }
                return account;
            }
        }
        return null;
    }
    /* bank 设置透支金额 setCeiling*/
    public Account setCeiling(long id,double newceiling){
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            Account account=it.next();
            CreditAccount account1=new CreditAccount();
            if(account.getId()==id&&account instanceof CreditAccount){
                account1=(CreditAccount)account;
                account1.setCeiling(newceiling);
                return account1;
            }
        }
        return null;
    }
    /*bank  requestloan  参数 id money  返回 account*/
    public Account requestLoan(long id,double money){
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            LoanCreditAccount account1=new LoanCreditAccount();
            LoanSavingAccount account2=new LoanSavingAccount();
            Account account=it.next();
            if(account.getId()==id&&account instanceof LoanCreditAccount){
                account1=(LoanCreditAccount)account;
                account1.requestLoan(money);
                return account1;
            }
            else if(account.getId()==id&&account instanceof LoanSavingAccount){
                account2=(LoanSavingAccount)account;
                account2.requestLoan(money);
                return account2;
            }
        }
        return null;
    }
    /*bank  payLoan 参数 id money 返回 account*/
    public Account PayLoan(long id,double money){
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            LoanCreditAccount account1=new LoanCreditAccount();
            LoanSavingAccount account2=new LoanSavingAccount();
            Account account=it.next();
            if(account.getId()==id&&account instanceof LoanCreditAccount){
                account1=(LoanCreditAccount)account;
                account1.payLoan(money);
                return account1;
            }
            else if(account.getId()==id&&account instanceof LoanSavingAccount){
                account2=(LoanSavingAccount)account;
                account2.payLoan(money);
                return account2;
            }
        }
        return null;
    }





    /*统计所有balance */
    public double allAccounts() {
		double sum =0.0;
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            Account account=it.next();
            sum+= account.getBalance();
            }
            return sum;
    }
    /*统计所有透支额度 */
    public double getAllCeiling() {
		double sum =0.0;
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            CreditAccount account2=new CreditAccount();
            Account account=it.next();
            if(account instanceof CreditAccount)
                account2=(CreditAccount)account;
                sum+=account2.getCeiling();
            }
            return sum;
	}
    /* 统计所有贷款额 */
    public double getAllLoan() {
		double sum =0.0;
        Iterator<Account>it=set.iterator();
        while(it.hasNext()){
            LoanCreditAccount account1=new LoanCreditAccount();
            LoanSavingAccount account2=new LoanSavingAccount();
            Account account=it.next();
            if(account instanceof LoanCreditAccount){
                account1=(LoanCreditAccount)account;
                sum+=account1.getLoan();
            }
            else if(account instanceof LoanSavingAccount){
                account2=(LoanSavingAccount)account;
                sum+=account2.getLoan();
            }
            }
            return sum;
	}
    
}
