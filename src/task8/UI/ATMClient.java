package task8.UI;

import javax.swing.JFrame;

public class ATMClient{
    public JFrame frame;
    public ATMClient(){
        frame=new JFrame();
        frame.setLayout(null);
        frame.setTitle("BAM Client");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}