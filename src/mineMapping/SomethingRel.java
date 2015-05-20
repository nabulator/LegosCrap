package mineMapping;

import java.awt.*;
import java.util.Date;
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
	private static UltrasonicSensor uss;

	public static Point pos;
	public static char dir;
	public static long timeStamp;

	private static final int MIN_DIS_FROM_WALL = 23;
	private static final int WALL_ADJ = 3;

	public static void main(String[] args) throws InterruptedException 
	{
		TouchSensor ts = new TouchSensor(SensorPort.S1);
		uss = new UltrasonicSensor(SensorPort.S2);

		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);

		moveForward();

		data = new ArrayList<String>();
		pos = new Point(0, 0);
		dir = 'u';
		timeStamp = System.currentTimeMillis();

		int count = 0;
		final int MAX_COUNTER = 5;
		while(!finished() && Button.ENTER.isUp())
		{
			if(uss.getDistance() > MIN_DIS_FROM_WALL + WALL_ADJ)
				count++;

			//left
			if(count >= MAX_COUNTER)
			{
				turnLeft();
				Thread.sleep(1000);
				count = 0;
			}
			//right
			else if(ts.isPressed())
			{
				changeData('r');
				turnRight();
				Thread.sleep(1000);
				count = 0;
			}

			moveForward();
		}

		stopMoving();
		
		System.out.println("Done");
		
		playMusic();
		
		Thread.sleep(4000);


		int pointer = 0;

		printData(pointer);

		while(Button.ENTER.isUp())
		{


			if(Button.RIGHT.isDown())
			{
				while( Button.RIGHT.isDown() );

				if(pointer < data.size()/2 - 7)
				{
					pointer++;
				}

				printData(pointer);

			}
			else if(Button.LEFT.isDown())
			{
				while( Button.LEFT.isDown() );

				if(pointer > 0)
				{
					pointer--;
				}
				printData(pointer);
			}
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
			x+= distance;
		else if(dir == 'l')
			x-= distance;

		changeDir(turn);
		pos = new Point(x, y);

	}

	private static void printData(int pointer)
	{
		if(data.size() < 15)
			for(int i = 0; i < data.size()/2; i++)
				System.out.println("Go: " + data.get(i * 2) + 
					" turn:" + data.get((i * 2) + 1));
		else
			for(int i = 0; i < 7; i++)
				System.out.println("Go: " + data.get((i + pointer)*2) + 
					" turn:" + data.get((i + pointer)*2 + 1));
		
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
		final int STARTING_RADIUS= 100;
		int x = pos.x;
		int y = pos.y;

		return System.currentTimeMillis() - timeStamp > 8000 && STARTING_RADIUS >= Math.sqrt((x*x) - (y*y));
	}

	final static int DEGREES_FOR_TURN = 174;
	final static int DEGREES_FOR_ADJUSTMENT = 200;
	public static void turnLeft()
	{
		stopMoving();

		//		rotate(DEGREES_FOR_ADJUSTMENT);

		changeData('l');

		rightWheel.setAcceleration(100);
		leftWheel.setAcceleration(100);
		leftWheel.rotate(-DEGREES_FOR_TURN, true);
		rightWheel.rotate(DEGREES_FOR_TURN, false);
		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);

		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();

		//SECOND ADJUSTMENT
		rotate(830);
	}

	final static int DEGREES_FOR_RIGHT = 6;

	public static void turnRight()
	{
		stopMoving();


		rotate(-DEGREES_FOR_ADJUSTMENT);

		rightWheel.setAcceleration(100);
		leftWheel.setAcceleration(100);
		leftWheel.rotate(DEGREES_FOR_TURN + DEGREES_FOR_RIGHT, true);
		rightWheel.rotate(-DEGREES_FOR_TURN + DEGREES_FOR_RIGHT, false);
		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);

		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
	}

	public static void stopMoving()
	{
		rightWheel.stop(true);
		leftWheel.stop(false);
	}

	public static void moveForward()
	{
		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);
		rightWheel.forward();
		leftWheel.forward();
	}

	private static void rotate( int degs )
	{
		leftWheel.rotate(degs, true);
		rightWheel.rotate(degs, false);
	}
	
	private static void playMusic()
	{
		Thread d = new Thread(new Music());
		d.start();
		Button.waitForAnyPress();
	}

}