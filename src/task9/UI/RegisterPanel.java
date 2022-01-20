package task9.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class RegisterPanel extends ATMClient implements ActionListener {
    private JPanel registerPanel;
	private UserClient user;
	JComboBox <String>TypeBox;//账户类型
	JTextField nameText;//用户名
	JTextField passwordText;//密码
	JTextField repasswordText;//确认密码
	JTextField personIdText;//身份证
	JTextField emailText;//电子邮箱
	//function BUtton
	String submit="提交";
	String back="返回";
	public RegisterPanel(){
        super.frame.setSize(400,400);
        super.frame.setLayout(null);//register 界面大小
        registerPanel=new JPanel();
        registerPanel.setLayout(null);
        //账户类型
        JLabel typeLabel = new JLabel("账户类型：");
		typeLabel.setBounds(90, 12, 75, 35);
		registerPanel.add(typeLabel);
        TypeBox = new JComboBox<String>();
		TypeBox.addItem("储蓄账户");
		TypeBox.addItem("信用账户");
		TypeBox.addItem("可贷款储蓄账户");
		TypeBox.addItem("可贷款信用账户");
		TypeBox.setBounds(160, 20, 135, 20);
		registerPanel.add(TypeBox);
		TypeBox.addActionListener(this);

		//用户名
		JLabel nameLabel = new JLabel("用户名：");
		nameLabel.setBounds(90, 52, 75, 35);
		registerPanel.add(nameLabel);
        nameText = new JTextField();
		nameText.setBounds(160, 60, 135, 20);
		registerPanel.add(nameText);
        nameText.addActionListener(this);

		//密码
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setBounds(90, 92, 75, 35);
		registerPanel.add(passwordLabel);
        passwordText = new JTextField();
		passwordText.setBounds(160, 100, 135, 20);
		registerPanel.add(passwordText);
        passwordText.addActionListener(this);

		//确认密码
		JLabel repasswordLabel = new JLabel("确认密码：");
		repasswordLabel.setBounds(90, 132, 75, 35);
		registerPanel.add(repasswordLabel);
		repasswordText=new JTextField();
		repasswordText.setBounds(160, 140, 135, 20);
		registerPanel.add(repasswordText);
        repasswordText.addActionListener(this);

        //身份证号
		JLabel personIdLabel = new JLabel("身份证号：");
		personIdLabel.setBounds(90, 172, 75, 35);
		registerPanel.add(personIdLabel);
        personIdText = new JTextField();
		personIdText.setBounds(160, 180, 135, 20);
		registerPanel.add(personIdText);
        personIdText.addActionListener(this);
    
		//E-Mail
		JLabel emailLabel = new JLabel("电子邮件：");
		emailLabel.setBounds(90, 212, 75, 35);
		registerPanel.add(emailLabel);
		emailText = new JTextField();
		emailText.setBounds(160, 220, 135, 20);
		registerPanel.add(emailText);
        emailText.addActionListener(this);
	
        //提交与返回
		JButton submitbutton = new JButton(submit);
		submitbutton.setBounds(80, 290, 90, 30);
		registerPanel.add(submitbutton);
        submitbutton.addActionListener(this);
		
		JButton backbutton = new JButton(back);
		backbutton.setBounds(180, 290, 90, 30);
		registerPanel.add(backbutton);
        backbutton.addActionListener(this);
        super.frame.setContentPane(registerPanel);//添加至容器
		try {
			user=new UserClient();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(this.frame,"连接失败");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.frame,"IO异常");
			e.printStackTrace();
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



    @Override
	
	public void actionPerformed(ActionEvent e){
		
		String action = e.getActionCommand() ;
		
		if(action.equals(submit)){
				//获取注册信息
				int type = TypeBox.getSelectedIndex()+1 ;
			    String name = nameText.getText() ;
				String password=passwordText.getText();
				String repassword=repasswordText.getText();
				String personId = personIdText.getText() ;
				String email = emailText.getText();
				Account account=null;
				To to=new To("", account);//生成to对象
				switch(type){
					case 1:account=new SavingAccount();
					       account.setName(name);
						   account.setPassword(password);
						   account.setPersonld(personId);
						   account.setEmail(email);
						   account.setBalance(0.0);
						   to.setType(1);
						break;
					case 2:account=new SavingAccount();
					       account.setName(name);
					       account.setPassword(password);
					       account.setPersonld(personId);
					       account.setEmail(email);
					       account.setBalance(0.0);
						   to.setType(2);
						break;
					case 3:account=new SavingAccount();
					       account.setName(name);
					       account.setPassword(password);
					       account.setPersonld(personId);
					       account.setEmail(email);
					       account.setBalance(0.0);
						   to.setType(3);
						break;
					case 4:account=new SavingAccount();
					       account.setName(name);
					       account.setPassword(password);
					       account.setPersonld(personId);
					       account.setEmail(email);
					       account.setBalance(0.0);
						   to.setType(4);
						   break;
				 }
					    to.setBusinessAction("register");
						to.setToAccount(account);
						to.setRepassword(repassword);
						To rTo=this.CSAction(to);
						if(rTo.getToAccount()!=null){
						JOptionPane.showMessageDialog(this.frame,"开户成功，用户ID为："+rTo.getToAccount().getId());
						BusinessPanel businessPanel=new BusinessPanel(rTo.getToAccount());//转到busine界面
						businessPanel.frame.setVisible(true);
						this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(this.frame,rTo.getException());
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
			MainPanel mainpanel=new MainPanel();
            this.frame.setVisible(false);
            mainpanel.frame.setVisible(true);
		}
    }
	
}
