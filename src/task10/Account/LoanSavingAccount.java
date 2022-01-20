package task10.Account;

import java.io.IOException;
import task10.Exception.*;
public class LoanSavingAccount extends SavingAccount implements Loanable{
	private static final long serialVersionUID = 1L;
	private double loan=10000.00;//��ǰ���
    public LoanSavingAccount(){
    }
    public LoanSavingAccount(String password, String name, String personld, String email, double balance) throws IOException {
        super (password, name, personld, email, balance);
    }
    public void setloan(double loan){
        if(loan>0){
            this.loan=loan;
        }else{
            try{
                throw(new LoanException("�����쳣"));
            }catch(LoanException e){
                e.printStackTrace();
            }
        }
    }
    public void requestLoan(double money) throws LoanException{
        if(money>0&&money<=loan)
            setloan(this.loan-money);
        else{
                throw(new LoanException("�����쳣"));
        } 
    }
    public void payLoan(double money) throws BalanceNotEnoughException,LoanException{
        if(money<0){
            throw(new LoanException("��������Ϊ����"));
        }else if (money<=super.getBalance()){
            super.setBalance(super.getBalance()-money);
            setloan(loan+money);
            }
        else{
                throw(new BalanceNotEnoughException("�����쳣"));
            }    
    }
    public double getLoan(){
        return this.loan;
    }
  /*  public String toString() {
        String id = Long.toString(super.getId());
        return "LoanSavingAccount  "+"id:"+id+"name"+super.getName();
    }*/
     /*euqals    �˻�������id��ͬ����Ϊ��ͬ����*/
     public boolean equals(Object obj) {
        if(obj instanceof LoanSavingAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
}