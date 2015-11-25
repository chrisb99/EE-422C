package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;


public class MMController {
	@FXML
	private Canvas mmCanvas;
	private Double gcWdth;
	private Double gcHght;


	@FXML
	void checkButton(ActionEvent event) {
		// When the check button is pressed fire the code to check the player's
		// guess
		
		int totalGuess = MasterMind.getGuess();
		int bCount = 0;
		ArrayList<BwPeg> pegStats = MasterMind.getFeedBack();
		
		//if you are already on 12, that mean you have no more moves
		//we don't have an end yet
		
		
		//need an alertbox here
		if(pegStats == null){
			System.out.println("Please dont' do that, it's incredibly annoying.");
			return;
		}
		
		
		if(totalGuess == 12){
			bCount = checkBlack(pegStats);
			if(bCount == 4){
				// we need an alert box
				// you win
				System.out.println("you win, good job!");
				return;
			}else{
				// alert box here
				System.out.println("idiot, you lost");
				return;
			}
		}else{
			bCount = checkBlack(pegStats);
			if(bCount == 4){
				// we need an alert box
				// you win
				System.out.println("you win, good job!");
				return;
			}else{
				//just keep going if there are still moves
				MasterMind.incrementGuess();
				MasterMind.clearPegLoc();
				this.drawBoard();
				return;
			}
		}

	}
	
	public int checkBlack(ArrayList<BwPeg> pegStats){
		int bCount = 0;
		for(BwPeg e: pegStats){
			if(e.toString().equals("b")){
				bCount ++;
			}
		}
		return bCount;
	}

	@FXML
	void playBluePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.BLUE));
		this.drawBoard();
	}

	@FXML
	void playGreenPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.GREEN));
		this.drawBoard();
	}

	@FXML
	void playOrangePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.ORANGE)); 
		this.drawBoard();
	}

	@FXML
	void playPurplePeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.PURPLE)); 
		this.drawBoard();
	}

	@FXML
	void playRedPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.RED)); 
		this.drawBoard();
	}

	@FXML
	void playYellowPeg(ActionEvent event) {
		MasterMind.addColoredPeg(new ColorPeg(javafx.scene.paint.Color.YELLOW)); 
		this.drawBoard();
	}

	
	@FXML
	void undoLastMove(ActionEvent event) {
		MasterMind.undoLastPeg();
		//need to find a way to clear canvas, or it will leave the old peg trace on.
		this.drawBoard();
	}
	
	
	
	

	void drawPeg(GamePeg piece) {
		// Will get a GamePeg piece and depending on weather it's BW or Colored
		// will draw the peg in the work
	}

	void drawBoard() {
		
		GraphicsContext gc = mmCanvas.getGraphicsContext2D();
		
		ColorPeg[][] slots = MasterMind.getSlots();
		BwPeg[][] bwDraw = MasterMind.getPegDraw();
		gcWdth = mmCanvas.getWidth();
		gcHght = (mmCanvas.getHeight());
		double rHght = gcHght / 13;
		gc.setStroke(javafx.scene.paint.Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine((gcWdth * .865), 0, (gcWdth * .865), gcHght);
//		gc.strokeLine((gcWdth * .9), 0, (gcWdth * .9), gcHght);
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
			if(bwDraw[i][0] != null){
				gc.setFill(bwDraw[i][0].getColorMethod());
				gc.fillOval(gcWdth * .885, (i * rHght + (rHght / 6)), 20, 20);
				if(bwDraw[i][0].toString().equals("w")){
					gc.strokeOval(gcWdth * .885 + 9, (i * rHght + (rHght / 6)) + 9, 2, 2);
				}
			}else{
				gc.strokeOval(gcWdth * .885, (i * rHght + (rHght / 6)), 20, 20);
			}
			
			if(bwDraw[i][1] != null){
				gc.setFill(bwDraw[i][1].getColorMethod());
				gc.fillOval(gcWdth * .885, (i * rHght + (5 * rHght / 8)), 20, 20);
				if(bwDraw[i][1].toString().equals("w")){
					gc.strokeOval(gcWdth * .885 + 9, (i * rHght + (5 * rHght / 8)) + 9, 2, 2);
				}
			}else{
				gc.strokeOval(gcWdth * .885, (i * rHght + (5 * rHght / 8)), 20, 20);
				
			}
			
			if(bwDraw[i][2] != null){
				gc.setFill(bwDraw[i][2].getColorMethod());
				gc.fillOval(gcWdth * .935, (i * rHght + (rHght / 6)), 20, 20);
				if(bwDraw[i][2].toString().equals("w")){
					gc.strokeOval(gcWdth * .935 + 9, (i * rHght + (rHght / 6)) + 9, 2, 2);
				}
			}else{
				gc.strokeOval(gcWdth * .935, (i * rHght + (rHght / 6)), 20, 20);
				
			}
			
			if(bwDraw[i][3] != null){
				gc.setFill(bwDraw[i][3].getColorMethod());
				gc.fillOval(gcWdth * .935, (i * rHght + (5 * rHght / 8)), 20, 20);
				if(bwDraw[i][3].toString().equals("w")){
					gc.strokeOval(gcWdth * .935 + 9, (i * rHght + (5 * rHght / 8)) + 9, 2, 2);
				}
			}else{
				gc.strokeOval(gcWdth * .935, (i * rHght + (5 * rHght / 8)), 20, 20);
			}		
			
		}
		for (int i = 0; i < 13; i++) {
			
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			
			if(slots[i][0]==null){
				gc.clearRect(gcWdth * .1, (i * rHght + (rHght / 4)), 40, 40);
				gc.strokeOval(gcWdth * .1, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][0].getColorMethod());
				gc.fillOval(gcWdth * .1, (i * rHght + (rHght / 4)), 40, 40);
			}
			
			if(slots[i][1]==null){
				gc.clearRect(gcWdth * .3, (i * rHght + (rHght / 4)), 40, 40);
				gc.strokeOval(gcWdth * .3, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][1].getColorMethod());
				gc.fillOval(gcWdth * .3, (i * rHght + (rHght / 4)), 40, 40);
			}
			
			if(slots[i][2]==null){
				gc.clearRect(gcWdth * .5, (i * rHght + (rHght / 4)), 40, 40);
				gc.strokeOval(gcWdth * .5, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][2].getColorMethod());
				gc.fillOval(gcWdth * .5, (i * rHght + (rHght / 4)), 40, 40);
			}
			
			if(slots[i][3]==null){
				gc.clearRect(gcWdth * .7, (i * rHght + (rHght / 4)), 40, 40);
				gc.strokeOval(gcWdth * .7, (i * rHght + (rHght / 4)), 40, 40);
			}else{
				gc.setFill(slots[i][3].getColorMethod());
				gc.fillOval(gcWdth * .7, (i * rHght + (rHght / 4)), 40, 40);
			}
		}


	}

}
