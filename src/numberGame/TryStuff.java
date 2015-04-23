package numberGame;

import lejos.nxt.*;
import lejos.robotics.Color;

public class TryStuff 
{
	public static void main(String[] args)
	{
		Battleship test = new Battleship();
		test.init();
		test.myTurn();
		
		Button.waitForAnyPress();
	}
}
