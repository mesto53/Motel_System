package Model;

public abstract class Taxi extends Service{

	public TypeRide type;

    public Taxi(TypeRide t) {
	         type = t;
	         this.price = t.price;
    }

    //getting the price of the service:
    public int getRidePrice() {
    	price = type.getRidePrice();
        return price;
   }

     public abstract String toString();
}
