package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployerDashboardController {

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
	
	public void Profile() {
		
	}
	
	public void ViewBlockchain() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewBlockchain.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void CheckEmployeeHistory() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CandidateHistory.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void CheckPendingRequest() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PendingRequests.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void UpdateEmployeeDetails() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateBlock.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
