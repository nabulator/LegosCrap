package mineMapping;

import lejos.nxt.Sound;

public class Music implements Runnable
{
	
	private static int D = 1175, Bf = 932, C = 1046, Q = 440, T = 147;
	
	private static int[] noteData = {D, D, D, D, Bf, C, D, C, D};
	private static int[] length = 	{T, T, T, Q, Q, Q, T*2, T, Q*2 };

	public void run() 
	{
		// TODO Auto-generated method stub
		for( int i=0 ; i<noteData.length ; i++ )
		{
			Sound.playNote(Sound.PIANO, noteData[i], length[i]);
			
			try {
				Thread.sleep( 10 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
