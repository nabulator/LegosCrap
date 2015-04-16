package dogProject;

import lejos.nxt.*;

public class Measurements 
{
	
	public static void quarterRightTurn()
	{
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		Motor.C.rotate(200, true);
		Motor.B.rotate(-200, false);
	}
	
	public static void quarterLeftTurn()
	{
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		Motor.C.rotate(-200, true);
		Motor.B.rotate(200, false);
	}
	
	public static void moveForward(int cm)
	{
		int degrees = cm2d(cm);
		Motor.B.rotate(degrees,true);
		Motor.C.rotate(degrees,false);
	}
	
	public static void moveBackward(int cm)
	{
		int degrees = cm2d(cm);
		Motor.B.rotate(-degrees,true);
		Motor.C.rotate(-degrees,false);
	}
	
	static int LIFT_DEGREE = 65;
	public static void liftUp()
	{
		Motor.A.rotate(-LIFT_DEGREE);
	}
	
	public static void liftDown()
	{
		Motor.A.rotate(LIFT_DEGREE);
	}
	
	public static void liftUpHalf()
	{
		Motor.A.rotate(-LIFT_DEGREE/2);
	}
	
	
	/**
	 * Converts cenitmeters to degrees rotation on robot.
	 * @param 
	 * @return
	 */
	public static int cm2d ( int cm )
	{
		return (int) ((cm / 17.0) * 360);
	}
	
}
