package task10.CS;
import java.io.Serializable;
import task10.Account.*;
public class To  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String BusinessAction;//ҵ�����  
    private Account ToAccount;//�����˻�����Account ��Ϣ
    private double money;//���׽��
    private long transferID; //ת��ID
    private String repassword;//ע��ʱ������ȷ��
    private String exception;//�����쳣��Ϣ
    private int type;
    private long id;//��¼ʱ��id ����
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
