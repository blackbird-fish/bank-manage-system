package task8.Account;

import java.io.IOException;

public class LoanCreditAccount extends CreditAccount implements Loanable{
    private double loan=10000.00;
    public LoanCreditAccount(){
    }
    public LoanCreditAccount(String password, String name, String personld, String email, double balance) throws IOException {
        super (password, name, personld, email, balance);
    }
    public void setloan(double loan) {
        if(loan>0){
            this.loan = loan;
        }else{
            try{
                throw(new LoanException("贷款异常"));
            }catch(LoanException e){
                e.printStackTrace();
            }
        }
        
    }
    public void requestLoan(double money) throws LoanException{
        if(money<loan){
            setloan(this.loan-money);
        }else{
            throw (new LoanException("贷款异常"));
        }
    }
    public void payLoan(double money) throws LoanException{
        if (money<=super.getBalance()){
            super.setBalance(super.getBalance()-money);
            setloan(loan+money);
        }else{
                throw(new LoanException("还贷异常"));
        }
    }
    public double getLoan(){
        return this.loan;
    }
    @Override
    /*toString 方法 */
    public String toString() {
        String id = Long.toString(super.getId());
        return "LoanCreditAccoount  "+"id:"+id+"name"+super.getName();
    }
    @Override
    /*euqals    账户类型且id相同的视为相同对象*/
    public boolean equals(Object obj) {
       if(obj instanceof LoanCreditAccount){
           Account a=(Account)obj;
           return this.getId()==a.getId();
       }
       return false;
    }
}
