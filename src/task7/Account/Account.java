package task7.Account;
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
    public final Account deposit(double balance)throws DepositException{
        if(balance>0)
        this.balance+=balance;
        else{
            throw(new DepositException("存款异常"));
        }
        return this;
    }
    public abstract Account withdraw(double balance) throws BalanceNotEnoughException;
}


