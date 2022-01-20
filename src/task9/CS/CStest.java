package task9.CS;

import java.io.IOException;
import java.net.UnknownHostException;
import task9.Account.*;

public class CStest {
    public static void main(String[] args){
        UserClient user=null;
        try {
            user=new UserClient();
        } catch (UnknownHostException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
        LoanCreditAccount lca=null;
        try {
            lca=new LoanCreditAccount("w123", "neo", "123", "23", 50.0);
            System.out.println(lca.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        To to=new To("register", lca);
        to.setRepassword("w123");to.setType(4);
        to.setMoney(10);
        try {
            user.SendMessage(to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        To to1=null;
        try {
            to1=user.ReceiveMessage();
            Account a=to1.getToAccount();
            if(a==null){
                System.out.println("无返回");
            }else{
                System.out.println(to1.getToAccount().getId());
                System.out.println(to1.getToAccount().getBalance());
            }   
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        To loginto=new To("login",null);
        loginto.setId(to1.getToAccount().getId());
        loginto.setPassword(to1.getToAccount().getPassword());
        try {
            user.SendMessage(loginto);
            try {
                To loginto1=user.ReceiveMessage();
                System.out.println(loginto1.getToAccount().getId()+loginto1.getToAccount().getName());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    
}
