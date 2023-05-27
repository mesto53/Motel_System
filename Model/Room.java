package Model;
import java.util.ArrayList;

public abstract class Room {
	public int id,price;
	ArrayList<String> reservedDates;
	public Room(int id,String sDate,String eDate) {
		this.id = id;
		reservedDates = new ArrayList<String>();
		this.reservedDates.add(sDate + "_"+eDate);
	}
	
	public void AddDate(String Date) {
		this.reservedDates.add(Date);
	}
	
	public boolean isReserved(String sdate,String edate) {
		
		for(String d:reservedDates) {
			String[] dext = d.split("_");
			if(isBetween(dext[0],sdate,dext[1]) || isBetween(dext[0],edate,dext[1]))
				return true;
		}
		return false;
	}
	public abstract String toString();
	
	
	public boolean isBetween(String d1,String d2,String d3) {
		String []a1 = d1.split("/");
		String []a2 = d2.split("/");
		String []a3 = d3.split("/");
		int []a = new int[3];
		int []b = new int[3];
		int []c = new int[3];
		for(int i = 0;i<a1.length;i++) {
			a[i] = Integer.parseInt(a1[i]);
			b[i] = Integer.parseInt(a2[i]);
			c[i] = Integer.parseInt(a3[i]);
		}
		if((b[2] == c[2]) && (b[2] == a[2])) {
			long x1 = a[1] * 30;
			x1 += a[0];
			long x2 = b[1] * 30;
			x2 += b[0];
			long x3 = c[1] * 30;
			x3 += c[0];
			if(x2 <= x3 && x2>= x1) {
				return true;
			}
			else return false;
		}
		return false;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}