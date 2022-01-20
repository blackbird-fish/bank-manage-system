package task2;
class Bank {
    Account[] accounts=new Account[100];
    private static int AccountNum=0;
    public Account register(long id, String password, String name, String personld, String email, int accounttype){
            if(accounttype==1){
                accounts[AccountNum++]=new SavingAccount(id, password, name, personld, email, 0.0);
            }else{
                accounts[AccountNum++]=new CreditAccount(id,password,name,personld,email,0.0);
            }
            return accounts[AccountNum];
    }
    public Account login(long id,String password){
        for (int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&accounts[i].getPassword().equals(password)){
                return accounts[i];
            }
        }
        return null;
    }
    public Account deposit(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id){
                accounts[i].deposit(money);
                return accounts[i];
            }
        }
        return null;
    }
    public Account withdraw(long id,double money){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id){
                accounts[i].withdraw(money);
                return accounts[i];
            }
        }
        return null;
    }
    public Account setCeiling(long id,double newceiling){
        for(int i=0;i<AccountNum;i++){
            if(accounts[i].getId()==id&&(accounts[i] instanceof CreditAccount)){
                ((CreditAccount)accounts[i]).setCeiling(newceiling);
                return accounts[i];
            }
        }
        return null;
    }
    public double allAccounts() {
		double sum =0;
		for(int i=0;i<AccountNum;i++) {
			sum +=accounts[i].getBalance();
		}
		return sum;
	}
    public double getAllCeiling(long id) {
		double sum =0.0 ;
		for (int i=0;i < AccountNum; i++) {
			if (accounts[i].getId()==id) {
				if (accounts[i] instanceof CreditAccount) {
					sum +=((CreditAccount)accounts[i]).getCeiling();
				}
			}
		}
		return sum;
	}
}
