package task10.UI;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import task10.LanguageSource.Language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;


public class LanguagePanel extends ATMClient implements ActionListener{
    private JPanel LanguagePanel;
    JComboBox<String> Languagechoice;
    private JButton selectButton;
    public LanguagePanel(){
        LanguagePanel=new JPanel();
        this.frame.setSize(300,250);
        //welcom 
        JLabel messageLabel = new JLabel("Choose Your Language");
		messageLabel.setBounds(80, 40, 260, 40);
		LanguagePanel.add(messageLabel);
        //button
        LanguagePanel.setLayout(null);
        Languagechoice = new JComboBox<String>();
        Languagechoice.setBounds(75,90,150,30);
        Languagechoice.addItem("中文");
		Languagechoice.addItem("English");
        LanguagePanel.add(Languagechoice);
        Languagechoice.addActionListener(this);


        selectButton=new JButton("Select");
        selectButton.setBounds(75,130,150,30);
        LanguagePanel.add(selectButton);
        selectButton.addActionListener(this);
        this.frame.setContentPane(LanguagePanel);
        this.frame.setLocationRelativeTo(null); //调整至正中间

        
    }
    public static void main(String[] args){
        LanguagePanel languagePanel=new LanguagePanel();
        languagePanel.frame.setVisible(true);
        
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
		if(action.equals("Select")){
            int choice = Languagechoice.getSelectedIndex();
            Language l=new Language(choice);
            this.frame.setVisible(false);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MainPanel mainwindow = new MainPanel(l);
                        mainwindow.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
		}
	}
    
}
