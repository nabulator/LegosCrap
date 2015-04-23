package numberGame;

import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Battleship 
{
	private int[][] myBoard;
	private int[][] oppBoard;
	
	public final int DIST_NAME_TO_BOARD= 2;
	public final int DIST_OPP_TO_MYBOARD = 7;
	public void init()
	{
		//int 0 is unknown, int 1 is empty, int 2 is ship
		oppBoard = new int[4][4];
		
		//int 0 is empty spot, int 1 is ship, int 2 is broken ship
		myBoard = new int[4][4];
		int count = 0;
		while(count < 4)
		{
			int col = (int)(Math.random() * 4);
			int row = (int)(Math.random() * 4);
			
			if(myBoard[col][row] == 0)
			{
				myBoard[col][row] = 1;
				count++;
			}
		}
	}
	
	public void myTurn()
	{
		int x = 0;
		int y = 0;
		while(Button.ENTER.isUp())
		{
			this.draw();
			LCD.drawChar('/', x + DIST_OPP_TO_MYBOARD, y + DIST_NAME_TO_BOARD);

			if(Button.RIGHT.isDown())
			{
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
			
			LCD.drawInt(x, 15, 9);
			LCD.drawInt(y, 17, 9);
		}
	}
	
	public void shoot(int x, int y)
	{
		
	}
	
	public boolean recieveHit(int x, int y)
	{
		int object = myBoard[x][y];
		if(object == 0)
			return false;
		else
		{
			myBoard[x][y] = 2;
			return true;
		}
	}
	
	public void draw()
	{	
		//Draw my board
		LCD.drawString("Yours", 0, 0);
		for(int col = 0; col < 4; col++)
			for(int row = 0; row < 4; row++)
			{
				int spot = myBoard[col][row];
				char object = ' ';

				if(spot == 0)
					object = '-';
				else if(spot == 1)
					object = 'O';
				else if(spot == 2)
					object = 'X';
				
				LCD.drawChar(object, col, row + DIST_NAME_TO_BOARD);

			}
		
		//Draw line dividing boards
		for(int row = 0; row < 8; row++)
			LCD.drawChar('|', 5, row);
		
		//Draw opponents board
		LCD.drawString("Opponents", DIST_OPP_TO_MYBOARD, 0);

		for(int col = 0; col < 4; col++)
			for(int row = 0; row < 4; row++)
			{
				int spot = oppBoard[col][row];
				char object = ' ';
				if(spot == 0)
					object = '?';
				else if(spot == 1)
					object = '-';
				else if(spot == 2)
					object = 'X';
				LCD.drawChar(object, col + DIST_OPP_TO_MYBOARD, row + DIST_NAME_TO_BOARD);

			}
	}
}
