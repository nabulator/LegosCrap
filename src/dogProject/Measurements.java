package dogProject;

import lejos.nxt.*;

public class Measurements 
{
	public static void main(String[] args)
	{
//		Motor.B.rotate(741,true);
//		Motor.C.rotate(741,true);
//
//		Button.waitForAnyPress();
//		
//		Motor.B.rotate(158,true);
//		Motor.C.rotate(158,false);
		Button.waitForAnyPress();
	}
	
	public static void quarterTurn()
	{
		Motor.B.setAcceleration(2000);
		Motor.C.setAcceleration(2000);
		Motor.C.rotate(200, true);
		Motor.B.rotate(-200, false);
	}
	
	/**
	 * Converts cenitmeters to degrees rotation on robot.
	 * @param 
	 * @return
	 */
	public int cm2d ( int cm )
	{
		return cm * 17;
	}
	
}
