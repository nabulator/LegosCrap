package mineMapping;

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
	
	public static void main(String[] args) throws InterruptedException 
	{
		TouchSensor ts = new TouchSensor(SensorPort.S1);
		UltrasonicSensor uss = new UltrasonicSensor(SensorPort.S2);
		
		rightWheel.setAcceleration(1000);
		leftWheel.setAcceleration(1000);
		
		final int MIN_DIS_FROM_WALL = 10;
		
		moveForward();
		while(!finished())
		{
			if(uss.getDistance() > MIN_DIS_FROM_WALL)
			{
				turnLeft();
				Thread.sleep(1000);
			}
			
			else if(ts.isPressed())
			{
				turnRight();
				Thread.sleep(1000);
			}

			moveForward();
		}

	}

	public static boolean finished()
	{
		return false;
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
		
	}
	
	public static void turnRight()
	{
		stopMoving();
		
		
		leftWheel.rotate(-DEGREES_FOR_ADJUSTMENT, true);
		rightWheel.rotate(-DEGREES_FOR_ADJUSTMENT, false);
		
		leftWheel.rotate(DEGREES_FOR_TURN, true);
		rightWheel.rotate(-DEGREES_FOR_TURN, false);
		
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
