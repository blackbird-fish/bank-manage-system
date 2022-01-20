package task7.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import task7.Account.*;
public class BusinessPanel extends ATMClient implements ActionListener {
    private JPanel businessPanel;
    private Bank bank=Bank.getBank();
    Account account;
    JComboBox<String>businessType;
    JTextField moneyText;
    public BusinessPanel(Account account){
        this.account=account;
        businessPanel=new JPanel();
        super.frame.setSize(400,400);
        super.frame.setLayout(null);//register 界面大小
        businessPanel=new JPanel();
        businessPanel.setLayout(null);
        //显示 id
        JLabel IDLabel = new JLabel("用户名：");
		IDLabel.setBounds(90, 60, 75, 35);
		businessPanel.add(IDLabel);
        JLabel IDValue = new JLabel(Long.toString(account.getId()));
		IDValue.setBounds(160, 60, 75, 35);
		businessPanel.add(IDValue);

        JLabel NameLabel=new JLabel("姓名：");
        NameLabel.setBounds(90,100,75,35);
        businessPanel.add(NameLabel);
        JLabel NameValue=new JLabel(account.getName());
        NameValue.setBounds(160,100,75,35);
        businessPanel.add(NameValue);

        JLabel balanceLabel=new JLabel("余额：");
        balanceLabel.setBounds(90,140,75,35);
        businessPanel.add(balanceLabel);
        JLabel balanceValue=new JLabel(String.valueOf(account.getBalance()));
        balanceValue.setBounds(160,140,75,35);
        businessPanel.add(balanceValue);

        JLabel ceilingLabel=new JLabel("信用额度：");
        ceilingLabel.setBounds(90,180,75,35);
        businessPanel.add(ceilingLabel);
        String ceiling="0.00";
        if(account instanceof CreditAccount){
            CreditAccount creditaccount=(CreditAccount)account;
            ceiling=String.valueOf(creditaccount.getCeiling());
        }
        JLabel ceilingValue=new JLabel(ceiling);
        ceilingValue.setBounds(160,180,75,35);
        businessPanel.add(ceilingValue);

        JLabel loanLabel=new JLabel("贷款额度：");
        loanLabel.setBounds(90,220,75,35);
        businessPanel.add(loanLabel);
        String loan="0.00";
        if(account instanceof LoanSavingAccount){
            LoanSavingAccount lsaccount=(LoanSavingAccount)account;
            loan=String.valueOf(lsaccount.getLoan());
        }else if(account instanceof LoanCreditAccount){
            LoanCreditAccount lcaccount=(LoanCreditAccount)account;
            loan=String.valueOf(lcaccount.getLoan());
        }
        JLabel loanValue=new JLabel(loan);
        loanValue.setBounds(160,220,75,35);
        businessPanel.add(loanValue);

        //操作
        businessType = new JComboBox<String>();
		businessType.addItem("存款");
		businessType.addItem("取款");
        businessType.addItem("转账");
        if(account instanceof CreditAccount){
            businessType.addItem("设置信用额度");
        }
        if(account instanceof Loanable){
            businessType.addItem("贷款");
            businessType.addItem("还贷");

        }
		businessType.setBounds(80, 260, 90, 30);
		businessPanel.add(businessType);
		businessType.addActionListener(this);
        //金额框
        moneyText=new JTextField();
        moneyText.setBounds(180,260,90,30);
        businessPanel.add(moneyText);
        moneyText.addActionListener(this);

        //提交与返回
		JButton submitbutton = new JButton("提交");
		submitbutton.setBounds(80, 290, 90, 30);
		businessPanel.add(submitbutton);
        submitbutton.addActionListener(this);
		
		JButton backbutton = new JButton("返回");
		backbutton.setBounds(180, 290, 90, 30);
		businessPanel.add(backbutton);
        backbutton.addActionListener(this);
        super.frame.setContentPane(businessPanel);//添加至容器
    }
   


    @Override
    public void actionPerformed(ActionEvent e) {
        bank=Bank.getBank();
		String action = e.getActionCommand() ;
		if("提交".equals(action)){
           double money=Double.valueOf(moneyText.getText());
            if(businessType.getSelectedItem().equals("存款"))
            {
                Account ac=null;
                try {
                    ac = bank.deposit(account.getId(),money);
                    JOptionPane.showMessageDialog(this.frame,"存款成功");
                    BusinessPanel businessPanel=new BusinessPanel(ac);
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                } catch (DepositException e4) {
                    JOptionPane.showMessageDialog(this.frame,"存款失败");
                    e4.printStackTrace();
                }
            }else if(businessType.getSelectedItem().equals("取款")){
                Account ac1=null;
                try {
                    ac1 = bank.withdraw(account.getId(),money);
                    JOptionPane.showMessageDialog(this.frame,"取款成功");
                    BusinessPanel businessPanel=new BusinessPanel(ac1);
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                } catch (BalanceNotEnoughException e3) {
                    JOptionPane.showMessageDialog(this.frame,"取款失败");
                    e3.printStackTrace();
                }
            }else if(businessType.getSelectedItem().equals("设置信用额度")){
                Account ac2=null;
                try {
                    ac2 = bank.setCeiling(account.getId(),money);
                    JOptionPane.showMessageDialog(this.frame,"设置信用额度成功");
                    BusinessPanel businessPanel=new BusinessPanel(ac2);
                    businessPanel.frame.setVisible(true);
                } catch (CeilingException e3) {
                    JOptionPane.showMessageDialog(this.frame,"设置信用额度失败");
                    e3.printStackTrace();
                }
            }else if(businessType.getSelectedItem().equals("贷款")){
                Account ac3=null;
                try {
                    ac3 = bank.requestLoan(account.getId(),money);
                    JOptionPane.showMessageDialog(this.frame,"贷款成功");
                    BusinessPanel businessPanel=new BusinessPanel(ac3);
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                } catch (LoanException e2) {
                    JOptionPane.showMessageDialog(this.frame,"贷款异常");
                    e2.printStackTrace();
                }
            }else if(businessType.getSelectedItem().equals("还贷")){
                Account ac4=null;
                try {
                    ac4 = bank.PayLoan(account.getId(),money);
                    BusinessPanel businessPanel=new BusinessPanel(ac4);
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                } catch (LoanException e1) {
                    JOptionPane.showMessageDialog(this.frame,"还贷异常");
                    e1.printStackTrace();
                }
            }
            else if(businessType.getSelectedItem().equals("转账")){
                TransferPanel transferPanel=new TransferPanel(this.account, money);
                transferPanel.frame.setVisible(true);
                this.frame.setVisible(false);
                //转账
            }
           }
		
		if("返回".equals(action)){
			MainPanel mainPanel=new MainPanel();
            mainPanel.frame.setVisible(true);
            this.frame.setVisible(false);
		}
	}
}
