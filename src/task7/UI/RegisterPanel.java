package task7.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import task7.Account.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterPanel extends ATMClient implements ActionListener {
    private JPanel registerPanel;
	JComboBox <String>TypeBox;//账户类型
	JTextField nameText;//用户名
	JTextField passwordText;//密码
	JTextField repasswordText;//确认密码
	JTextField personIdText;//身份证
	JTextField emailText;//电子邮箱
	private Bank bank=Bank.getBank();
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
	
        //提交与f返回
		JButton submitbutton = new JButton("提交");
		submitbutton.setBounds(80, 290, 90, 30);
		registerPanel.add(submitbutton);
        submitbutton.addActionListener(this);
		
		JButton backbutton = new JButton("返回");
		backbutton.setBounds(180, 290, 90, 30);
		registerPanel.add(backbutton);
        backbutton.addActionListener(this);
        super.frame.setContentPane(registerPanel);//添加至容器
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand() ;
		
		if("提交".equals(action)){
				//获取注册信息
				bank=Bank.getBank();
				int type = TypeBox.getSelectedIndex()+1 ;
			    String name = nameText.getText() ;
				String password=passwordText.getText();
				String repassword=repasswordText.getText();
				String personId = personIdText.getText() ;
				String email = emailText.getText();
				try {
					Account a=bank.register(password,repassword, name, personId, email,type);
					String str="开户成功，ID为："+Long.toString(a.getId());
					JOptionPane.showMessageDialog(this.frame,str);
				} catch (RegisterException e1) {
					JOptionPane.showMessageDialog(this.frame,"两次密码不一致");
					e1.printStackTrace();
				}
		}
		
		if("返回".equals(action)){
			MainPanel mainpanel=new MainPanel();
            this.frame.setVisible(false);
            mainpanel.frame.setVisible(true);
		}
	}
}
