package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.Connection;
import acsse.csc03a3.blockchainInfo.MyTransaction;
import acsse.csc03a3.blockchainInfo.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PendingRequestsController {

	@FXML
	private TextField txtID;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtPosition;
	@FXML
	private TextField txtStartDate;
	@FXML
	private TextField txtEndDate;
	@FXML
	private TextArea txtCommnet;
	@FXML
	private Text txtAlert;
	
	private MyTransaction request = null;
	
	public void GetRequest() {
		request = Connection.client.GetPendingRequest();
		if(request != null) {
			txtID.setText(request.getSender());
			txtID.setDisable(true);
			txtName.setText(request.getData().getName());
			txtName.setDisable(true);
			txtSurname.setText(request.getData().getSurname());
			txtSurname.setDisable(true);
			txtPosition.setText(request.getData().getPosition());
			txtPosition.setDisable(true);
		}else {
			txtAlert.setStyle("-fx-fill: RED");
			txtAlert.setText("No more request");
		}
	}
	
	public void Back() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployerDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtID.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void Approve() {
		UserInfo ui = request.getData();
		
		if(txtStartDate.getText().isBlank()) {
			ui.setStartDate("-");
		}else {
			ui.setStartDate(txtStartDate.getText());
		}
		
		if(txtEndDate.getText().isBlank()) {
			ui.setEndDate("-");
		}else {
			ui.setEndDate(txtEndDate.getText());
		}
		
		if(txtCommnet.getText().isBlank()) {
			ui.setComment("-");
		}else {
			String parts[] = txtCommnet.getText().split("\n");
			String comment = "";
			for(int x=0; x<parts.length; x++) {
				comment += parts[x];
			}
			ui.setComment(comment);
		}
		
		request.setData(ui);
		Connection.client.AddBlock(request);
		
		CleanInputs();
		
		txtAlert.setStyle("-fx-fill: #2F9E00");
		txtAlert.setText("Block added.");
	}
	
	private void CleanInputs() {
		txtID.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtPosition.setText("");
		txtStartDate.setText("");
		txtEndDate.setText("");
		txtCommnet.setText("");
	}
	
	public void Reject() {
		
		String response = Connection.client.Reject();
		txtAlert.setStyle("-fx-fill: #2F9E00");
		txtAlert.setText(response);
		CleanInputs();
	}
}
