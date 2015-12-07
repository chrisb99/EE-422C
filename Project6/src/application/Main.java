/* CRITTERS <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * <Hasun Amarasekara>
 * <hua59>
 * <16345>
 * <Tianyi Bi>
 * <tb25947>
 * <16345>
 * Slip days used: <1>
 * Fall 2015
 */
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
	public void start(Stage stage) {
		URL location = getClass().getResource("MasterMindGUI.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		MMController mastermindController = new MMController();
		loader.setController(mastermindController);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root, 600, 900);
			stage.setTitle("MasterMind");
			stage.setScene(scene);
			stage.show();
			mastermindController.launchMastermind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
