package task8.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends ATMClient implements ActionListener{
    private JPanel mainJPanel;
    public MainPanel(){
        mainJPanel=new JPanel();
        //welcom 
        JLabel bamJLabel = new JLabel("模拟  ICBC ATM终端 ");
		bamJLabel.setBounds(130, 40, 260, 40);
		mainJPanel.add(bamJLabel);
        //button
        mainJPanel.setLayout(null);//调整至正中间
        JButton RegisterButton=new JButton("登录");
        RegisterButton.addActionListener(this);
        RegisterButton.setBounds(70, 150, 80, 30);
        mainJPanel.add(RegisterButton);
        JButton LoginButton=new JButton("注册");
        LoginButton.addActionListener(this);
        LoginButton.setBounds(220, 150, 80, 30);
        mainJPanel.add(LoginButton);
        super.frame.setContentPane(mainJPanel);
        
    }
    public static void main(String[] args){
        MainPanel mainwindow=new MainPanel();
        
        mainwindow.frame.setVisible(true);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if("注册".equals(action)){
			RegisterPanel registeraction=new RegisterPanel();
            this.frame.setVisible(false);
            registeraction.frame.setVisible(true);
		}
		
		if("登录".equals(action)){
			LoginPanel loginaction=new LoginPanel();
            this.frame.setVisible(false);//关闭之前的窗口
            loginaction.frame.setVisible(true);
		}
	}
    
}
