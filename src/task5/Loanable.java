package task5;
public interface Loanable{
    void requestLoan(double money);
    void payLoan(double money);
    double getLoan();
}
class LoanSavingAccount extends SavingAccount implements Loanable{
    private double loan=0.0;
    public LoanSavingAccount(){
    }
    public LoanSavingAccount(String password, String name, String personld, String email, double balance) {
        super (password, name, personld, email, balance);
    }
    public void setloan(double loan){
        this.loan=loan;
    }
    public void requestLoan(double money){
        setloan(this.loan+money);
    }
    public void payLoan(double money){
        if (money<=super.getBalance()){
            super.setBalance(super.getBalance()-money);
            this.loan+=money;
            }
    }
    public double getLoan(){
        return this.loan;
    }
    public String toString() {
        String id = Long.toString(super.getId());
        return "LoanSavingAccount  "+"id:"+id+"name"+super.getName();
    }
     /*euqals    账户类型且id相同的视为相同对象*/
     public boolean equals(Object obj) {
        if(obj instanceof LoanSavingAccount){
            Account a=(Account)obj;
            return this.getId()==a.getId();
        }
        return false;
     }
}
class LoanCreditAccount extends CreditAccount implements Loanable{
    private double loan=0.0;
    public LoanCreditAccount(){
    }
    public LoanCreditAccount(String password, String name, String personld, String email, double balance) {
        super (password, name, personld, email, balance);
    }
    public void setloan(double loan) {
        this.loan = loan;
    }
    public void requestLoan(double money){
       setloan(this.loan+money);
    }
    public void payLoan(double money){
        if (money<=super.getBalance()){
            super.setBalance(super.getBalance()-money);
            this.loan+=money;
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