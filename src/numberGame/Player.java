package numberGame;

import java.awt.Point;

import lejos.nxt.Button;
import lejos.nxt.LCD;

/**
 * An interface for each individual player
 * 
 * @author francis
 */
public class Player 
{
	private States[][] myBoard;
	private States[][] oppBoard;
	
	public final int DIST_NAME_TO_BOARD= 2;
	public final int DIST_OPP_TO_MYBOARD = 7;
	
	/**
	 * Initializes the tables
	 */
	public void init()
	{
		//int 0 is unknown, int 1 is empty, int 2 is ship
		oppBoard = new States[4][4];
		for(int r=0; r<4; r++ ) 
			for( int c=0; c<4; c++) 
				oppBoard[c][r] = States.UNKNOWN;
		
		LCD.drawString("ASFDF", 1, 1);
		
		//int 0 is empty spot, int 1 is ship, int 2 is broken ship
		myBoard = new States[4][4];
		int count = 0;
		for(int r=0; r<4; r++ ) 
			for( int c=0; c<4; c++) 
				myBoard[c][r] = States.EMPTY;
		while(count < 4)
		{
			int col = (int)(Math.random() * 4);
			int row = (int)(Math.random() * 4);
			
			if(myBoard[col][row] == States.EMPTY )
			{
				myBoard[col][row] = States.SHIP;
				count++;
			}
		}
	}
	
	/**
	 * Gets point via GUI
	 */
	public Point myTurn()
	{
		int x = 0;
		int y = 0;
		while(Button.ENTER.isUp())
		{
			this.draw();
			LCD.drawChar('/', x + DIST_OPP_TO_MYBOARD, y + DIST_NAME_TO_BOARD);

			if(Button.RIGHT.isDown())
			{
				while( Button.RIGHT.isDown() ); //HACKKKKK
				
				x++;
				if(x >= 4)
				{
					x = 0; 
					y++;
					if(y >= 4)
					{
						y = 0; 
					}
				}
				
			}
			else if(Button.LEFT.isDown())
			{
				while( Button.LEFT.isDown() );
				
				x--;
				if(x < 0)
				{
					x = 3;
					y--;
					if(y < 0)
					{
						y = 3;
					}
				}
			}
		}
		
		return new Point(x, y);
	}
	
	/**
	 * Check if oppoent's board was hit
	 * @param p Poibnt
	 * @param hit
	 */
	public void updateOppBoard( Point p, boolean hit)
	{
		if( hit )
			oppBoard[p.x][p.y] = States.HIT;
		else
			oppBoard[p.x][p.y] = States.EMPTY;
			
	}
	
	/**
	 * Checks if own board was hit
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean recieveHit(int x, int y)
	{
		States p = myBoard[x][y];
		if(p == States.EMPTY)
			return false;
		else
		{
			myBoard[x][y] = States.HIT;
			return true;
		}
	}
	
	public void draw()
	{	
		//Draw my board
		LCD.drawString("Yours", 0, 0);
		for(int col = 0; col < 4; col++)
			for(int row = 0; row < 4; row++)
				LCD.drawChar(myBoard[col][row].getChar(), col, row + DIST_NAME_TO_BOARD);
		
		//Draw line dividing boards
		for(int row = 0; row < 8; row++)
			LCD.drawChar('|', 5, row);
		
		//Draw opponents board
		LCD.drawString("Opponents", DIST_OPP_TO_MYBOARD, 0);

		for(int col = 0; col < 4; col++)
			for(int row = 0; row < 4; row++)
				LCD.drawChar( oppBoard[col][row].getChar() , col + DIST_OPP_TO_MYBOARD, row + DIST_NAME_TO_BOARD);
	}
	
}
