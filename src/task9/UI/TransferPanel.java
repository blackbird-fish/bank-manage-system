package task9.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import task9.Account.*;
import task9.CS.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class TransferPanel extends ATMClient implements ActionListener{
    private JPanel transferPanel;
    private JTextField transferID;
    double money;
    Account account;
    String confirm="确认";
    String back="返回";
    private UserClient user;
    public TransferPanel(Account account,double money){
        //创建 client 对象
        try {
            user=new UserClient();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        transferPanel=new JPanel();
        super.frame.setSize(400,300);
        super.frame.setLayout(null);//register 界面大小
        this.account=account;
        this.money=money;
        transferPanel.setLayout(null);//调整至正中间
        
        JLabel balanceLabel=new JLabel("当前余额：");
        balanceLabel.setBounds(100,50,70,35);
        transferPanel.add(balanceLabel);
        JLabel balanceValue=new JLabel(String.valueOf(account.getBalance()));
        balanceValue.setBounds(200,50,70,35);
        transferPanel.add(balanceValue);
        JLabel transferLabel=new JLabel("转入账户ID");
        transferLabel.setBounds(155,90,60,35);
        transferPanel.add(transferLabel);
        transferID=new JTextField();
        transferID.setBounds(100,125,170,25 );
        transferID.addActionListener(this);
        transferPanel.add(transferID);
        
        JButton confirmButton=new JButton(confirm);
        confirmButton.addActionListener(this);
        confirmButton.setBounds(70, 170, 80, 30);
        transferPanel.add(confirmButton);
        JButton backButton=new JButton(back);
        backButton.addActionListener(this);
        backButton.setBounds(220, 170, 80, 30);
        transferPanel.add(backButton);
        super.frame.setContentPane(transferPanel);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if(action.equals(confirm)){
			long ID=Long.parseLong(transferID.getText());
            To to=null;
            To rto=null;
            to=new To("transfer",account);
                to.setTransferID(ID);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"转账成功");
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,rto.getException());
                }
          
		}
		if(action.equals(back)){
            To exit = new To("exit" , null ) ;
			try {
				user.SendMessage(exit) ;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this.frame,"IO异常");
			user.close();
		    }
            BusinessPanel businessPanel=new BusinessPanel(this.account);
            this.frame.setVisible(false);
            businessPanel.frame.setVisible(true);
		}
	}
    public To CSAction(To to){
		try {
			user.SendMessage(to);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			To to1=null;
			try {
				to1 = user.ReceiveMessage();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this.frame,"IO异常");
				e1.printStackTrace();
			}
			return to1;
	}


}
