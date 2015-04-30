package numberGame;

import java.io.File;

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
		//Player test = new Player();
		//test.init();
		//test.myTurn();
		System.out.println(Sound.playSample(new File("explosion9.wav")));
		Button.waitForAnyPress();
	}
}
d