package task2;
class Account{
    private long id;
    private String password;
    private String name;
    private String personld;
    private String email;
    private double balance;
    public Account(long id, String password, String name, String personld, String email, double balance) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.personld = personld;
        this.email = email;
        this.balance = balance;
    }
    public Account() {
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
    public Account deposit(double balance){
        this.balance+=balance;
        return this;
    }
    public Account withdraw(double balance){
       this.balance-=balance;
        return this;
    }
}

class SavingAccount extends Account{
    public SavingAccount() {
    }
    public SavingAccount(long id, String password, String name, String personld, String email, double balance) {
        super(id, password, name, personld, email, balance);
    }
}

class CreditAccount extends Account{
    private double ceiling;
    public CreditAccount() {
    }
    public CreditAccount(long id, String password, String name, String personld, String email, double balance) {
        super(id, password, name, personld, email, balance);
    }
    public double getCeiling() {
        return ceiling;
    }
    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }
    public CreditAccount withdraw(double balance){
        super.withdraw(balance);
        return this;
     }
}