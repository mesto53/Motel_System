package Model;

public class Cab extends TypeRide{


	int d;
	
	public Cab(int distance) {
		this.d = distance;
		int r = d*2;
		this.price = r;
	}

	public int getRidePrice(){
		int r = d*2;
		this.price = r;
		return price;
	}
}
