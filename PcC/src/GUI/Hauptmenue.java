package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Programm.Timer;


public class Hauptmenue extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674250087507166707L;
	
	
	
	

	public Hauptmenue(String n){
		super(n);
		setSize(250, 156);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(0);
		GUI();
	}
	
	public void GUI(){
		
		
	
		//Initialize Panels
		JPanel p_main = new JPanel();
		JPanel p_time = new JPanel();
		JPanel p_controll = new JPanel();
		//Initialize Label
		JLabel lb_time = new JLabel("00:00");
		//Initialize Button
		JButton bt_close = new JButton("Schlieﬂen");
		JButton bt_setTime = new JButton("Einstellungen");
		JButton bt_controlledClose = new JButton("Herunterfahren");
		JButton bt_log = new JButton("Protokoll");
		
		final Timer time = new Timer(System.currentTimeMillis(), lb_time);
		
	
		//Set Label
		lb_time.setBounds(20, 20, 160, 160);
		lb_time.setFont(new Font(getName(), getState(), 30));
		
		//Set Panels
		p_time.setBounds(0, 0, 200, 200);
		p_controll.setLayout(new GridLayout(2,2));
		p_controll.setBounds(0, 300, 200, 100);
		
		//Set Buttons
		
		//Show Panels
		getContentPane().add(p_main);
		p_main.add(p_time);
		p_main.add(p_controll);
		//Show Label
		p_time.add(lb_time);
		//Show Buttons
		p_controll.add(bt_controlledClose);
		p_controll.add(bt_log);
		p_controll.add(bt_setTime);
		p_controll.add(bt_close);
		
	
			time.start();
			
			System.out.println(time.get_leftTime());
			lb_time.setText(String.valueOf(time.get_leftTime()));
			
		
		
		
		//Actionlistener
		//bt_close: Close the program without saving the time
		bt_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PasswordControll PWC = new PasswordControll("Schlieﬂen");
				PWC.setVisible(true);
			}
		});
		
		//bt_controlledClose: 
		bt_controlledClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					time.get_Verwalter().shutdown(time.get_leftTime());
				} catch (StringIndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//bt_setTime:
		bt_setTime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PasswordControll PWC = new PasswordControll("Einstellung");
				PWC.setVisible(true);
				
			}
		});
		
		//bt_log:
		bt_log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Protokoll log = new Protokoll("log");
				log.setVisible(true);
			}
		});
	}
	
}
