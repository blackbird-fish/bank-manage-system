package task10.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import task10.LanguageSource.Language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends ATMClient implements ActionListener{
    private JPanel mainJPanel;
    String login;
    String register;
    private Language language;
    public MainPanel(Language l){
        this.language=l;
        mainJPanel=new JPanel();
        //welcom 
        JLabel bamJLabel = new JLabel(language.getString("maintitle"));
		bamJLabel.setBounds(130, 40, 260, 40);
		mainJPanel.add(bamJLabel);
        //button
        mainJPanel.setLayout(null);//调整至正中间

        login=language.getString("login");
        register=language.getString("register");

        
        JButton RegisterButton=new JButton(login);
        RegisterButton.addActionListener(this);
        RegisterButton.setBounds(70, 150, 90, 30);
        mainJPanel.add(RegisterButton);
        JButton LoginButton=new JButton(register);
        LoginButton.addActionListener(this);
        LoginButton.setBounds(220, 150, 90, 30);
        mainJPanel.add(LoginButton);
        this.frame.setContentPane(mainJPanel);
        this.frame.setLocationRelativeTo(null);
        
    }
   
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if(action.equals(register)){
			RegisterPanel registeraction=new RegisterPanel(this.language);
            this.frame.setVisible(false);
            registeraction.frame.setVisible(true);
		}
		if(action.equals(login)){
			LoginPanel loginaction=new LoginPanel(this.language);
            this.frame.setVisible(false);//关闭之前的窗口
            loginaction.frame.setVisible(true);
		}
	}
    
}
