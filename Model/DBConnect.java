package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {
	final String url="jdbc:sqlserver://laptop-o36treut;databaseName=GUIdb;encrypt=true;trustServerCertificate=true;";
	final String user = "sa";
	final String password = "sqlRabih";
	Connection con = null;
	Statement stm = null;
	
	
	private static DBConnect db = null;
	private DBConnect() {
	}
	public static DBConnect getDBCon() {
		if(db == null)
			db = new DBConnect();
		return db;
	}
	
	
	//connection
	public void connection() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
		stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}
	
	//close connection
	public void closeConnection() throws SQLException {
		stm.close();
		con.close();
	}
	
	public Statement getStatement() {
		return stm;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public boolean IsAlreadyRegistered(String Client_name,String email) {
		try {
			ResultSet rs = stm.executeQuery("select cname ,email from Client");
			while(rs.next()) {
				if(Client_name.equals(rs.getString(1)) && email.equals(rs.getString(2))) {
					rs.close();
					return true;
				}
			}
			rs.close();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	public int getClientId(String client_name,String email) {
		int id = 0;
		try {
			ResultSet rs = stm.executeQuery("select cid from Client where cname = "+"'"+client_name+"'"+"and email = '"+email+"'");
			rs.next();
			id = Integer.parseInt(rs.getString(1));
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public ResultSet getReservations(String name,String email){
		int cid = this.getClientId(name, email);
		try {
			ResultSet res = stm.executeQuery("select roomid,sDate,eDate from Reservations where cid = "+cid);
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateFees(int fees,String name,String email) throws SQLException {
		int cid = this.getClientId(name, email);
		stm.executeUpdate("update Client set fees = fees + "+fees+"where cid = "+cid);
	}
	
	
	
	
	public void reduceFees(int amount,String name,String email) throws SQLException {
		int cid = this.getClientId(name, email);
		stm.executeUpdate("update Client set fees = fees - "+amount+"where cid = "+cid);
	}
}
