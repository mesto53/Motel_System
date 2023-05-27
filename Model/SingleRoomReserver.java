package Model;


public class SingleRoomReserver extends Reserver{
	
	public Room reserve(int id,String sdate,String edate){
		r = SingleRoomFlyWeight.isAvailable(id, sdate,edate);
		return r;
	}
}