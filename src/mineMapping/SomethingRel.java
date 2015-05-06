package mineMapping;

import java.awt.Point;
import java.util.ArrayList;

import lejos.nxt.*;

/**
 * This controls the robot that searches
 * (thus doing something relevant)
 * @author 60129
 *
 */
public class SomethingRel {

	private static NXTRegulatedMotor rightWheel = Motor.B;
	private static NXTRegulatedMotor leftWheel = Motor.C;
	private static ArrayList<String> data;
	
	public static Point pos;
	public static char dir;
	
	public static void main(String[] args) throws InterruptedException 
	{
		TouchSensor ts = new TouchSensor(SensorPort.S1);
		UltrasonicSensor uss = new UltrasonicSensor(SensorPort.S2);
		
		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);
		
		final int MIN_DIS_FROM_WALL = 10;
		
		moveForward();
		
		data = new ArrayList<String>();
		pos = new Point(0, 0);
		dir = 'u';
		
		while(!finished())
		{
			if(uss.getDistance() > MIN_DIS_FROM_WALL)
			{
				changeData('l');
				turnLeft();
				Thread.sleep(1000);
			}
			
			else if(ts.isPressed())
			{
				changeData('r');
				turnRight();
				Thread.sleep(1000);
			}

			moveForward();
		}
	}
	
	public static void changeData(char turn)
	{
		int distance = Motor.B.getTachoCount();
		data.add(String.valueOf(distance));
		
		data.add(String.valueOf(turn));
		
		int x = pos.x;
		int y = pos.y;
		
		if(dir == 'u')
			y+= distance;
		else if(dir == 'd')
			y-= distance;
		else if(dir == 'r')
			y+= distance;
		else if(dir == 'l')
			y-= distance;
		
		changeDir(turn);
		pos = new Point(x, y);
	}
	
	private static void changeDir(char turn)
	{
		if(turn == 'l')
		{
			if(dir == 'u')
				dir = 'l';
			else if(dir == 'l')
				dir = 'd';
			else if(dir == 'd')
				dir = 'r';
			else if(dir == 'r')
				dir = 'u';
		}
		else if(turn == 'r')
		{
			if(dir == 'u')
				dir = 'r';
			else if(dir == 'r')
				dir = 'd';
			else if(dir == 'd')
				dir = 'l';
			else if(dir == 'l')
				dir = 'u';
		}
	}

	public static boolean finished()
	{
		final int STARTING_RADIUS= 180;
		int x = pos.x;
		int y = pos.y;
		
		return STARTING_RADIUS <= Math.sqrt((x*x) - (y*y));
	}
	
	final static int DEGREES_FOR_TURN = 200;
	final static int DEGREES_FOR_ADJUSTMENT = 100;
	public static void turnLeft()
	{
		stopMoving();
		
		leftWheel.rotate(DEGREES_FOR_ADJUSTMENT, true);
		rightWheel.rotate(DEGREES_FOR_ADJUSTMENT, false);

		leftWheel.rotate(-DEGREES_FOR_TURN, true);
		rightWheel.rotate(DEGREES_FOR_TURN, false);
		
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
	}
	
	public static void turnRight()
	{
		stopMoving();
		
		
		leftWheel.rotate(-DEGREES_FOR_ADJUSTMENT, true);
		rightWheel.rotate(-DEGREES_FOR_ADJUSTMENT, false);
		
		leftWheel.rotate(DEGREES_FOR_TURN, true);
		rightWheel.rotate(-DEGREES_FOR_TURN, false);
		
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
	}
	
	public static void stopMoving()
	{
		rightWheel.stop();
		leftWheel.stop();
	}
	
	public static void moveForward()
	{
		rightWheel.forward();
		leftWheel.forward();
	}
}