package task9.UI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;


public class MainPanel extends ATMClient implements ActionListener{
    private JPanel mainJPanel;
    String login="登录";
    String register="注册";
    public MainPanel(){
        mainJPanel=new JPanel();
        //welcom 
        JLabel bamJLabel = new JLabel("模拟  ICBC ATM终端 ");
		bamJLabel.setBounds(130, 40, 260, 40);
		mainJPanel.add(bamJLabel);
        //button
        mainJPanel.setLayout(null);//调整至正中间
        
        JButton RegisterButton=new JButton(login);
        RegisterButton.addActionListener(this);
        RegisterButton.setBounds(70, 150, 80, 30);
        mainJPanel.add(RegisterButton);
        JButton LoginButton=new JButton(register);
        LoginButton.addActionListener(this);
        LoginButton.setBounds(220, 150, 80, 30);
        mainJPanel.add(LoginButton);
        super.frame.setContentPane(mainJPanel);
        
    }
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel mainwindow = new MainPanel();
					mainwindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if(action.equals(register)){
			RegisterPanel registeraction=new RegisterPanel();
            this.frame.setVisible(false);
            registeraction.frame.setVisible(true);
		}
		if(action.equals(login)){
			LoginPanel loginaction=new LoginPanel();
            this.frame.setVisible(false);//关闭之前的窗口
            loginaction.frame.setVisible(true);
		}
	}
    
}
