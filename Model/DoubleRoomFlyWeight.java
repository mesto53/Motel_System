package Model;

import java.util.HashMap;
import java.util.Map;

public class DoubleRoomFlyWeight {
	private static final Map<Integer,DoubleRoom> valid = new HashMap<Integer,DoubleRoom>();
	public static DoubleRoom isAvailable(int id,String sdate,String edate) {
		DoubleRoom sr = (DoubleRoom) valid.get(id);
		if(sr == null) {
			sr = new DoubleRoom(id,sdate,edate);
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
