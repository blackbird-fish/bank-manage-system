package task9.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import task9.CS.*;
import task9.Account.*;
public class BusinessPanel extends ATMClient implements ActionListener {
    private JPanel businessPanel;
    Account account;
    JComboBox<String>businessType;
    private UserClient user;
    JTextField moneyText;
    String deposit="存款";
    String withdraw="取款";
    String transfer="转账";
    String requestloan="贷款";
    String payloan="还款";
    String setceiling="设置额度";
    String submit="提交";
    String back="返回";
    
    public BusinessPanel(Account account){
        try {
            user=new UserClient();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this.frame,"连接失败");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this.frame,"IO异常");
            e.printStackTrace();
        }
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
		businessType.addItem(deposit);
		businessType.addItem(withdraw);
        businessType.addItem(transfer);
        if(account instanceof CreditAccount){
            businessType.addItem(setceiling);
        }
        if(account instanceof Loanable){
            businessType.addItem(requestloan);
            businessType.addItem(payloan);
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
		String action = e.getActionCommand() ;
        To to=null;
        To rto=null;
		if(action.equals(submit)){
            double money=Double.valueOf(moneyText.getText());
            if(businessType.getSelectedItem().equals(deposit))
            {
                to=new To("deposit", account);
                to.setMoney(money);
                rto=this.CSAction(to);
			    if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"存款成功");
			     	BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
			     	businessPanel.frame.setVisible(true);
						this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(this.frame,rto.getException());
						}

            }else if(businessType.getSelectedItem().equals(withdraw)){
                to=new To("withdraw",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"取款成功");
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,rto.getException());
                }
            }else if(businessType.getSelectedItem().equals(setceiling)){
                to=new To("setceiling",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"透支成功");
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,rto.getException());
                }
            }else if(businessType.getSelectedItem().equals(requestloan)){
                to=new To("requestloan",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"贷款成功");
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,rto.getException());
                }
            }else if(businessType.getSelectedItem().equals(payloan)){
                to=new To("payloan",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,"还贷成功");
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount());//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,rto.getException());
                }
            }
            else if(businessType.getSelectedItem().equals(transfer)){
                TransferPanel transferPanel=new TransferPanel(this.account, money);
                transferPanel.frame.setVisible(true);
                this.frame.setVisible(false);
                //转账
            }
        }
		if(action.equals(back)){
			MainPanel mainPanel=new MainPanel();
            To exit = new To("exit" , null ) ;
			try {
				user.SendMessage(exit) ;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this.frame,"IO异常");
		    }finally{
                user.close();
            }
            mainPanel.frame.setVisible(true);
            this.frame.setVisible(false);
            
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




 
