package Model;

public class DoubleRoom extends Room{
	public DoubleRoom(int id,String sDate,String eDate) {
		super(id,sDate,eDate);
		this.price = 350;
	}
	
	public String toString() {
		String date = this.reservedDates.get(reservedDates.size() - 1);
		return "DoubleRoom of number:"+this.id +" in: " + date;
	}
}
