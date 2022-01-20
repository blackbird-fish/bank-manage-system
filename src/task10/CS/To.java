package task10.CS;
import java.io.Serializable;
import task10.Account.*;
public class To  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String BusinessAction;//业务操作  
    private Account ToAccount;//交互账户传递Account 信息
    private double money;//交易金额
    private long transferID; //转账ID
    private String repassword;//注册时的密码确认
    private String exception;//传递异常信息
    private int type;
    private long id;//登录时的id 密码
    private String password;
    
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
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public To(String BusinessAction,Account account){
        this.BusinessAction=BusinessAction;
        this.ToAccount=account;
    }
    public String getBusinessAction() {
        return BusinessAction;
    }
    public void setBusinessAction(String businessAction) {
        BusinessAction = businessAction;
    }
    public Account getToAccount() {
        return ToAccount;
    }
    public void setToAccount(Account toAccount) {
        ToAccount = toAccount;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public long getTransferID() {
        return transferID;
    }
    public void setTransferID(long trandferID) {
        this.transferID = trandferID;
    }
    public String getRepassword() {
        return repassword;
    }
    
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
  
    public String getException() {
        return exception;
    }
    public void setException(String exception) {
        this.exception = exception;
    }
    
}
