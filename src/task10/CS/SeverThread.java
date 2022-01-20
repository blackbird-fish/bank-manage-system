package task10.CS;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import task10.Exception.*;

import task10.Account.*;
public class SeverThread extends Thread {
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    ObjectOutputStream objectout;
    ObjectInputStream  objectin;
    Bank bank;
    public SeverThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        bank=Bank.getBank();
            try {
            out=socket.getOutputStream();
            in=socket.getInputStream();
            objectout=new ObjectOutputStream(out);
            objectin=new ObjectInputStream(in);
            To sendTo=null;
            To receiveTo=null;
            String businessAction=null;
            while(!Thread.interrupted()){
                bank.getData();
                    try {
                        receiveTo=(To)objectin.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }//读入to 内容
                businessAction=receiveTo.getBusinessAction();//接受来自客户端的请求
                if("exit".equals(businessAction)){
                    break;
                }
                sendTo=this.Business(receiveTo,businessAction,bank);
                    objectout.writeObject(sendTo);
                    objectout.reset();
                    objectout.flush();
                bank.saveData();
            }
                }catch(SocketException e){
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(objectout!=null)
                        try {
                            objectout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(objectin!=null);
                    try {
                        objectin.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(out!=null)
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(in!=null)
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(socket!=null)
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
    public To Business(To receivedTo,String business,Bank bank){
        //需要获取 receivedTo中的account 包含所有新建用户的信息
        Account receivedAccount=receivedTo.getToAccount();
        //注册
        if("register".equals(business)){
            Account account=null;
            String password=receivedAccount.getPassword();
            String name=receivedAccount.getName();
            String repassword=receivedTo.getRepassword();
            String personId=receivedAccount.getPersonld();
            String email=receivedAccount.getEmail();
            int accounttype=receivedTo.getType();
			synchronized(bank){
                try {
                    account=bank.register(password, repassword, name, personId, email, accounttype);
                } catch (RegisterException e) {
                    account=null;
                    receivedTo.setException("inconsistentpasswords");
                    e.printStackTrace();
                } catch (IOException e) {
                    receivedTo.setException("ioexception");
                    e.printStackTrace();
                }
                finally{
                    receivedTo.setToAccount(account);
			    }

            } 
		}
        //登录
        if("login".equals(business)){
			Account account=null ;
			synchronized(bank){
				try {
                    account=bank.login(receivedTo.getId(), receivedTo.getPassword()) ;
                } catch (LoginException e) {
                    account=null;
                    receivedTo.setException("loginerror");
                    e.printStackTrace();
                }
			}
            receivedTo.setToAccount(account);	
		}
		
		//存款
		if("deposit".equals(business)){
			Account account = null ;
			synchronized(bank){
				try {
                    account = bank.deposit(receivedAccount.getId(), receivedTo.getMoney()) ;
                } catch (DepositException e) {
                    account=null;
                    receivedTo.setException("depositerror");
                    e.printStackTrace();
                }
			}
            receivedTo.setToAccount(account);
		}
		
		//取款
		if("withdraw".equals(business)){
			Account account = null ;
			synchronized(bank){
				try {
                    account = bank.withdraw(receivedAccount.getId(), receivedTo.getMoney()) ;
                } catch (BalanceNotEnoughException e) {
                    account=null;
                    receivedTo.setException("balancenotenough");
                    e.printStackTrace();
                }
			}
            receivedTo.setToAccount(account);
		}
		
		//透支
		if("setceiling".equals(business)){
			Account account= null ;
			synchronized(bank){
				try {
                    account=bank.setCeiling(receivedAccount.getId(), receivedTo.getMoney()) ;
                } catch (CeilingException e) {
                    account=null;
                    receivedTo.setException("ceilingexception");
                    e.printStackTrace();
                }
			}	
            receivedTo.setToAccount(account);
		}
		
		//贷款
		if("requestloan".equals(business)){
			Account account = null ;
			synchronized(bank){
				try {
                    account=bank.requestLoan(receivedAccount.getId(), receivedTo.getMoney()) ;
                } catch (LoanException e) {
                    account=null;
                    receivedTo.setException("loanexception");
                    e.printStackTrace();
                }
			}    
            receivedTo.setToAccount(account);  
		}
		
		//还贷
		if("payloan".equals(business)){
			Account account=null ;
			synchronized(bank){
                            try {
                                account=bank.PayLoan(receivedAccount.getId(), receivedTo.getMoney());
                            } catch (BalanceNotEnoughException e) {
                                account=null;
                                receivedTo.setException("balancenotenough");
                                e.printStackTrace();
                            } catch (LoanException e) {
                                account=null;
                                receivedTo.setException("loanexception");
                                e.printStackTrace();
                            }
			}
            receivedTo.setToAccount(account);
		}
		
		//转账
		if("transfer".equals(business)){
            Account account=null;
			synchronized(bank){
                try {
                    account=bank.transfer(receivedAccount.getId(), receivedTo.getTransferID(), receivedTo.getMoney());
                } catch (NotfoundAccount e) {
                    account=null;
                    receivedTo.setException("notfoundaccount");
                    e.printStackTrace();
                } catch (BalanceNotEnoughException e) {
                    account=null;
                    receivedTo.setException("balancenotenough");
                    e.printStackTrace();
                } catch (DepositException e) {
                    account=null;
                    receivedTo.setException("transferexception");
                    e.printStackTrace();
                }
			}	
            receivedTo.setToAccount(account);
		}
        return receivedTo;
    }

}
