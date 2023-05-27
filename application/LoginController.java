package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Model.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable{

   Stage stage;
	
	
	@FXML
	Button submit;
	@FXML
	TextField clientname;
	@FXML
	TextField email;
	@FXML
	Label result;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stage = new Stage();
	}
	
	@FXML
	public void submitLogin(ActionEvent ae) {
		DBConnect db = DBConnect.getDBCon();
		try {
			db.connection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = clientname.getText().toString();
		String cemail = email.getText().toString();
		Connection con = db.getConnection();
		Statement st = db.getStatement();
		try {
			if(db.IsAlreadyRegistered(name, cemail)) {
				db.closeConnection();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
				try {
					Parent root = (Parent) loader.load();
					AccountController ac = loader.getController();
					ac.setClient(name, cemail);
					stage.setTitle("Account");
					stage.setScene(new Scene(root));
					stage.show();
				}
				catch(Exception ex) {
					System.out.println("Something wrong happened");
					ex.printStackTrace();
				}
			}
			else {
				db.closeConnection();
				stage.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	public void displayWindow() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
		try {
			Parent root = (Parent) loader.load();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("sign up");
			stage.setScene(new Scene(root));
			stage.show();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
