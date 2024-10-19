package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Connection;
import acsse.csc03a3.blockchainInfo.MyTransaction;
import acsse.csc03a3.blockchainInfo.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewRequestController {
	
	@FXML
	private TextField txtPublicKey;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtPosition;
	@FXML
	private Text txtAlert;
	
	public void Back() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtPublicKey.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void AddName() {
		txtName.setText(Connection.client.GetName());
	}
	
	public void Send() {
		UserInfo ui = new UserInfo();
		ui.setName(txtName.getText());
		ui.setSurname(txtSurname.getText());
		ui.setPosition(txtPosition.getText());
		ui.setEmployerID(txtPublicKey.getText());
		ui.setUserID(Connection.client.GetPublicKey());
		MyTransaction tran = new MyTransaction(ui.getUserID(), ui.getEmployerID(), ui);
		txtAlert.setStyle("-fx-fill: #2F9E00;");
		txtAlert.setText(Connection.client.RequestConfirmation(tran.toString()));
	}
}
