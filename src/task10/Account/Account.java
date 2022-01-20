package task10.Account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import task10.Exception.*;

public abstract class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
    private String password;
    private String name;
    private String personld;
    private String email;
    private double balance;
   /* private static long  presentid=100000;*/
    public Account(String password, String name, String personld, String email, double balance) throws IOException {
        this.IDCreate();//从文件中选取下一个可用账户
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
    //生成ID
    public void IDCreate() throws IOException{
        FileReader fileReader = new FileReader("src/task10/Account/ID.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line=bufferedReader.readLine();
        FileWriter fileWritter = new FileWriter("src/task10/Account/ID.txt",false);
        if(line==null){
            //如果空文件
            id=100001;
        }else{
            id=Long.parseLong(line);
        }
        String nextID=Long.toString(id+1);
        fileWritter.write(nextID);
        bufferedReader.close();
        fileWritter.close();
    }
}


