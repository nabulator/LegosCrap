package numberGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Human 
{
	public static void main(String[] args)
	{
		String name = "Group 6";
		LCD.drawString("Connecting...", 0, 8);
		RemoteDevice r = Bluetooth.getKnownDevice(name);
		
		//device name search fails-
		if (r == null) {
		      LCD.clear();
		      LCD.drawString("No such device", 0, 0);
		      Button.waitForAnyPress();
		      System.exit(1);
		}
		
		//connection fails
		BTConnection b = Bluetooth.connect(r);
		if (b == null)
		{
		      LCD.clear();
		      LCD.drawString("Connect fail", 0, 0);
		      Button.waitForAnyPress();
		      System.exit(1);
		}
		  
		DataInputStream dis = b.openDataInputStream();
	    DataOutputStream dos = b.openDataOutputStream();
	    
		LCD.clear();
	    LCD.drawString("THIS IS A HUMAN!!!", 0, 0);
	}
}
