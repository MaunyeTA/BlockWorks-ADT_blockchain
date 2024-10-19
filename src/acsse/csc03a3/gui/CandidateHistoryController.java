package acsse.csc03a3.gui;

import java.io.IOException;
import java.util.List;

import acsse.csc03a3.blockchainInfo.Connection;
import acsse.csc03a3.blockchainInfo.UserBlockInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CandidateHistoryController {

	@FXML
	private TextField txtSearchPublicKey;
	
	@FXML
	private ListView<String> listView;
	
	public void Back() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployerDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txtSearchPublicKey.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void Search() {
		
		List<UserBlockInfo> blocks = Connection.client.GetEmployeeHistory(txtSearchPublicKey.getText());
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		for(int x=0; x<blocks.size(); x++) {
			items.add(blocks.get(x).toString());
		}
		
		listView.setItems(items);
		SetBlockInfoEvent();
		
	}
	
	private void SetBlockInfoEvent() {
		
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){
			
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				
				return new ListCell<String>() {
					
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						if(item != null) {
							
							setText(item);
							Text text = new Text(item);
							text.setWrappingWidth(listView.getWidth());
                            setPrefWidth(listView.getWidth());
                            setPrefHeight(60);
                            setGraphic(text);
							setOnMouseClicked(e->{
								
								if(e.getClickCount() == 2) {
									
									String strBlock = getItem();
									if(strBlock != null) {

										try {
											FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BlockInfo.fxml"));
											Parent root = fxmlLoader.load();
											BlockInfoController blockInfoController = fxmlLoader.getController();
											blockInfoController.setSrtBack("CandidateHistory.fxml");
											blockInfoController.setBlock(strBlock);
											blockInfoController.Display();
								            Stage stage = (Stage) txtSearchPublicKey.getScene().getWindow();
								            stage.setScene(new Scene(root));
								        } catch (IOException ex) {
								            ex.printStackTrace();
								        }
										
									}
									
								}	
								
							});
							
						}
						
					}
					
				};
				
			}
			
		});
		
	}

}
