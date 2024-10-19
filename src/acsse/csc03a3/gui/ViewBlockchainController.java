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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewBlockchainController {
	
	@FXML
	private Button btnSwitch;
	
	@FXML
	private ListView<String> listsView;
	
	public void ViewALL() {
		
		List<UserBlockInfo> blocks = Connection.client.GetBlockchain();
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		for(int x=0; x<blocks.size(); x++) {
			items.add(blocks.get(x).toString());
		}
		
		listsView.setItems(items);
		
		SetBlockInfoEvent();
	}
	
	public void ViewOnlyFromMyCompany() {
		
		List<UserBlockInfo> blocks = Connection.client.GetBlockchain();
		String myPublicKey = Connection.client.GetPublicKey();
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		
		for(int x=0; x<blocks.size(); x++) {
			if(blocks.get(x).getTransaction().getReceiver().equals(myPublicKey) || blocks.get(x).getTransaction().getSender().equals(myPublicKey)) {
				items.add(blocks.get(x).toString());
			}
		}
		
		listsView.setItems(items);
		
		SetBlockInfoEvent();
	}
	
	public void Back() {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployerDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnSwitch.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	private void SetBlockInfoEvent() {
		
		listsView.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){
			
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				
				return new ListCell<String>() {
					
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						if(item != null) {
							
							setText(item);
							Text text = new Text(item);
							text.setWrappingWidth(listsView.getWidth());
                            setPrefWidth(listsView.getWidth());
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
											blockInfoController.setSrtBack("ViewBlockchain.fxml");
											blockInfoController.setBlock(strBlock);
											blockInfoController.Display();
								            Stage stage = (Stage) btnSwitch.getScene().getWindow();
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
