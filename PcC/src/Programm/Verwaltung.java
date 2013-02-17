package Programm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Verwaltung {
	
	Date datum = new Date();

	private long _settedTime;
	private String _pw;
	
	public String get_pw() {
		return _pw;
	}

	public void set_pw(String _pw) {
		this._pw = _pw;
	}

	public long get_settedTime() {
		return _settedTime;
	}

	public void set_settedTime(long _settedTime) {
		this._settedTime = _settedTime;
	}
	
	public void loadSettedTime() throws NumberFormatException, IOException, NullPointerException{
		//Prüfung, ob heute schon gespielt
		
		
		FileReader fr2 = new FileReader("settings.bin");
		BufferedReader br2 = new BufferedReader(fr2);
		
		set_settedTime(Long.valueOf(br2.readLine().substring(11)));
		set_pw(br2.readLine().substring(3));
		
		FileReader fr = new FileReader("zeiten.log");
		BufferedReader br = new BufferedReader(fr);
		
		String line1=br.readLine();
		System.out.println(get_settedTime());
				
		while(line1.substring(0, line1.indexOf(" ")).equals(String.valueOf(datum.getDate())+String.valueOf(datum.getMonth())+String.valueOf(datum.getYear()))){
			
			set_settedTime(get_settedTime()-(Long.valueOf(line1.substring(line1.lastIndexOf(" ")+1))*1000));
			
			line1 = br.readLine();
			System.out.println(get_settedTime());
		}

		System.out.println(get_settedTime());
		
		br.close();
		fr.close();
		
		br2.close();
		fr2.close();

	}
	
	public void shutdown(long lefttime) throws IOException, StringIndexOutOfBoundsException{
		
		FileReader fr = new FileReader("zeiten.log");
		BufferedReader br = new BufferedReader(fr);
		
		String textInDatei = "";
		String line;
		while((line = br.readLine()) != null){
			textInDatei = textInDatei +"\n"+ line;
		}
		
		br.close();
		fr.close();
		
		FileWriter fw = new FileWriter("zeiten.log");
		BufferedWriter bw = new BufferedWriter(fw);
	
		StringBuilder sb = new StringBuilder();
		sb.append(datum.getDate());
		sb.append(datum.getMonth());
		sb.append(datum.getYear());
		sb.append(" [USEDTIME] ");
		sb.append(String.valueOf(lefttime).substring(0,String.valueOf(lefttime).length()-3));
		
		String neuerEintrag = sb.toString();
		
		bw.write(neuerEintrag +  textInDatei);
		
		bw.close();
		fw.close();
		
		Runtime.getRuntime().exec("c:/windows/system32/shutdown.exe -s -f -t 0");
	}
	
	public void close(){
		System.exit(0);
	}
	
	
}
