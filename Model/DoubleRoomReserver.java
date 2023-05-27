package Model;

public class DoubleRoomReserver extends Reserver{
	public Room reserve(int id,String sdate,String edate){
		r =DoubleRoomFlyWeight.isAvailable(id, sdate,edate);
		return r;
	}
}
