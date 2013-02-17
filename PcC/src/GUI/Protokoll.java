package GUI;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Protokoll extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1156866743719441048L;


	public Protokoll(String n){
		super(n);
		setSize(300, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GUI();
	}
	
	public void GUI(){
		
		
		//Initialize Panels
		JPanel p_main = new JPanel();
				
		//Initialize Label
		JTextArea ta_log =  new JTextArea();
		
		//Set Panel
		p_main.setLayout(new GridLayout(1, 1));
		
		//Add Panel
		getContentPane().add(p_main);
		
		//Add TestArea
		p_main.add(ta_log);
		
		try {
			ta_log.append(load("zeiten.log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String load(String filename) throws IOException, FileNotFoundException{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		String textInDatei = "";
		
		while(line != null){
			textInDatei = textInDatei + "\n"+line;
			
			line =  br.readLine();
		}
		
		return textInDatei;
	}
}
