import acsse.csc03a3.blockchainInfo.Client;
import acsse.csc03a3.blockchainInfo.Connection;
import acsse.csc03a3.blockchainInfo.ServerNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static Client client;
	
	public static void main(String[] args) {
		ServerNode server = new ServerNode(3000);
		server.startServer();
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/acsse/csc03a3/gui/Home.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/acsse/csc03a3/gui/styles.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("BlockWordks");
			primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}