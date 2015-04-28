package numberGame;

public enum States {
	
	UNKNOWN 	('?'), 
	EMPTY 		('-'), 
	SHIP		('O'), 
	HIT			('X');
	
	States( char t )
	{
		this.t = t;
	}
	
	private final char t;
	public char getChar() {return t;}
	
	
}
