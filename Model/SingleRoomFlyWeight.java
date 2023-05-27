package Model;
import java.util.HashMap;
import java.util.Map;

public class SingleRoomFlyWeight {
	private static final Map<Integer,SingleRoom> valid = new HashMap<Integer,SingleRoom>();
	public static SingleRoom isAvailable(int id,String sdate,String edate) {
		SingleRoom sr = (SingleRoom) valid.get(id);
		if(sr == null) {
			sr = new SingleRoom(id,sdate,edate);
			valid.put(id, sr);
			sr.AddDate(sdate+"_"+edate);
			System.out.println("You have reserved "+sr);
			return sr;
		}
		if(sr.isReserved(sdate,edate) == false) {
			sr.AddDate(sdate+"_"+edate);
			System.out.println("You have reserved "+sr);
			return sr;
		}
		else {
			System.out.println("Sorry!! Room with id:"+id+" has already been reserved in:"+sdate+"_"+edate);
			return null;
		}
	}
}
