package task3;
public abstract class Account{
    private long id;
    private String password;
    private String name;
    private String personld;
    private String email;
    private double balance;
    private static long  presentid=100000;
    public Account(String password, String name, String personld, String email, double balance) {
        this.id = ++presentid;
        this.password = password;
        this.name = name;
        this.personld = personld;
        this.email = email;
        this.balance = balance;
    }
    public Account() {
        this.id=++presentid;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPersonld() {
        return personld;
    }
    public void setPersonld(String personld) {
        this.personld = personld;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public final Account deposit(double balance){
        this.balance+=balance;
        return this;
    }
    public abstract Account withdraw(double balance);
}

class SavingAccount extends Account{
    public SavingAccount() {
    }
    public SavingAccount(String password, String name, String personld, String email, double balance) {
        super(password, name, personld, email, balance);
    }
    /* abstract 实现    如果取款金额大于余额返回null*/
    public Account withdraw(double balance){
        if(super.getBalance()>=balance){
            super.setBalance(super.getBalance()-balance);
            return this;
        }
        return null;
        
    }
}

class CreditAccount extends Account{
    private double ceiling;
    public CreditAccount() {
    }
    public CreditAccount(String password, String name, String personld, String email, double balance) {
        super (password, name, personld, email, balance);
    }
    public double getCeiling() {
        return ceiling;
    }
    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }
    /* abstract 实现 */
    public CreditAccount withdraw(double balance){
        if(super.getBalance()>=balance){
            super.setBalance(super.getBalance()-balance);
            return this;
        }
        return null;
     }
}