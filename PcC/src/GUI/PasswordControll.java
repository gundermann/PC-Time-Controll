package GUI;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PasswordControll extends JFrame{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1667903866736894685L;
	String _name;

	public PasswordControll(String n){
		super(n);
		set_name(n);
		setSize(250, 166);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		GUI();
	}
	
	public void GUI(){
		//Initialize Panels
		JPanel p_main = new JPanel();
		//Initialize Label
		JLabel lb_pw = new JLabel("Passwort:");
		//Initialize Button
		JButton bt_submit = new JButton("OK");
		//Initialize Textfields
		final JTextField tf_pw = new JTextField();
		
				//Set Panel
				p_main.setLayout(new GridLayout(3,1));
		
				//Set Label
				lb_pw.setBounds(0, 60, 160, 10);
				
				//Set Textfields
				tf_pw.setBounds(0,80, 160, 20);
				
				//Show Panels
				getContentPane().add(p_main);
			
				//Show Label
				p_main.add(lb_pw);
				p_main.add(tf_pw);
				//Show Buttons
				p_main.add(bt_submit);

			//Actionlisteners
			//bt_submit
			bt_submit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				
					try {
						FileReader fr = new FileReader("settings.bin");
						BufferedReader br = new BufferedReader(fr);
						
						br.readLine();
						String pw = br.readLine();
						
						if(tf_pw.getText().equals(pw.substring(3))){
							
							if(get_name().equals("Einstellung")){
							Settings setter = new Settings("Einstellung");
							setter.setVisible(true);
							}
							else if (get_name().equals("Schlieﬂen")){
								System.exit(EXIT_ON_CLOSE);
							}
						}
						
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						dispose();
					}
				
					
				}
			});
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}
	
	
}
