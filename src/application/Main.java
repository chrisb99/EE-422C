package application;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage){ 
	URL location = getClass().getResource("MasterMindGUI.fxml");
	FXMLLoader loader = new FXMLLoader(location);
	MMController mmCont= new MMController();
	loader.setController(mmCont);
	try {
		//MasterMind.startGame(mmCont);
		Parent root = loader.load();
		Scene scene = new Scene(root, 600, 900);
		stage.setTitle("MasterMind");
		stage.setScene(scene);
		stage.show();
		//MasterMind game = new MasterMind();
		MasterMind.startGame(mmCont);

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	public static void main(String[] args) {
		launch(args);
	}
}
