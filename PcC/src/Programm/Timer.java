package Programm;

import java.io.IOException;

import javax.swing.JLabel;



public class Timer extends Thread{
	
	private long _startTime;
	private long _systemTime;
	private long _leftTime;
	private JLabel lb;
	Verwaltung verwalter = new Verwaltung();
	
	
	public Timer(long startTime, JLabel lb){
		set_startTime(startTime);
		setLb(lb);
		try {
			verwalter.loadSettedTime();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException npe){
			npe.printStackTrace();
		}
	}

	public JLabel getLb() {
		return lb;
	}

	public void setLb(JLabel lb) {
		this.lb = lb;
	}

	public long get_startTime() {
		return _startTime;
	}

	public void set_startTime(long _startTime) {
		this._startTime = _startTime;
	}

	public long get_systemTime() {
		return _systemTime;
	}

	public void set_systemTime(long _systemTime) {
		this._systemTime = _systemTime;
	}

	public long get_leftTime() {
		return _leftTime;
	}

	public void set_leftTime(long _leftTime) {
		this._leftTime = _leftTime;
	}
	
	public void run() {
		while(Timer.currentThread().isAlive()){
			
			if(verwalter.get_settedTime()==0){
				try {
					sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					verwalter.close();
				}
			}
			
			set_systemTime(System.currentTimeMillis());
			set_leftTime(_systemTime - _startTime);
		
			if(verwalter.get_settedTime() < _leftTime){
				try {
					verwalter.shutdown(get_leftTime());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (StringIndexOutOfBoundsException sioobe){
					sioobe.printStackTrace();
				}
			}
		
			getLb().setText(umformen(_leftTime));
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String umformen(long leftTime){
		String changedTime = null;
		long timeInSec = leftTime/1000;
		String minutes = "00";
		String sec= "00";
		
		if(timeInSec%60 < 10){
			sec= "0"+timeInSec%60;
		}
		else if(timeInSec%60 < 60){
			sec = String.valueOf(timeInSec%60);
		}
		
		if(timeInSec >= 60 & timeInSec < 600){
			minutes = "0"+String.valueOf((timeInSec-timeInSec%60)/60);
		}
		else if(timeInSec >= 600){
			minutes = String.valueOf((timeInSec-timeInSec%60)/60);
		}
		changedTime = minutes + ":"+sec;
		
		return changedTime;
	}
	
	public Verwaltung get_Verwalter(){
		return verwalter;
	}

}
