package Model;

public class Uber extends Taxi{

	public Uber(TypeRide t) {
		super(t);
	}

	public String toString() {
		String s;
		s = "Thanks for booking a Taxi from Uber, have a safe ride. Enjoy!!!!";
		return s;
	}
}
