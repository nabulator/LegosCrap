package numberGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class Recieve 
{
	public static void main(String[] args)
	{
		String connected = "Connected";
		String waiting = "Waiting...";
		String closing = "Closing...";
		UltrasonicSensor ss = new UltrasonicSensor(SensorPort.S1);

		while (true) {
			LCD.drawString(waiting, 0, 0);
			NXTConnection connection = Bluetooth.waitForConnection();
			LCD.clear();
			LCD.drawString(connected, 0, 0);

			DataInputStream dis = connection.openDataInputStream();
			DataOutputStream dos = connection.openDataOutputStream();
		}
	}
}
