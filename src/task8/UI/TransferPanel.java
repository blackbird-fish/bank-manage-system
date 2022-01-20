package task8.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import task8.Account.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferPanel extends ATMClient implements ActionListener{
    private JPanel transferPanel;
    private JTextField transferID;
    double money;
    Account account;
    private Bank bank=Bank.getBank();
    public TransferPanel(Account account,double money){
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
        
        JButton confirmButton=new JButton("确认");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(70, 170, 80, 30);
        transferPanel.add(confirmButton);
        JButton backButton=new JButton("返回");
        backButton.addActionListener(this);
        backButton.setBounds(220, 170, 80, 30);
        transferPanel.add(backButton);
        super.frame.setContentPane(transferPanel);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if("确认".equals(action)){
			long ID=Long.parseLong(transferID.getText());
            Account account1=null;
            try {
                account1 = bank.transfer(account.getId(),ID,money);
                bank.saveData();
                BusinessPanel businessPanel=new BusinessPanel(account1);
                JOptionPane.showMessageDialog(this.frame,"转账成功");
                this.frame.setVisible(false);
                businessPanel.frame.setVisible(true);
            } catch (NotfoundAccount e1) {
                JOptionPane.showMessageDialog(this.frame,"无此用户！");
                e1.printStackTrace();
            }catch ( BalanceNotEnoughException e2){
                JOptionPane.showMessageDialog(this.frame,"余额不足！");
            }catch(DepositException e3){
                JOptionPane.showMessageDialog(this.frame,"无效的转账金额！");
            }
          
		}
		
		if("返回".equals(action)){
            BusinessPanel businessPanel=new BusinessPanel(this.account);
            this.frame.setVisible(false);
            businessPanel.frame.setVisible(true);
		}
	}

}
