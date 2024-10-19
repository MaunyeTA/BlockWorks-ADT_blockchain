package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeDashboardController {

	@FXML
	private Button btnLogout;
	
	public void Logout() {
		Connection.client.Logout();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void SeeHistory() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("History.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void NewRequest() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewRequest.fxml"));
            Parent root = fxmlLoader.load();
            NewRequestController req = fxmlLoader.getController();
			req.AddName();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
