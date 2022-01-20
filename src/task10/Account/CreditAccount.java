package task10.Account;

import java.io.IOException;
import task10.Exception.*;


public class CreditAccount extends Account {
	private static final long serialVersionUID = 1L;
	private double ceiling=10000.0;
    public CreditAccount() {
    }
    public CreditAccount(String password, String name, String personld, String email, double balance) throws IOException {
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
            throw(new CeilingException("�������ö���쳣"));
        }
    }
    /* abstract ʵ��   �����˻�����͸֧����͸֧���*/
    public CreditAccount withdraw(double money) throws BalanceNotEnoughException{
        if(money<0){
            throw (new BalanceNotEnoughException("�����쳣"));
        }else if(money>0&&super.getBalance()-money+this.getCeiling()<0){
            throw (new BalanceNotEnoughException("�����쳣"));
        }else{
            super.setBalance(super.getBalance()-money);
        }
        return this;
     }
   /* @Override
    public String toString() {
        String id = Long.toString(super.getId());
        return "CreditAccoount  "+"id:"+id+"name"+super.getName();
    }*/
    @Override
    /*euqals    �˻�������id��ͬ����Ϊ��ͬ����*/
    public boolean equals(Object obj) {
        if(obj instanceof CreditAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
    }
