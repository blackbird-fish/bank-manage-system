package task9.Account;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
public class Bank {
    private List<Account> accounts=new ArrayList<Account>();
    /* 单例模式*/
    private Bank(){}
    private static Bank bank=new Bank();
    public static Bank getBank() {
		return bank;
	}
    /*功能 register*/
    public Account register(String password, String repassword,String name, String personld, String email, int accounttype)throws RegisterException, IOException{
        Account account=null;
        if(!password.equals(repassword)){ //验证密码
				throw( new RegisterException("开户异常"));
		}
        else{
            switch(accounttype){
                case 1:account=new SavingAccount(password, name, personld, email, 0.0);accounts.add(account);return account;
                case 2:account=new CreditAccount(password, name, personld, email, 0.0);accounts.add(account);return account;
                case 3:account=new LoanSavingAccount(password, name, personld, email, 0.0);accounts.add(account);return account;
                case 4:account=new LoanCreditAccount(password, name, personld, email, 0.0);accounts.add(account);return account;
             }
        }
        return null;     
    }
    /* 登录  login*/
    public Account login (long id,String password)throws LoginException{
        Account account = null ;
		for(int i=0 ; i<accounts.size() ; i++){
			account = accounts.get(i) ;
			if( id == account.getId() && password.equals(account.getPassword())){
				return account;
			}
		}
			throw(new LoginException("账号或密码错误")) ;
    }
    /* bank  deposit 存款*/
    public Account deposit(long id,double money) throws DepositException{
        Account account = null;
		for(int i=0 ; i<accounts.size() ; i++){
			account = accounts.get(i) ;
			if( id == account.getId()){
                accounts.set(i, account.deposit(money));
				return account;
			}
        }
        return null;
    }
    /*bank withdraw 取款 */
    public Account withdraw(long id,double money)throws BalanceNotEnoughException{
        Account account=null;
        for(int i=0 ; i<accounts.size() ; i++){
			account = accounts.get(i) ;
			if( id == account.getId()){
                account.withdraw(money);
                accounts.set(i, account);
				return account;
			}
		}
        return null;
    }
    /* bank 设置透支金额 setCeiling*/
    public Account setCeiling(long id,double newceiling) throws CeilingException{
       Account account=null;
       for(int i=0;i<accounts.size();i++){
           account=accounts.get(i);
           if(id==account.getId()&&account instanceof CreditAccount){
               CreditAccount ca=(CreditAccount)account;
               ca.setCeiling(newceiling);
               accounts.set(i,ca);
               return ca;
           }
       }
       return null;
    }
    /*bank  requestloan  参数 id money  返回 account*/
    public Account requestLoan(long id,double money) throws LoanException{
        Account account=null;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            if(id==account.getId()&&account instanceof LoanCreditAccount){
                LoanCreditAccount lca=(LoanCreditAccount)account;
                lca.requestLoan(money);
                accounts.set(i,lca);
                return lca;
            }
            else if(id==account.getId()&&account instanceof LoanSavingAccount){
                LoanSavingAccount lsa=(LoanSavingAccount)account;
                lsa.requestLoan(money);
                accounts.set(i,lsa);
                return lsa;
            }
        }
        return null;
    }
    /*bank  payLoan 参数 id money 返回 account*/
    public Account PayLoan(long id,double money) throws  BalanceNotEnoughException, LoanException{
        Account account=null;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            if(id==account.getId()&&account instanceof LoanCreditAccount){
                LoanCreditAccount lca=(LoanCreditAccount)account;
                lca.payLoan(money);
                accounts.set(i,lca);
                return lca;
            }
            else if(id==account.getId()&&account instanceof LoanSavingAccount){
                LoanSavingAccount lsa=(LoanSavingAccount)account;
                lsa.payLoan(money);
                accounts.set(i,lsa);
                return lsa;
            }
        }
        return null;
    }
    public Account transfer(long id1,long id2,double money)throws NotfoundAccount, BalanceNotEnoughException, DepositException{
        Account account=null;
        Account account1=null;
        Account account2=null;
        int index1=0;
        int index2=0;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            if(id1==account.getId()){
                account1=account;
                index1=i;
            }
            if(id2==account.getId()){
                account2=account;
                index2=i;
            }
        }
        if(account2!=null&&account1!=null){
            account1.withdraw(money);
            account2.deposit(money);
            accounts.set(index1, account1);
            accounts.set(index2, account2);
            return account1;
        }
        else{
            throw(new NotfoundAccount("不存在的用户"));
        }

    }





    /*统计所有balance */
    public double allAccounts() {
		double sum =0.0;
        Account account=null;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            sum+=account.getBalance();
        }
        return sum;
    }
    /*统计所有透支额度 */
    public double getAllCeiling() {
		double sum =0.0;
        Account account=null;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            if(account instanceof CreditAccount){
                CreditAccount ca=(CreditAccount)account;
                sum+=ca.getCeiling();
            }
        }
        return sum;
	}
    /* 统计所有贷款额 */
    public double getAllLoan() {
		double sum =0.0;
        Account account=null;
        for(int i=0;i<accounts.size();i++){
            account=accounts.get(i);
            if(account instanceof LoanCreditAccount){
                LoanCreditAccount lca=(LoanCreditAccount)account;
                sum+=lca.getLoan();
            }
            else if(account instanceof LoanSavingAccount){
                LoanSavingAccount lsa=(LoanSavingAccount)account;
                sum+=lsa.getLoan();
            }
            }
            return sum;
	}
    

    //保存至内存
    public void saveData(){
		FileOutputStream fos = null ;
		ObjectOutputStream oos  = null ;
		try {
			fos = new FileOutputStream("src/task9/Account/accounts.txt") ;
			oos = new ObjectOutputStream(fos) ;
			oos.writeObject(accounts);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oos != null){
					oos.close();
				}
				if(oos != null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    @SuppressWarnings("unchecked")
    public List<Account> getData(){
		FileInputStream fis = null ;
		ObjectInputStream ois = null ;
		try {
			fis = new FileInputStream("src/task9/Account/accounts.txt");
				ois = new ObjectInputStream(fis);
				try {
					accounts= (List<Account>)ois.readObject() ;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(ois != null)
						ois.close();
					if(fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return accounts;
	}

}
