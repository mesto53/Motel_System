package Model;

public class Luxury extends TypeRide{
    int d;
	
	public Luxury(int distance) {
		this.d = distance;
		int r = d*10;
		this.price = r;
	}

	public int getRidePrice(){
		int r = d*10;
		this.price = r;
		return price;
	}
}
