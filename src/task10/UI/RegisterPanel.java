package task10.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	String submit;
	String back;
	private Language language;
	public RegisterPanel(Language l){
		try {
			user=new UserClient();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(this.frame,language.getString("failtolink"));
			e.printStackTrace();
		} catch (IOException e) {
			/*JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));*/
			e.printStackTrace();
		}
		this.language=l;
        super.frame.setSize(400,400);//register 界面大小
        super.frame.setLayout(null);
        registerPanel=new JPanel();
        registerPanel.setLayout(null);
        //账户类型
        JLabel typeLabel = new JLabel(language.getString("accounttype"));
		typeLabel.setBounds(70, 22, 75, 35);
		registerPanel.add(typeLabel);
        TypeBox = new JComboBox<String>();
		TypeBox.addItem(language.getString("savingaccount"));
		TypeBox.addItem(language.getString("creditaccount"));
		TypeBox.addItem(language.getString("loansavingaccount"));
		TypeBox.addItem(language.getString("loancreditaccount"));
		TypeBox.setBounds(160, 30, 145, 20);
		registerPanel.add(TypeBox);
		TypeBox.addActionListener(this);

		//用户名
		JLabel nameLabel = new JLabel(language.getString("name"));
		nameLabel.setBounds(70, 62, 75, 35);
		registerPanel.add(nameLabel);
        nameText = new JTextField();
		nameText.setBounds(160, 70, 145, 20);
		registerPanel.add(nameText);
        nameText.addActionListener(this);

		//密码
		JLabel passwordLabel = new JLabel(language.getString("password"));
		passwordLabel.setBounds(70, 102, 75, 35);
		registerPanel.add(passwordLabel);
        passwordText = new JTextField();
		passwordText.setBounds(160, 110, 145, 20);
		registerPanel.add(passwordText);
        passwordText.addActionListener(this);

		//确认密码
		JLabel repasswordLabel = new JLabel(language.getString("repassword"));
		repasswordLabel.setBounds(70, 142, 75, 35);
		registerPanel.add(repasswordLabel);
		repasswordText=new JTextField();
		repasswordText.setBounds(160, 150, 145, 20);
		registerPanel.add(repasswordText);
        repasswordText.addActionListener(this);

        //身份证号
		JLabel personIdLabel = new JLabel(language.getString("personId"));
		personIdLabel.setBounds(70, 182, 75, 35);
		registerPanel.add(personIdLabel);
        personIdText = new JTextField();
		personIdText.setBounds(160, 190, 145, 20);
		registerPanel.add(personIdText);
        personIdText.addActionListener(this);
    
		//E-Mail
		JLabel emailLabel = new JLabel(language.getString("email"));
		emailLabel.setBounds(70, 222, 75, 35);
		registerPanel.add(emailLabel);
		emailText = new JTextField();
		emailText.setBounds(160, 230, 145, 20);
		registerPanel.add(emailText);
        emailText.addActionListener(this);




		submit=language.getString("submit");
		back=language.getString("back");
        //提交与返回
		JButton submitbutton = new JButton(submit);
		submitbutton.setBounds(80, 290, 90, 30);
		registerPanel.add(submitbutton);
        submitbutton.addActionListener(this);
		
		JButton backbutton = new JButton(back);
		backbutton.setBounds(180, 290, 90, 30);
		registerPanel.add(backbutton);
        backbutton.addActionListener(this);
        this.frame.setContentPane(registerPanel);//添加至容器
		this.frame.setLocationRelativeTo(null);//将界面调整至中心
		
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
						JOptionPane.showMessageDialog(this.frame,language.getString("succeedregister")+rTo.getToAccount().getId());
						BusinessPanel businessPanel=new BusinessPanel(rTo.getToAccount(),this.language);//转到busine界面
						businessPanel.frame.setVisible(true);
						this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(this.frame,language.getString(rTo.getException()));
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
			MainPanel mainpanel=new MainPanel(this.language);
            this.frame.setVisible(false);
            mainpanel.frame.setVisible(true);
		}
    }
	
}
