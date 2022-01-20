package task4;
class Bank {
    Account[] accounts=new Account[100];
    private static int AccountNum=0;
    /* 单例模式*/
    private final static Bank bank=new Bank();
    private Bank(){}
    public static Bank getBank() {
		return bank;
	}
    /*功能 register*/
    public Account register(String password, String name, String personld, String email, int accounttype){
        switch(accounttype){
           case 1:
           System.out.println("SavingAccount:");
           accounts[AccountNum++]=new SavingAccount(password, name, personld, email, 0.0);break;
           case 2:
           System.out.println("CreditAccount:");
           accounts[AccountNum++]=new CreditAccount(password, name, personld, email, 0.0);break;
           case 3:
           System.out.println("LoanSavingAccount:");
           accounts[AccountNum++]=new LoanSavingAccount(password, name, personld, email, 0.0);break;
           case 4:
           System.out.println("LoanCreditAccount:");
           accounts[AccountNum++]=new LoanCreditAccount(password, name, personld, email, 0.0);break;
        }
        return accounts[AccountNum];
              
    }
    /* 登录  login*/
    public Account login(long id,String password){
        for (int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&accounts[i].getPassword().equals(password)){
                return accounts[i];
            }
        }
        return null;
    }
    /* bank  deposit 存款*/
    public Account deposit(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id){
                accounts[i].deposit(money);
                return accounts[i];
            }
        }
        return null;
    }
    /*bank withdraw 取款 */
    public Account withdraw(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id){
                accounts[i].withdraw(money);
                return accounts[i];
            }
        }
        return null;
    }
    /* bank 设置透支金额 setCeiling*/
    public Account setCeiling(long id,double newceiling){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&(accounts[i] instanceof CreditAccount)){
                ((CreditAccount)accounts[i]).setCeiling(newceiling);
                return accounts[i];
            }
        }
        return null;
    }
    /*bank  requestloan  参数 id money  返回 account*/
    public Account requestLoan(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&(accounts[i] instanceof Loanable)){
                ((Loanable)accounts[i]).requestLoan(money);;
                return accounts[i];
            }
        }
        return null;
    }
    /*bank  payLoan 参数 id money 返回 account*/
    public Account PayLoan(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&(accounts[i] instanceof Loanable)){
                ((Loanable)accounts[i]).requestLoan(money);;
                return accounts[i];
            }
        }
        return null;
    }





    /*统计所有balance */
    public double allAccounts() {
		double sum =0;
		for(int i=0;i<AccountNum;i++) {
			sum +=accounts[i].getBalance();
		}
		return sum;
	}
    /*统计所有透支额度 */
    public double getAllCeiling() {
		double sum =0.0 ;
		for (int i=0;i < AccountNum; i++) {
				if (accounts[i] instanceof CreditAccount) {
					sum +=((CreditAccount)accounts[i]).getCeiling();
				}
		}
		return sum;
	}
    /* 统计所有贷款额 */
    public double getAllLoan() {
		double sum =0.0 ;
		for (int i=0;i < AccountNum; i++) {
				if (accounts[i] instanceof Loanable) {
					sum +=((Loanable)accounts[i]).getLoan();
				}
		}
		return sum;
	}
    
}
