package Model;

public class SingleRoom extends Room{
	public SingleRoom(int id,String sDate,String eDate) {
		super(id,sDate,eDate);
		this.price = 250;
	}
	
	public String toString() {
		String date = this.reservedDates.get(reservedDates.size() - 1);
		return "SingleRoom of number:"+this.id +" in: " + date;
	}
	
	
}
