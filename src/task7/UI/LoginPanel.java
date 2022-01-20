package task7.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import task7.Account.*;

public class LoginPanel extends ATMClient implements ActionListener{
    private JPanel loginPanel;
    JTextField IDText;
    JTextField passwordText;
    private Bank bank=Bank.getBank();
    public LoginPanel(){
        //用户名
        loginPanel=new JPanel();
        loginPanel.setLayout(null);
        JLabel IDLabel = new JLabel("ID：");
		IDLabel.setBounds(90, 52, 75, 35);
		loginPanel.add(IDLabel);
        IDText = new JTextField();
		IDText.setBounds(160, 60, 135, 20);
		loginPanel.add(IDText);
        IDText.addActionListener(this);
        //密码
        JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setBounds(90, 92, 75, 35);
		loginPanel.add(passwordLabel);
        passwordText = new JTextField();
		passwordText.setBounds(160, 100, 135, 20);
		loginPanel.add(passwordText);
        passwordText.addActionListener(this);
        
        JButton loginbButton = new JButton("登录");
		loginbButton.setBounds(95, 160, 90, 30);
		loginPanel.add(loginbButton);
        loginbButton.addActionListener(this);
		
		JButton backbutton = new JButton("返回");
		backbutton.setBounds(195, 160, 90, 30);
		loginPanel.add(backbutton);
        backbutton.addActionListener(this);
        super.frame.setContentPane(loginPanel);//添加至容器
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		
		if("登录".equals(action)){
            long ID = Long.parseLong(IDText.getText());
			String password=passwordText.getText();
            try {
				Account a=bank.login(ID, password);
                JOptionPane.showMessageDialog(this.frame,"登录成功");
                BusinessPanel businessPanel=new BusinessPanel(a);
                this.frame.setVisible(false);
                businessPanel.frame.setVisible(true);
				} 
            catch (LoginException e2) {
                    JOptionPane.showMessageDialog(this.frame,"账户或密码错误");
					e2.printStackTrace();
				}
            
	    }
        if("返回".equals(action)){
			MainPanel mainpanel=new MainPanel();
            this.frame.setVisible(false);
            mainpanel.frame.setVisible(true);
		}
}
}
