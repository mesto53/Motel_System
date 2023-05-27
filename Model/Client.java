package Model;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client extends Person{
	public Reserver reserver;
	public Room room;
	public ArrayList<String> view;
	private int TotalFees = 0,bill = 0;
	//public Service s;
	
public Client(String name,String em) {
	super(name,em);
	view = new ArrayList<String>();
}

public void setReserver(Reserver r) {
	this.reserver = r;
}

public Room getRoom(int id,String sdate,String edate) {
	room = reserver.reserve(id, sdate, edate);
	if(room != null) {
		String s = room.toString();
		this.TotalFees = room.getPrice();
		view.add(s);
	}
	return room;
}

public void viewMyReservations() {
	String s ="Mr."+this.getName()+" your reservations are:\n";
	for(String a: view) {
		s += a + "\n";
	}
	System.out.println(s);
}


//method that notifies the subscriber:
public void update(String s) {
	System.out.println(this.getName() +", notify that "+s);
}

public void orderService(Service s) {
	this.TotalFees = s.getPrice();
}

public int getBill() throws SQLException {
	DBConnect db = DBConnect.getDBCon();
	db.connection();
	db.getConnection();
	int cid = db.getClientId(getName(), getEmail());
	ResultSet res = db.getStatement().executeQuery("select fees from Client where cid = "+cid);
	res.next();
	bill = Integer.parseInt(res.getString(1));
	return bill;
}

public void setTotalFees(int totalFees) {
	TotalFees = totalFees;
}

public int getLastFee() {
	return this.TotalFees;
}

}
