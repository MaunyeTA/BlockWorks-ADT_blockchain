package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Client;
import acsse.csc03a3.blockchainInfo.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField txtPublicKey;
	
	@FXML
	private TextField txtPrivateKey;
	
	@FXML
	private Text txtAlert;
	
	public void LoginUser() {
		
		int response = Connection.client.Login(txtPublicKey.getText(), txtPrivateKey.getText());
		if(response == 1) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployerDashboard.fxml"));
	            Parent root = fxmlLoader.load();
	            Stage stage = (Stage) txtAlert.getScene().getWindow();
	            stage.setScene(new Scene(root));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}else if(response == 2) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeDashboard.fxml"));
	            Parent root = fxmlLoader.load();
	            Stage stage = (Stage) txtAlert.getScene().getWindow();
	            stage.setScene(new Scene(root));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}else {
			txtAlert.setText("Incorrect Password");
		}
		
	}
	
	public void Back() {
		Connection.client.Logout();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtAlert.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
