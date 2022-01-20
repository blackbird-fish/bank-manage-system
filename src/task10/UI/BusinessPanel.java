package task10.UI;
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
import task10.CS.*;
import task10.LanguageSource.Language;
import task10.Account.*;
public class BusinessPanel extends ATMClient implements ActionListener {
    private JPanel businessPanel;
    Account account;
    JComboBox<String>businessType;
    private UserClient user;
    JTextField moneyText;
    String deposit;
    String withdraw;
    String transfer;
    String requestloan;
    String payloan;
    String setceiling;
    String submit;
    String back;
    private Language language;
    
    public BusinessPanel(Account account,Language l){
        this.language=l;
        try {
            user=new UserClient();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this.frame,language.getString("unkownhost"));
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
            e.printStackTrace();
        }
        this.account=account;
        businessPanel=new JPanel();
        super.frame.setSize(400,350);
        super.frame.setLayout(null);//register 界面大小
        businessPanel=new JPanel();
        businessPanel.setLayout(null);
        //显示 id
        JLabel NameLabel=new JLabel(language.getString("name"));
        NameLabel.setBounds(90,20,90,35);
        businessPanel.add(NameLabel);
        JLabel NameValue=new JLabel(account.getName());
        NameValue.setBounds(210,20,90,35);
        businessPanel.add(NameValue);

        JLabel balanceLabel=new JLabel(language.getString("balance"));
        balanceLabel.setBounds(90,60,90,35);
        businessPanel.add(balanceLabel);
        JLabel balanceValue=new JLabel(String.valueOf(account.getBalance()));
        balanceValue.setBounds(210,60,90,35);
        businessPanel.add(balanceValue);

        JLabel ceilingLabel=new JLabel(language.getString("ceiling"));
        ceilingLabel.setBounds(90,100,90,35);
        businessPanel.add(ceilingLabel);
        String ceiling="0.00";
        if(account instanceof CreditAccount){
            CreditAccount creditaccount=(CreditAccount)account;
            ceiling=String.valueOf(creditaccount.getCeiling());
        }
        JLabel ceilingValue=new JLabel(ceiling);
        ceilingValue.setBounds(210,100,90,35);
        businessPanel.add(ceilingValue);

        JLabel loanLabel=new JLabel(language.getString("loanavailabe"));
        loanLabel.setBounds(90,140,90,35);
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
        loanValue.setBounds(210,140,90,35);
        businessPanel.add(loanValue);

        deposit=language.getString("deposit");
        withdraw=language.getString("withdrawl");
        transfer=language.getString("transfer");
        setceiling=language.getString("setceiling");
        requestloan=language.getString("requestloan");
        payloan=language.getString("payloan");

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
		businessType.setBounds(80, 180, 90, 30);
		businessPanel.add(businessType);
		businessType.addActionListener(this);
        //金额框
        moneyText=new JTextField();
        moneyText.setBounds(190,180,90,30);
        businessPanel.add(moneyText);
        moneyText.addActionListener(this);

        submit=language.getString("submit");
        back=language.getString("back");

        //提交与返回
		JButton submitbutton = new JButton(submit);
		submitbutton.setBounds(80, 210, 90, 30);
		businessPanel.add(submitbutton);
        submitbutton.addActionListener(this);
		
		JButton backbutton = new JButton(back);
		backbutton.setBounds(190, 210, 90, 30);
		businessPanel.add(backbutton);
        backbutton.addActionListener(this);
        this.frame.setContentPane(businessPanel);//添加至容器    
        this.frame.setLocationRelativeTo(null);//将界面调整至中心处
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
                    JOptionPane.showMessageDialog(this.frame,language.getString("depositsucceed"));
			     	BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
			     	businessPanel.frame.setVisible(true);
						this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
						}

            }else if(businessType.getSelectedItem().equals(withdraw)){
                to=new To("withdraw",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,language.getString("withdrawlsucceed"));
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
                }
            }else if(businessType.getSelectedItem().equals(setceiling)){
                to=new To("setceiling",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,language.getString("setceilingsucceed"));
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
                }
            }else if(businessType.getSelectedItem().equals(requestloan)){
                to=new To("requestloan",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,language.getString("loansucceed"));
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
                }
            }else if(businessType.getSelectedItem().equals(payloan)){
                to=new To("payloan",account);
                to.setMoney(money);
                rto=this.CSAction(to);
                if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,language.getString("payloansucceed"));
                    BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
                    businessPanel.frame.setVisible(true);
                    this.frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
                }
            }
            else if(businessType.getSelectedItem().equals(transfer)){
                TransferPanel transferPanel=new TransferPanel(this.account, money,this.language);
                transferPanel.frame.setVisible(true);
                this.frame.setVisible(false);
                //转账
            }
        }
		if(action.equals(back)){
			MainPanel mainPanel=new MainPanel(this.language);
            To exit = new To("exit" , null ) ;
			try {
				user.SendMessage(exit) ;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
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
                JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
                e1.printStackTrace();
            }
            return to1;
    }
    

}




 
