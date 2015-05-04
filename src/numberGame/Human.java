package numberGame;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Human 
{
	public static boolean myTurn = true;
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		String name = "Group 8";
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
	    
	    Player game = new Player();
	    game.init();
	    
	    boolean isAliveOpp = true;
	    while( game.isAlive() && isAliveOpp )
	    {
	    	Point p = game.myTurn();
	    	dos.writeInt( p.x );
	    	dos.writeInt( p.y );
	    	dos.flush();
	    	
	    	boolean isHit = dis.readBoolean();
	    	isAliveOpp = dis.readBoolean();
	    	
	    	game.updateOppBoard(p, isHit);
	    	
	    	if( ! isAliveOpp )
	    		break;
	    	
	    	Thread.sleep(1000);
	    	
	    	int xOpp = dis.readInt();
	    	int yOpp = dis.readInt();
	    	
	    	Point pOpp = new Point(xOpp, yOpp);
	    	boolean isalreadyhit = game.recieveHit(pOpp);
	    	dos.writeBoolean( isalreadyhit );
	    	dos.writeBoolean( game.isAlive() );
	    	dos.flush();
	    }
	    
	    LCD.clear();
	    if( game.isAlive() )
	    {
	    	LCD.drawString("YOU WON!!!!", 3, 4);
	    	Sound.playSample(new File("FF9.wav"));
	    	Sound.systemSound(true, 2);
	    }
	    else
	    {
	    	LCD.drawString("You suck", 3, 4);
	    	Sound.systemSound(true, 4);
	    }
	    
	    Thread.sleep(3000);
	    
	    Button.waitForAnyPress();
	}	
}
