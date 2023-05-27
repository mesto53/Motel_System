package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class AccountController implements Initializable{

	String name = null;
	String email = null;
	Client client;
	
	@FXML
	Button italian;
	@FXML
	Button british;
	@FXML
	Button allocompany;
	@FXML
	Button ubercompany;
	
	@FXML
	Label fees;
	
	@FXML
	ToggleGroup ride;
	@FXML
	RadioButton luxury;
	@FXML
	RadioButton cab;
	
	@FXML
	ListView<String> list;
	
	@FXML
	MenuItem viewReserve;
	@FXML
	MenuItem totalfees;
	@FXML
	MenuItem credit;
	@FXML
	MenuItem debit;
	
	@FXML
	public void showFees() throws SQLException {
		int bill = client.getBill();
		fees.setText("Your current bill is: "+bill);
	}
	
	@FXML
	public void payByCredit() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		db.reduceFees(client.getBill(), name, email);
		client.setTotalFees(0);
		
	}
	
	@FXML
	public void payByDebit() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		int x = client.getBill();
		db.reduceFees(x * 25/100, name, email);
		client.setTotalFees(client.getBill());
		
	}
	
	@FXML
	public void displayReserve() throws NumberFormatException, SQLException {
		DBConnect db = DBConnect.getDBCon();
		ArrayList<String> reservations = new ArrayList<String>();
		try {
			db.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.getConnection();
		db.getStatement();
		ResultSet infos = db.getReservations(client.getName(), client.getEmail());
		while(infos.next()) {
			if(Integer.parseInt(infos.getString(1)) < 200) {
				reservations.add("SingleRoom of number:"+infos.getString(1)+" reserved in: "+infos.getString(2)+" till: "+infos.getString(3));
			}
			else {
				reservations.add("DoubleRoom of number:"+infos.getString(1)+" reserved in: "+infos.getString(2)+" till: "+infos.getString(3));
			}
		}
		infos.close();
		db.closeConnection();
		list.getItems().addAll(reservations);
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	
	@FXML
	public void Italian() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		client.orderService(FoodFactory.createKitchen("Italian"));
		db.updateFees(client.getLastFee(), name, email);
		italian.setDisable(true);
	}
	
	@FXML
	public void British() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		client.orderService(FoodFactory.createKitchen("British"));
		db.updateFees(client.getLastFee(), name, email);
		british.setDisable(true);
	}
	
	@FXML
	public void Uber() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		if(luxury.isSelected()) {
			client.orderService(new Uber(new Luxury(20)));
		}
		else if(cab.isSelected())
			client.orderService(new Uber(new Cab(20)));
		else {
			fees.setText("please select the type ride");
			db.closeConnection();
			return;
		}
		db.updateFees(client.getLastFee(), name, email);
		db.closeConnection();
		ubercompany.setDisable(true);
		allocompany.setDisable(true);
	}
	
	
	@FXML
	public void Allo() throws SQLException {
		DBConnect db = DBConnect.getDBCon();
		db.connection();
		db.getConnection();
		db.getStatement();
		if(luxury.isSelected()) {
			client.orderService(new AlloTaxi(new Luxury(20)));
		}
		else if(cab.isSelected())
			client.orderService(new AlloTaxi(new Cab(20)));
		else {
			fees.setText("please select the type ride");
			db.closeConnection();
			return;
		}
		db.updateFees(client.getLastFee(), name, email);
		db.closeConnection();
		ubercompany.setDisable(true);
		allocompany.setDisable(true);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	
	public void setClient(String name,String email) {
		this.name = name;
		this.email = email;
		client = new Client(name,email);
		System.out.println(client);
	}

}
