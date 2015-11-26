package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MMController {
	@FXML
	private Canvas mmCanvas;
	private static Double gcWdth;
	private static Double gcHght;
	// private boolean coverSet = true;

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
		drawBWPegs();
		bCount = checkBlack(pegStats);
		if ((bCount == 4) || (guessNum == 12)) {
			String endText;
			if (bCount == 4) {
				endText = "you win, good job!";
			} else {
				endText = "Hahaha you suck!";
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("End of Game");
			alert.setHeaderText(endText);
			alert.setContentText("Choose your option.");

			ButtonType buttonTypeOne = new ButtonType("One");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				 MasterMind.startGame();
				 drawBoard();
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
	void undoLastMove(ActionEvent event) {
		MasterMind.undoLastPeg();
		// need to find a way to clear canvas, or it will leave the old peg
		// trace on.
		this.drawBoard();
	}

	private static GraphicsContext gc;
	static double rHght;

	void drawBoard() {
		gc = mmCanvas.getGraphicsContext2D();
		ColorPeg[][] slots = MasterMind.getSlots();
		BwPeg[][] bwDraw = MasterMind.getPegDraw();
		gcWdth = mmCanvas.getWidth();
		gcHght = (mmCanvas.getHeight());
		gc.setFill(Color.SANDYBROWN);
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
		drawColoredSlots(true);
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
					gc.fillOval(gcWdth * horizOffset, (i * rHght + vrtclOffset), 18, 18);
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

}
