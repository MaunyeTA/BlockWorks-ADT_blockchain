package acsse.csc03a3.gui;

import java.io.IOException;

import acsse.csc03a3.blockchainInfo.UserBlockInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlockInfoController {
	
	private String srtBack = "";
	private String strlock = "";
	private UserBlockInfo block = null;
	
	@FXML
	private TextField txtHash;
	@FXML
	private TextField txtEndDate;
	@FXML
	private TextField txtStartDate;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtPosition;
	@FXML
	private TextField txtSender;
	@FXML
	private TextField txtReciever;
	@FXML
	private TextField txtPHash;
	@FXML
	private TextArea txtComment;
	@FXML
	private Text txtAlert;

	public void Back(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(srtBack));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtHash.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void CopyHash() {
		if(!strlock.equals("")) {
			
			Clipboard clip = Clipboard.getSystemClipboard();
			ClipboardContent content = new ClipboardContent();
			
			if(block != null) {
				content.putString(block.getHash());
				clip.setContent(content);
				txtAlert.setText("Hash copied");
			}
			
		}
	}
	
	public void Display() {
		
		block = new UserBlockInfo("buffer\n"+strlock);
		
		txtHash.setText(block.getHash());
		txtHash.setStyle("-fx-text-fill: #292929");
		txtPHash.setText(block.getPreviousHash());
		txtPHash.setStyle("-fx-text-fill: #292929");
		txtName.setText(block.getTransaction().getData().getName());
		txtName.setStyle("-fx-text-fill: #292929");
		txtSurname.setText(block.getTransaction().getData().getSurname());
		txtSurname.setStyle("-fx-text-fill: #292929");
		txtPosition.setText(block.getTransaction().getData().getPosition());
		txtPosition.setStyle("-fx-text-fill: #292929");
		txtSender.setText(block.getTransaction().getSender());
		txtSender.setStyle("-fx-text-fill: #292929");
		txtReciever.setText(block.getTransaction().getReceiver());
		txtReciever.setStyle("-fx-text-fill: #292929");
		txtStartDate.setText(block.getTransaction().getData().getStartDate());
		txtStartDate.setStyle("-fx-text-fill: #292929");
		txtEndDate.setText(block.getTransaction().getData().getEndDate());
		txtEndDate.setStyle("-fx-text-fill: #292929");
		txtComment.setText(block.getTransaction().getData().getComment());
		txtComment.setStyle("-fx-text-fill: #292929");
	}
	
	/**
	 * @param srtBack the srtBack to set
	 */
	public void setSrtBack(String srtBack) {
		this.srtBack = srtBack;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.strlock = block;
	}
	
}
