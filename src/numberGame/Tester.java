package numberGame;

import lejos.nxt.*;
import lejos.robotics.Color;

/**
 * Completely useless GUI tester
 * @author 60129
 *
 */
public class Tester 
{
	public static void main(String[] args)
	{
		Player test = new Player();
		test.init();
		test.draw();
		
		Button.waitForAnyPress();
	}
}
