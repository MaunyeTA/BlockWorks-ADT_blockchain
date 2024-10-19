package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtPublicKey;
	
	@FXML
	private PasswordField txtPrivateKey;
	
	@FXML
	private Text txtAlert;
	
	@FXML
	private CheckBox txtCheck;
	
	public void Register() {
		
		String response = "";
		
		if(txtCheck.isSelected()) {
			response = Connection.client.Register(txtID.getText(), txtPublicKey.getText(), txtPrivateKey.getText(), "true");
		}else {
			response = Connection.client.Register(txtID.getText(), txtPublicKey.getText(), txtPrivateKey.getText(), "false");
		}
		
		if(response.equals("Registered successfully")) {
			txtAlert.setStyle("-fx-fill: #3DCB00");
			txtAlert.setText(response);
		}else {
			txtAlert.setStyle("-fx-fill: RED");
			txtAlert.setText(response);
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
