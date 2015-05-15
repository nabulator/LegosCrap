package battleShip;

import java.io.File;
import java.util.ArrayList;

import lejos.nxt.*;
import lejos.robotics.Color;
import mineMapping.Music;
import mineMapping.SomethingRel;

/**
 * Completely useless GUI tester
 * @author 60129
 *
 */
public class Tester 
{
	public static void main(String[] args) throws InterruptedException
	{
		testArrayList();
		testMusic();
	}
	
	private static ArrayList<String> data;
	
	private static void printData(int pointer)
	{
		for(int i = 0; i < 7; i++)
			System.out.println("Go: " + data.get((i + pointer)*2) + 
				" turn:" + data.get((i + pointer)*2 + 1));
	}
	
	private static void testMusic()
	{
		Thread d = new Thread(new Music());
		d.start();
		Button.waitForAnyPress();
	}
	
	private static void testArrayList()
	{
		data = new ArrayList<String>();

		for(int i = 0; i < 10; i++)
		{
			data.add(String.valueOf(i));
			
			data.add("u");
		}
		
		int pointer = 0;
		
		printData(pointer);
		
		while(Button.ENTER.isUp())
		{

			
			if(Button.RIGHT.isDown())
			{
				while( Button.RIGHT.isDown() ); //HACKKKKK
				
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
	
	private static void testGraph()
	{
		char[] dirs = { 'r', 'u', 'l', 'd' };
		ArrayList<String> d = new ArrayList<String>();
		for( int i=0 ; i<10 ; i++ )
		{
			d.add( String.valueOf((int)(Math.random() * 360) )); //adds distance (taco count)
			d.add( String.valueOf(( dirs[ (int)(Math.random()*4) ] ) ));
		}
		
//		SomethingRel.graph(d);
	}
	
	private static void testTacos() throws InterruptedException
	{
		//Player test = new Player();
			//test.init();
				//test.myTurn();
//				System.out.println(Sound.playSample(new File("FF9.wav")));
				
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
//				Button.waitForAnyPress();
	}
	
//	public static void testWobbling()
//	{
//		int dist = uss.getDistance();
//		
//		//for testing purposes
//		rightWheel.forward();
//		leftWheel.forward();
//
//		if(dist > MIN_DIS_FROM_WALL + 1)
//		{
//			leftWheel.setSpeed( leftWheel.getSpeed()  * 0.5f );			
//		}
//		else if(dist < MIN_DIS_FROM_WALL - 1 && dist > MIN_DIS_FROM_WALL - WALL_ADJ) // 3 9
//		{
//			rightWheel.setSpeed( rightWheel.getSpeed() * 0.5f );
//		}
//		else
//		{
//			rightWheel.forward();
//			leftWheel.forward();
//		}
//	}
}