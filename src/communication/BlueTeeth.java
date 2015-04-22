package communication;

import java.io.*;
import javax.bluetooth.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

public class BlueTeeth {

	public static void main(String[] args) {
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
	    LCD.drawString("Connected", 0, 0);
	    
	    for(int i=0;i<100;i++)
	    {
	    	try
	    	{		 
	    		LCD.drawInt(i*30000, 8, 0, 2);
	    		dos.writeInt(i*30000);
	    		dos.flush();
	        }
	    	catch (IOException ioe)
	    	{
	    			LCD.drawString("Write Exception", 0, 0);
	        }
	    	
	        try 
	        {
	        	LCD.drawInt(dis.readInt(),8, 0,3);
	        }
	        catch (IOException ioe)
	        {
	        	LCD.drawString("Read Exception ", 0, 0);
	        }
	      }
	    
	    //CLEANUP CODE
	    try
	    {
	        LCD.drawString("Closing... ", 0, 0);
	        dis.close();
	        dos.close();
	        b.close();
	    }
	    catch (IOException ioe) 
	    {
	        LCD.drawString("Close Exception", 0, 0);
	    }
	    
	      LCD.drawString("Finished",3, 4);
	      Button.waitForAnyPress();
	}

}
