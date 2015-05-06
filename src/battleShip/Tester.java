package battleShip;

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
	public static void main(String[] args) throws InterruptedException
	{
		//Player test = new Player();
		//test.init();
		//test.myTurn();
//		System.out.println(Sound.playSample(new File("FF9.wav")));
		
		while(true)
		{
			

			if(Button.ENTER.isDown())
			{
				Motor.B.rotate(50);
				Motor.C.rotate(50);
			}
			
			if(Button.RIGHT.isDown())
			{
				Motor.B.forward();
				Motor.C.forward();
			}
			if(Button.LEFT.isDown())
			{
				System.out.println("B: tacho: " + Motor.B.getTachoCount() + " position: " + Motor.B.getPosition());
				System.out.println("C: tacho: " + Motor.C.getTachoCount() + " position: " + Motor.C.getPosition());
				Thread.sleep(400);
			}
			if(Button.ESCAPE.isDown() )
			{
				Motor.B.resetTachoCount();
				Motor.C.resetTachoCount();
			}
		}
//		Button.waitForAnyPress();
	}
}