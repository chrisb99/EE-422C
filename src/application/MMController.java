package application;

import java.awt.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MMController {
	@FXML
	private Canvas mmCanvas;
	private Double gcWdth;
	private Double gcHght;

	@FXML
	void checkButton(ActionEvent event) {
		// When the check button is pressed fire the code to check the player's
		// guess.
	}

	@FXML
	void playBluePeg(ActionEvent event) {

	}

	@FXML
	void playGreenPeg(ActionEvent event) {

	}

	@FXML
	void playOrangePeg(ActionEvent event) {

	}

	@FXML
	void playPurplePeg(ActionEvent event) {

	}

	@FXML
	void playRedPeg(ActionEvent event) {

	}

	@FXML
	void playYellowPeg(ActionEvent event) {

	}
	void drawPeg(GamePeg piece){
		//Will get a GamePeg piece and depending on weather it's BW or Colored will draw the peg in the work
	}
	
	

	void drawBoard() {
		GraphicsContext gc = mmCanvas.getGraphicsContext2D();
		gcWdth = mmCanvas.getWidth();
		gcHght = (mmCanvas.getHeight());
		gc.setFill(javafx.scene.paint.Color.AQUA);
		double rHght=gcHght/13;
		gc.setStroke(javafx.scene.paint.Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine((gcWdth * .8), 0, (gcWdth * .8), gcHght);
		gc.strokeLine((gcWdth * .9), 0, (gcWdth * .9), gcHght);		
		gc.strokeLine((gcWdth), 0, (gcWdth), gcHght);
		gc.setLineWidth(3);
		gc.strokeLine(0, gcHght, gcWdth, gcHght);
		gc.strokeLine(0, gcHght, gcWdth, gcHght);
		gc.strokeLine(0, 0, 0, gcHght);
		gc.strokeLine(0, 0, gcWdth, 0);
		//Draw the Status
		gc.setLineWidth(1);
		for (int i = 0; i < 12; i++) {
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			gc.strokeOval(gcWdth*.825,(i*rHght+(rHght/6)), 20,20 );
			gc.strokeOval(gcWdth*.825,(i*rHght+(5*rHght/8)), 20,20 );
			gc.strokeOval(gcWdth*.925,(i*rHght+(rHght/6)), 20,20 );
			gc.strokeOval(gcWdth*.925,(i*rHght+(5*rHght/8)), 20,20 );
		}
		for (int i = 0; i < 13; i++) {
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			gc.strokeOval(gcWdth*.1,(i*rHght+(rHght/4)), 40,40 );
			gc.strokeOval(gcWdth*.3,(i*rHght+(rHght/4)), 40,40 );
			gc.strokeOval(gcWdth*.5,(i*rHght+(rHght/4)), 40,40 );
			gc.strokeOval(gcWdth*.7,(i*rHght+(rHght/4)), 40,40 );
		}
		
		
		
		
	}

}
