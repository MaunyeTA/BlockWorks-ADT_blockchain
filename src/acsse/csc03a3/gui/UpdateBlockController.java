package acsse.csc03a3.gui;

import java.io.IOException;
import java.util.List;

import acsse.csc03a3.blockchainInfo.Connection;
import acsse.csc03a3.blockchainInfo.MyTransaction;
import acsse.csc03a3.blockchainInfo.UserBlockInfo;
import acsse.csc03a3.blockchainInfo.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateBlockController {
	
	@FXML
	private TextField txtHash;
	@FXML
	private TextField txtPublicKey;
	@FXML
	private TextField txtPosition;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtStartDate;
	@FXML
	private TextField txtEndDate;
	@FXML
	private TextArea txtComment;
	@FXML
	private Text txtAlert;
	
	private UserBlockInfo block;
	
	public void Back() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployerDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtPublicKey.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void GetInfo() {
		
		block = Connection.client.GetBlockByHash(txtHash.getText());
		if(block != null) {
			txtPublicKey.setText(block.getTransaction().getSender());
			txtPosition.setText(block.getTransaction().getData().getPosition());
			txtName.setText(block.getTransaction().getData().getName());
			txtSurname.setText(block.getTransaction().getData().getSurname());
			txtStartDate.setText(block.getTransaction().getData().getStartDate());
			txtEndDate.setText(block.getTransaction().getData().getEndDate());
			txtComment.setText(block.getTransaction().getData().getComment());
			
			txtPosition.setDisable(false);
			txtEndDate.setDisable(false);
			txtComment.setDisable(false);
		}else {
			txtAlert.setText("That's not your block");
		}
		
		
	}
	
	public void Save() {
		
		MyTransaction transaction = block.getTransaction();
		UserInfo ui = transaction.getData();
		
		if(txtPosition.getText().isBlank()) {
			ui.setStartDate("-");
		}else {
			ui.setPosition(txtPosition.getText());
		}
		
		if(txtEndDate.getText().isBlank()) {
			ui.setEndDate("-");
		}else {
			ui.setEndDate(txtEndDate.getText());
		}
		
		if(txtComment.getText().isBlank()) {
			ui.setComment("-");
		}else {
			String parts[] = txtComment.getText().split("\n");
			String comment = "";
			for(int x=0; x<parts.length; x++) {
				comment += parts[x];
			}
			ui.setComment(comment);
		}
		
		transaction.setData(ui);
		String sender = transaction.getSender();
		transaction.setSender(transaction.getReceiver());
		transaction.setReceiver(sender);
		
		Connection.client.AddBlock(transaction);
				
		txtAlert.setText("Block added");
		
		CleanInputs();
		
	}
	
	private void CleanInputs() {
		txtHash.setText("");
		txtPosition.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtStartDate.setText("");
		txtEndDate.setText("");
		txtComment.setText("");
		txtPublicKey.setText("");
		
		txtPosition.setDisable(true);
		txtEndDate.setDisable(true);
		txtComment.setDisable(true);
	}
	
}
