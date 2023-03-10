package view;

import controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Login;

public class Home extends Application {

	public Login user;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		//AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("Home.fxml"));
		AnchorPane anchorPane = loader.load();
		
		Scene scene = new Scene(anchorPane);	
		
		HomeController controller = loader.getController();
		controller.SetLogon(user);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Home");
		primaryStage.show();
		
	}

}
 