package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReserveController implements Initializable{
	
	
	@FXML
	Button reserveRoom;
	@FXML
	TextField name;
	@FXML
	TextField email;
	@FXML
	TextField roomID;
	@FXML
	TextField sDate;
	@FXML
	TextField eDate;
	@FXML
	Label isRegistered;

	@FXML
	public void modelReserve() { 
		
		
		String client_name = name.getText().toString();
		String client_email = email.getText().toString();
		int rid = Integer.parseInt(roomID.getText().toString());
		String start = sDate.getText().toString();
		String end = eDate.getText().toString();
		Reserver r;
		Client client = new Client(client_name,client_email);
		
		DBConnect db = DBConnect.getDBCon();
		//room from model:
		if(rid < 200 ) {
			r = new SingleRoomReserver();
		}
		else if(rid >= 200 && rid<300){
			r = new DoubleRoomReserver();
		}
		else {
			isRegistered.setText("please enter valid room number");
			return;
		}
		client.setReserver(r);
		try {
			db.connection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection con = db.getConnection();
		Statement st = db.getStatement();
	   boolean check = db.IsAlreadyRegistered(client_name,client_email);
		
		
		
		
		try {
			ResultSet res = st.executeQuery("Select * from Reservations");
			//if there are reservations:
			if(res.next()) {
				res.beforeFirst();
				//if the room has reservations we fill this resevations up
				while(res.next()) {
					int IDR = Integer.parseInt(res.getString(2));
					if(rid == IDR)
						r.reserve(rid, res.getString(3), res.getString(4));
				}
				res.close();
				
			    Room room = client.getRoom(rid, start, end);
				//check if the booking by client is possible
				if(room == null) {
					isRegistered.setText("sorry the room is already reserved in the specified day");
					return;
				}
				
				if(check) {
					int cid = db.getClientId(client_name,client_email);
					System.out.println(cid);
					try {
						st.executeUpdate("insert into Reservations values ("+cid+","+rid+",'"+start+"','"+end+"');");
					}catch(SQLException e2) {
						isRegistered.setText("Fail, you already booked this room!!!");
						return;
					}
					db.updateFees(client.getLastFee(), client_name, client_email);
					isRegistered.setText("success booking with existed account");
					
				}
				else {
					//create a new account to a client
					st.executeUpdate("insert into Client values ('"+client_name+"','"+client_email+"','0');");
					isRegistered.setText("Booking was successfull and you have a new account :)");
					int cid = db.getClientId(client_name,client_email);
					st.executeUpdate("insert into Reservations values ("+cid+","+rid+",'"+start+"','"+end+"');");
					st.executeUpdate("update Client set fees = "+client.getLastFee()+" where cid = "+cid+";");
					isRegistered.setText("success with new account");
				}
				reserveRoom.setDisable(true);
				return;
			}
			
			//there are no reservations.
			else {
				Room room =  client.getRoom(rid, start, end);
				if(check) {
					isRegistered.setText("Booking was successfull you are already registered!!");
					int cid = db.getClientId(client_name,client_email);
					st.executeUpdate("insert into Reservations values ("+cid+","+rid+",'"+start+"','"+end+"');");
					db.updateFees(client.getLastFee(), client_name, client_email);
					//isRegistered.setText("success with existed account");
				}
				else {
					//create a new account to a client 
					st.executeUpdate("insert into Client values ('"+client_name+"','"+client_email+"',"+client.getLastFee()+")");
					isRegistered.setText("Booking was successfull and you have a new account :)");
					int cid = db.getClientId(client_name,client_email);
					st.executeUpdate("insert into Reservations values ("+cid+","+rid+",'"+start+"','"+end+"');");
					isRegistered.setText("success with new account");
				}
				reserveRoom.setDisable(true);
				return;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showReserveScreen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserveRoom.fxml"));
		try {
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("book now");
			stage.setScene(new Scene(root));
			stage.show();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
