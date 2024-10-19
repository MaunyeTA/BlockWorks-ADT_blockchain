package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Client;
import acsse.csc03a3.blockchainInfo.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
		
	@FXML
	private Button loginButton;
	
	public void Login(ActionEvent event) {
		Connection.client = new Client("localhost", 3000);
		try {
			LoginController login = new LoginController();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
			//fxmlLoader.setController(login);
            Parent root = fxmlLoader.load();
            Scene scene = loginButton.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void Register(ActionEvent event) {
		Connection.client = new Client("localhost", 3000);
		try {
			RegisterController register = new RegisterController();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
			//fxmlLoader.setController(register);
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
