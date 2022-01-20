package task10.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import task10.Account.*;
import task10.CS.*;
import task10.LanguageSource.Language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class TransferPanel extends ATMClient implements ActionListener{
    private JPanel transferPanel;
    private JTextField transferID;
    double money;
    Account account;
    String confirm;
    String back;
    private UserClient user;
    private Language language;
    public TransferPanel(Account account,double money,Language l){
        this.language=l;
        //创建 client 对象
        try {
            user=new UserClient();
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        transferPanel=new JPanel();
        super.frame.setSize(400,300);
        super.frame.setLayout(null);//register 界面大小
        this.account=account;
        this.money=money;
        transferPanel.setLayout(null);//调整至正中间
        
        JLabel balanceLabel=new JLabel(language.getString("currentbalance"));
        balanceLabel.setBounds(70,50,100,35);
        transferPanel.add(balanceLabel);
        JLabel balanceValue=new JLabel(String.valueOf(account.getBalance()));
        balanceValue.setBounds(230,50,100,35);
        transferPanel.add(balanceValue);
        JLabel transferLabel=new JLabel(language.getString("transferid"));
        transferLabel.setBounds(155,90,100,35);
        transferPanel.add(transferLabel);
        transferID=new JTextField();
        transferID.setBounds(100,125,170,25 );
        transferID.addActionListener(this);
        transferPanel.add(transferID);


        confirm=language.getString("confirm");
        back=language.getString("back");
        JButton confirmButton=new JButton(confirm);
        confirmButton.addActionListener(this);
        confirmButton.setBounds(70, 170, 80, 30);
        transferPanel.add(confirmButton);
        JButton backButton=new JButton(back);
        backButton.addActionListener(this);
        backButton.setBounds(220, 170, 80, 30);
        transferPanel.add(backButton);
        this.frame.setContentPane(transferPanel);
        this.frame.setLocationRelativeTo(null);
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
                    JOptionPane.showMessageDialog(this.frame,language.getString("transfersucceed"));
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
                }
          
		}
		if(action.equals(back)){
            To exit = new To("exit" , null ) ;
			try {
				user.SendMessage(exit) ;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
			user.close();
		    }
            BusinessPanel businessPanel=new BusinessPanel(this.account,this.language);
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
				JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
				e1.printStackTrace();
			}
			return to1;
	}


}
