package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class MMController {
	@FXML
	private Canvas mmCanvas;
	private Double gcWdth;
	private Double gcHght;


	@FXML
	void checkButton(ActionEvent event) {
		// When the check button is pressed fire the code to check the player's
		// guess.
		int bCount = 0;
		if (MasterMind.getPegLoc() < 4) {
			// display error.
		} else {
			ArrayList<BwPeg> fb = MasterMind.getFeedBack();
			for (BwPeg e: fb) {
				if (!e.getColorMethod().equals(Color.BLACK)) {
					bCount+=1;
				}
			}
			if(bCount==4){
				//You WIN!
			}
			else{
				//You loose
			}
			
		}

	}

	@FXML
	void playBluePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.BLUE));
		this.drawBoard();
	}

	@FXML
	void playGreenPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.GREEN)); 
	}

	@FXML
	void playOrangePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.ORANGE)); 
	}

	@FXML
	void playPurplePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.PURPLE)); 
	}

	@FXML
	void playRedPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.RED)); 
	}

	@FXML
	void playYellowPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.YELLOW)); 
	}
	void displayBoard(){
		
	}
	
	
	
	
	
	

	void drawPeg(GamePeg piece) {
		// Will get a GamePeg piece and depending on weather it's BW or Colored
		// will draw the peg in the work
	}

	void drawBoard() {
		GraphicsContext gc = mmCanvas.getGraphicsContext2D();
		gcWdth = mmCanvas.getWidth();
		gcHght = (mmCanvas.getHeight());
		gc.setFill(javafx.scene.paint.Color.AQUA);
		double rHght = gcHght / 13;
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
		// Draw the Status
		gc.setLineWidth(1);
		for (int i = 1; i < 13; i++) {
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			gc.strokeOval(gcWdth * .825, (i * rHght + (rHght / 6)), 20, 20);
			gc.strokeOval(gcWdth * .825, (i * rHght + (5 * rHght / 8)), 20, 20);
			gc.strokeOval(gcWdth * .925, (i * rHght + (rHght / 6)), 20, 20);
			gc.strokeOval(gcWdth * .925, (i * rHght + (5 * rHght / 8)), 20, 20);
		}
		ColorPeg[][] slots = MasterMind.getSlots();
		for (int i = 0; i < 13; i++) {
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			if(slots[i][0]==null){
				gc.strokeOval(gcWdth * .1, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][3].getColorMethod());
				gc.fillOval(gcWdth * .1, (i * rHght + (rHght / 4)), 40, 40);
			}
			if(slots[i][1]==null){
				gc.strokeOval(gcWdth * .3, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][3].getColorMethod());
				gc.fillOval(gcWdth * .3, (i * rHght + (rHght / 4)), 40, 40);
			}
			if(slots[i][2]==null){
				gc.strokeOval(gcWdth * .5, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][3].getColorMethod());
				gc.fillOval(gcWdth * .5, (i * rHght + (rHght / 4)), 40, 40);
			}
			if(slots[i][3]==null){
				gc.strokeOval(gcWdth * .7, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][3].getColorMethod());
				gc.fillOval(gcWdth * .7, (i * rHght + (rHght / 4)), 40, 40);
			}
		}


	}

}
