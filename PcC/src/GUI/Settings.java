package GUI;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4052558773530859700L;

	public Settings(String n){
		super(n);
		setSize(250, 166);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		GUI();
	}
	
	public void GUI(){
		//Initialize Panels
		JPanel p_main = new JPanel();
		JPanel p_time = new JPanel();
		JPanel p_controll = new JPanel();
		//Initialize Label
		JLabel lb_time = new JLabel("Maximale Zeit in Minuten:");
		JLabel lb_pw = new JLabel("Passwort:");
		//Initialize Button
		JButton bt_submit = new JButton("Übernehmen");
		JButton bt_abort = new JButton("Abbrechen");
		//Initialize Textfields
		final JTextField tf_time = new JTextField();
		final JTextField tf_pw = new JTextField();
		
		//Set Label
		lb_time.setBounds(0, 20, 160, 10);
		lb_pw.setBounds(0, 60, 160, 10);
				
		//Set Panels
		p_time.setLayout(new GridLayout(4,1));
		p_time.setBounds(0, 0, 200, 220);
		p_controll.setLayout(new GridLayout(1,2));
		p_controll.setBounds(0, 400, 200, 50);
		
		//Set Textfields
		tf_time.setBounds(0,20, 160, 20);
		tf_pw.setBounds(0,80, 160, 20);
		
		//Show Panels
		getContentPane().add(p_main);
		p_main.add(p_time);
		p_main.add(p_controll);
		//Show Label
		p_time.add(lb_time);
		p_time.add(tf_time);
		p_time.add(lb_pw);
		p_time.add(tf_pw);
		//Show Buttons
		p_controll.add(bt_submit);
		p_controll.add(bt_abort);

		
		//bt_abort: abort the Menue
		bt_abort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		//bt_submit: set the new settings
		bt_submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					FileReader fr = new FileReader("settings.bin");
					BufferedReader br = new BufferedReader(fr);
					
					String time = br.readLine();
					String pw = br.readLine();
					
					FileWriter fw = new FileWriter("settings.bin");
					BufferedWriter bw = new BufferedWriter(fw);
					
					if(!tf_time.getText().equals("") & !tf_pw.getText().equals("")){
						try{
							long newTime = Long.valueOf(tf_time.getText());
							long timeToSet = newTime*60*1000;
							
							bw.write("settedTime:"+timeToSet+"\n");
							bw.write("PW:"+tf_pw.getText());
							
							bw.close();
							fw.close();
							
							br.close();
							fr.close();
						} catch (NumberFormatException nfe){
							tf_time.setText("Nur Zahlen erlaubt1.");
						}
						
					}else if (!tf_time.getText().equals("")){
						try{
							long newTime = Long.valueOf(tf_time.getText());
							long timeToSet = newTime*60*1000;
							
							bw.write("settedTime:"+timeToSet+"\n");
							bw.write(pw);
							
							bw.close();
							fw.close();
							
							br.close();
							fr.close();
						} catch (NumberFormatException nfe){
							tf_time.setText("Nur Zahlen erlaubt2.");
						}
						
					}else if(!tf_pw.getText().equals("")){
						
						bw.write(time+"\n");
						bw.write("PW:"+tf_pw.getText());
						
						bw.close();
						fw.close();
						
						br.close();
						fr.close();
				
					}
					
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String leerString = "";
				tf_pw.setText(leerString);
				tf_time.setText(leerString);
				dispose();
				
				
				
				
			}
		});
	
	
		
		
	}
}
