package peppers;

import lejos.nxt.*;

public class Capcasin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello mr.horn");
		Motor.B.forward();
		Motor.C.forward();
		Button.waitForAnyPress();
	}

}
