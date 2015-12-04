package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MMController {
	@FXML
	private Canvas mmCanvas;
	private static Double gcWdth;
	private static Double gcHght;
	private static GraphicsContext gc;
	private static double rHght;
	
	@FXML
	private Button blackButton;
	@FXML
	private Button whiteButton;
	@FXML
	private Button redButton;
	@FXML
	private Button blueButton;
	@FXML
	private Button yellowButton;
	@FXML
	private Button purpleButton;
	@FXML
	private Button greenButton;
	@FXML
	private Button orangeButton;
	@FXML
	private Button startButton;
	
	@FXML
	void startButton(ActionEvent event){
		startButton.setVisible(false);
		redButton.setVisible(false);
		blueButton.setVisible(false);
		yellowButton.setVisible(false);
		purpleButton.setVisible(false);
		orangeButton.setVisible(false);
		greenButton.setVisible(false);
		MasterMind.incrementGuess();
	}

	@FXML
	void checkButton(ActionEvent event) {
		// Check button is pressed fire this code to check the player's guess
		int guessNum = MasterMind.getGuess();
		int bCount = 0;
		ArrayList<BwPeg> pegStats = MasterMind.getFeedBack();

		// if you are already on 12, that mean you have no more moves
		// we don't have an end yet

		if (pegStats == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Guess");
			alert.setHeaderText("Each guess must have 4 pegs in it.");
			alert.setContentText("");
			alert.showAndWait();
			System.out.println("Please dont' do that, it's incredibly annoying.");
			return;
		}
		if(!MasterMind.getAi()){
			drawBWPegs();
		}
		bCount = checkBlack(pegStats);
		if ((bCount == 4) || (guessNum == 12)) {
			String endText = new String();
			endText = MasterMind.getAi()? "AI" : "You";
			if (bCount == 4) {
				endText += " win!";
			} else {
				endText += " suck!";
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("End of Game");
			alert.setHeaderText(endText);
			alert.setContentText("Would you like to play again?");
			startButton.setVisible(true);
			redButton.setVisible(true);
			blueButton.setVisible(true);
			yellowButton.setVisible(true);
			purpleButton.setVisible(true);
			orangeButton.setVisible(true);
			greenButton.setVisible(true);
			blackButton.setVisible(true);
			whiteButton.setVisible(true);
			ButtonType buttonTypeOne = new ButtonType("Play Again");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				launchMastermind();
			} else {
				// ... user chose CANCEL or closed the dialog
			}

		} else {
			MasterMind.incrementGuess();
		}
	}

	public int checkBlack(ArrayList<BwPeg> pegStats) {
		int bCount = 0;
		for (BwPeg e : pegStats) {
			if (e.toString().equals("b")) {
				bCount++;
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
	void playWhitePeg(ActionEvent event) {
		MasterMind.addBwPeg(new BwPeg(javafx.scene.paint.Color.WHITE));
		this.drawBoard();
	}
	
	@FXML
	void playBlackPeg(ActionEvent event) {
		MasterMind.addBwPeg(new BwPeg(javafx.scene.paint.Color.BLACK));
		this.drawBoard();
	}

	@FXML
	void undoLastMove(ActionEvent event) {
		MasterMind.undoLastPeg();
		this.drawBoard();
	}

	void launchMastermind() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Welcome to Mastermind");
		alert.setHeaderText("What would you like to do");
		ButtonType playButton = new ButtonType("Play Mastermind!");
		ButtonType watchButton = new ButtonType("Computer Play's");
		alert.getButtonTypes().setAll(playButton, watchButton);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == playButton) {
			// Game starts and the player plays.
			startButton.setVisible(false);
			blackButton.setVisible(false);
			whiteButton.setVisible(false);
			MasterMind.startGame();
			drawBoard();
		} else {
			MasterMind.startAIGame();
			computerPlays();
			drawBoard();
			
		}
	}

	// TODO Write the algorithm for the computer playing the Game and use a
	// timer like Project so we can watch each step the computer makes as an
	// animation
	void computerPlays() {
		//Can call the button click methods and recycle everything from above.
		return;
	}

	void drawBoard() {
		gc = mmCanvas.getGraphicsContext2D();
		ColorPeg[][] slots = MasterMind.getSlots();
		BwPeg[][] bwDraw = MasterMind.getPegDraw();
		gcWdth = mmCanvas.getWidth();
		gcHght = (mmCanvas.getHeight());
		gc.setFill(Color.DARKGOLDENROD);
//		Image backGround;
//		backGround = new Image(getClass().getResourceAsStream("Iroko_wood.png"));
//		gc.drawImage(backGround, 0, 0, gcHght, gcWdth);
		gc.fillRect(0, 0, gcWdth, gcHght);
		rHght = gcHght / 13;
		gc.setStroke(javafx.scene.paint.Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine((gcWdth * .865), 0, (gcWdth * .865), gcHght);
		gc.strokeLine((gcWdth), 0, (gcWdth), gcHght);
		gc.setLineWidth(3);
		gc.strokeLine(0, gcHght, gcWdth, gcHght);
		gc.strokeLine(0, gcHght, gcWdth, gcHght);
		gc.strokeLine(0, 0, 0, gcHght);
		gc.strokeLine(0, 0, gcWdth, 0);
		// Draw the Status
		gc.setLineWidth(2);
		// Draw BW PEGS
		drawBWPegs();
		// Draw the ColoredSlots and covers the top with the title.
		if(MasterMind.getAi()){
			drawColoredSlots(false);
		}else{
		drawColoredSlots(true);
		}
	}

	private void drawBWPegs() {
		gc.setLineWidth(4);
		BwPeg[][] bwDraw = MasterMind.getPegDraw();
		double horizOffset;
		double vrtclOffset;
		for (int i = 1; i < 13; i++) {
			gc.strokeLine((0), (rHght * i), (gcWdth), ((gcHght / 13) * i));
			for (int j = 0; j < 4; j++) {
				horizOffset = (j == 0 || j == 1) ? .885 : .935;
				vrtclOffset = (j == 1 || j == 3) ? ((5 * rHght) / 8) : (rHght / 6);
				if (bwDraw[i][j] != null) {
					gc.setFill(bwDraw[i][j].getColor());
					gc.strokeOval(gcWdth * horizOffset, (i * rHght + vrtclOffset), 20, 20);
					gc.fillOval(gcWdth * horizOffset, (i * rHght + vrtclOffset), 20, 20);
				} else {
					gc.strokeOval(gcWdth * horizOffset, (i * rHght + vrtclOffset), 20, 20);
				}
			}
		}

	}

	private void drawColoredSlots(boolean coverSet) {
		// Draw the Colored Slot Matrix on to the Board
		ColorPeg[][] slots = MasterMind.getSlots();
		double horizOffset;
		gc.setLineWidth(2);
		for (int i = 0; i < 13; i++) {
			horizOffset = .1;
			for (int j = 0; j < 4; j++) {
				if (slots[i][j] == null) {
					gc.setFill(Color.WHITE);
					gc.fillOval(gcWdth * horizOffset, (i * rHght + (rHght / 4)), 40, 40);
					gc.strokeOval(gcWdth * horizOffset, (i * rHght + (rHght / 4)), 40, 40);
				} else {
					gc.setFill(slots[i][j].getColor());
					gc.fillOval(gcWdth * horizOffset, (i * rHght + (rHght / 4)), 40, 40);
				}
				horizOffset += .2;
			}
			horizOffset = .1;
		}
		if (coverSet) {
			Image img;
			img = new Image(getClass().getResourceAsStream("MastermindLogo.png"));
			gc.drawImage(img, 0, 0, (gcWdth * .865), rHght);
		}
	}
	public int aiBlackCounter(Color [] computersGues){
		return 0;
	}
	
	

	public void mainAI(Color[] cs) {
		int guessNo = 0;
		Map colorMap = new HashMap<Color, String>();
		colorMap.put(Color.GREEN, "Green");
		colorMap.put(Color.RED, "Red");
		colorMap.put(Color.BLUE, "Blue");
		colorMap.put(Color.YELLOW, "Yellow");
		colorMap.put(Color.PURPLE, "Purple");
		colorMap.put(Color.ORANGE, "Orange");
		Color incorrect = null;
		Map<Color, Integer> colors = new HashMap<Color, Integer>();
		ArrayList<Color> inSolution = new ArrayList<Color>();
		ArrayList<Color> leftSide = new ArrayList<Color>();
		ArrayList<Color> rightSide = new ArrayList<Color>();
		Color[] solutionGuess = new Color[4];
		// add all the colors set to 0 to the map.
		colors.put(Color.GREEN, 0);
		colors.put(Color.RED, 0);
		colors.put(Color.PURPLE, 0);
		colors.put(Color.ORANGE, 0);
		colors.put(Color.YELLOW, 0);
		colors.put(Color.BLUE, 0);
		Set<Color> colorKeys = colors.keySet();
		Iterator<Color> colorItterator = colorKeys.iterator();
		int slotsFound = 0;

		while (colorItterator.hasNext() && slotsFound < 4) {
			Color e = colorItterator.next();
			for (int i = 0; i < 4; i++) {
				solutionGuess[i] = e;
			}
			int numOfColor = aiBlackCounter(solutionGuess);
			if (numOfColor > 0) {
				inSolution.add(e);
				slotsFound += numOfColor;
			} else {
				incorrect = e;
			}
			colors.put(e, colors.get(e) + numOfColor);
		}
		// Mask the left side with a color that's not in the Solution
		for (Color e : inSolution) {
			if (leftSide.size() == 2) {
				for (int i = 0; i < colors.get(e); i++) {
					rightSide.add(e);
				}
			} else if (rightSide.size() == 2) {
				for (int i = 0; i < colors.get(e); i++) {
					leftSide.add(e);
				}
			} else {
				solutionGuess[0] = incorrect;
				solutionGuess[1] = incorrect;
				solutionGuess[2] = e;
				solutionGuess[3] = e;
				int ttlOfClrInSl = colors.get(e);
				int nmBlcks = aiBlackCounter(solutionGuess);
				for (int i = 0; i < (ttlOfClrInSl - nmBlcks); i++) {
					leftSide.add(e);
				}
				for (int i = 0; i < nmBlcks; i++) {
					rightSide.add(e);
				}
			}
		}
		// Build a solution masked with a color not in the sequence
		ArrayList<Color[]> finalSetOfSolutions = new ArrayList<Color[]>();
		solutionGuess = new Color[4];
		solutionGuess[0] = leftSide.get(0);
		solutionGuess[1] = leftSide.get(1);
		solutionGuess[2] = rightSide.get(0);
		solutionGuess[3] = rightSide.get(1);
		finalSetOfSolutions.add(solutionGuess);

		solutionGuess = new Color[4];
		solutionGuess[0] = leftSide.get(1);
		solutionGuess[1] = leftSide.get(0);
		solutionGuess[2] = rightSide.get(0);
		solutionGuess[3] = rightSide.get(1);
		finalSetOfSolutions.add(solutionGuess);

		solutionGuess = new Color[4];
		solutionGuess[0] = leftSide.get(0);
		solutionGuess[1] = leftSide.get(1);
		solutionGuess[2] = rightSide.get(1);
		solutionGuess[3] = rightSide.get(0);
		finalSetOfSolutions.add(solutionGuess);

		solutionGuess = new Color[4];
		solutionGuess[0] = leftSide.get(1);
		solutionGuess[1] = leftSide.get(0);
		solutionGuess[2] = rightSide.get(1);
		solutionGuess[3] = rightSide.get(0);
		finalSetOfSolutions.add(solutionGuess);

		Iterator<Color[]> solsItter = finalSetOfSolutions.iterator();
		int numOfBlacks = 0;
		while (solsItter.hasNext() && numOfBlacks < 4) {
			numOfBlacks = aiBlackCounter(solsItter.next());
		}
	}

	
}
