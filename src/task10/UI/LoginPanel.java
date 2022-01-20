package task10.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import task10.Account.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import task10.CS.*;
import task10.LanguageSource.Language;

public class LoginPanel extends ATMClient implements ActionListener{
    private JPanel loginPanel;
    JTextField IDText;
    JTextField passwordText;
    private UserClient user;
    //fucntion Button
    String login;
    String back;
    private Language language;
    public LoginPanel(Language l){
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
        //用户名
        loginPanel=new JPanel();
        loginPanel.setLayout(null);
        JLabel IDLabel = new JLabel(language.getString("id"));
		IDLabel.setBounds(90, 52, 75, 35);
		loginPanel.add(IDLabel);
        IDText = new JTextField();
		IDText.setBounds(160, 60, 135, 20);
		loginPanel.add(IDText);
        IDText.addActionListener(this);
        //密码
        JLabel passwordLabel = new JLabel(language.getString("password"));
		passwordLabel.setBounds(90, 92, 75, 35);
		loginPanel.add(passwordLabel);
        passwordText = new JTextField();
		passwordText.setBounds(160, 100, 135, 20);
		loginPanel.add(passwordText);
        passwordText.addActionListener(this);
        //登录按钮
        login=language.getString("login");
        back=language.getString("back");
        JButton loginbButton = new JButton(login);
		loginbButton.setBounds(95, 160, 90, 30);
		loginPanel.add(loginbButton);
        loginbButton.addActionListener(this);
		//返回按钮
		JButton backbutton = new JButton(back);
		backbutton.setBounds(195, 160, 90, 30);
		loginPanel.add(backbutton);
        backbutton.addActionListener(this);
        this.frame.setContentPane(loginPanel);//添加至容器
        this.frame.setLocationRelativeTo(null);
    }
   
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if(action.equals(login)){
            long ID = Long.parseLong(IDText.getText());
			String password=passwordText.getText();
            Account account=null;
            To  to=new To("login",account);
            to.setId(ID);to.setPassword(password);
            To  rto=this.CSAction(to);
			    if(rto.getToAccount()!=null){
                    JOptionPane.showMessageDialog(this.frame,language.getString("loginsucceed"));
			     	BusinessPanel businessPanel=new BusinessPanel(rto.getToAccount(),this.language);//转到busine界面
			     	businessPanel.frame.setVisible(true);
						this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(this.frame,language.getString(rto.getException()));
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
    public To CSAction(To to){//与服务器交互
        try {
            user.SendMessage(to);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
            e1.printStackTrace();
        }
            To to1=null;
            try {
                to1 = user.ReceiveMessage();
            } catch (ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(this.frame,language.getString("classnotfound"));
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this.frame,language.getString("ioexception"));
                e1.printStackTrace();
            }
            return to1;
    }
}
