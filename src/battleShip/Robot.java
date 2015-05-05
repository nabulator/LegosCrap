package battleShip;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class Robot 
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		String connected = "Connected";
		String waiting = "Waiting...";

		LCD.drawString(waiting, 0, 0);
		NXTConnection connection = Bluetooth.waitForConnection();
		LCD.clear();
		LCD.drawString(connected, 0, 0);

		Player game = new Player();
		
		DataInputStream dis = connection.openDataInputStream();
		DataOutputStream dos = connection.openDataOutputStream();
		
	    game.init();
	    
	    Motor.B.setAcceleration(50);
	    Motor.C.setAcceleration(50);
	    
	    boolean isAliveOpp = true;
	    while( game.isAlive() && isAliveOpp )
	    {
	    	int xOpp = dis.readInt();
	    	int yOpp = dis.readInt();
	    	
	    	Point pOpp = new Point(xOpp, yOpp);
	    	boolean isalreadyhit = game.recieveHit(pOpp);
	    	dos.writeBoolean( isalreadyhit );
	    	dos.writeBoolean( game.isAlive() );
	    	dos.flush();
	    	
	    	if(isalreadyhit)
	    	{
	    		Motor.B.rotate(260, true);
	    		Motor.C.rotate(260);
	    	}
	    	
	    	if( ! game.isAlive() )
	    		break;
	    	
	    	Thread.sleep(1000);
	    	
	    	Point p = game.AITurn();
	    	dos.writeInt( p.x );
	    	dos.writeInt( p.y );
	    	dos.flush();
	    	
	    	boolean isHit = dis.readBoolean();
	    	isAliveOpp = dis.readBoolean();
	    	
	    	game.updateOppBoard(p, isHit);
	    	
	    }
	}
}
