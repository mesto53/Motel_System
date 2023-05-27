package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SampleController implements Initializable{
	
	
	@FXML
	Button login;
	@FXML
	Button book;
	@FXML
    Label answer;
	
	@FXML
	public void Book(ActionEvent e) {
		ReserveController RC = new ReserveController();
		RC.showReserveScreen();
	}
	
	@FXML
	public void Login(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
		try {
			Parent root = (Parent) loader.load();
			LoginController lc = loader.getController();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("sign up");
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
