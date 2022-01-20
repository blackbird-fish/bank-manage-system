package task10.CS;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class ATMServer implements ActionListener {
	    private JPanel ATMPanel;
	    private JFrame frame;
	    private static boolean Exit;
	    String open;
	    Socket socket;
	    SeverThread thread;
	    
	    public ATMServer(){
	        //ATM 界面
	    	ATMPanel=new JPanel();
	    	this.frame=new JFrame();
	    	frame.setSize(400,100);
	    	JLabel atmLabel = new JLabel("ATM server");
	 		ATMPanel.add(atmLabel);
	        open="开启";
	        JButton OpenButton=new JButton(open);
	        OpenButton.addActionListener(this);
	        OpenButton.setBounds(70, 150, 90, 30);
	        ATMPanel.add(OpenButton);
	        this.frame.setContentPane(ATMPanel);
	        this.frame.setLocationRelativeTo(null);
	        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    }
	
	
	    public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand() ;
			if(action.equals(open)){
				ServerSocket ss=null ;
				socket=null ;
				try {
					ss = new ServerSocket(9000) ;
					JOptionPane.showMessageDialog(this.frame,"服务器启动");
					while(true){
						if(Exit) {
							thread.interrupt();
							break;
						}
						socket = ss.accept() ;
						thread=new SeverThread(socket) ;
						thread.start();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}finally{
						try {
							if(socket != null){
								socket.close();
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						}
				}
			}
		}    
    public static void main(String[] args) {
    	ATMServer atm=new ATMServer();
    	atm.frame.setVisible(true);
		
	}
    
}
