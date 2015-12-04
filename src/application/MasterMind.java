package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public final class MasterMind {
	private static ColorPeg[][] coloredSlots;
	private static int guess;
	private static int pegLoc;
	private static int bwPegLoc;
	private static BwPeg[][] pegDraw;
	private static boolean ai = false;

	public static int getGuess() {
		// Will return the number of the guess we are currently on.
		return guess;
	}

	public static int getPegLoc() {
		return pegLoc;
	}
	
	public static int getBwPegLoc(){
		return bwPegLoc;
	}

	public static ColorPeg[][] getSlots() {
		return coloredSlots;
	}
	
	public static BwPeg[][] getPegDraw() {
		return pegDraw;
	}

	public static void incrementGuess() {
		guess += 1;
		pegLoc = 0;
		bwPegLoc = 0;
	}
	
	public static void setAi(){
		ai = true;
	}
	public static boolean getAi(){
		return ai;
	}

	// generate answers
	private static void generateAnswer() {
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			int c = rand.nextInt(6);
			switch (c) {
			case 0:
				coloredSlots[0][i] = new ColorPeg(Color.GREEN);
				continue;
			case 1:
				coloredSlots[0][i] = new ColorPeg(Color.BLUE);
				continue;
			case 2:
				coloredSlots[0][i] = new ColorPeg(Color.ORANGE);
				continue;
			case 3:
				coloredSlots[0][i] = new ColorPeg(Color.PURPLE);
				continue;
			case 4:
				coloredSlots[0][i] = new ColorPeg(Color.RED);
				continue;
			case 5:
				coloredSlots[0][i] = new ColorPeg(Color.YELLOW);
				continue;
			}
		}
	}
	//
	public static void addColoredPeg(ColorPeg lastSelectedPeg) {
		// Take the Pin the ColoredPeg passed in and add it colorSlots matrix
		if (pegLoc > 3)
			return;
		coloredSlots[guess][pegLoc] = lastSelectedPeg;
		pegLoc++;
	}
	
	
	public static void addBwPeg(BwPeg lastSelectedPeg){
		if (bwPegLoc > 3)
			return;
		pegDraw[guess][bwPegLoc] = lastSelectedPeg;
		bwPegLoc ++;
	}

	public static void undoLastPeg() {
		if(ai){
			if (bwPegLoc == 0 || guess == 0)
				return;
			bwPegLoc --;
			pegDraw[guess][bwPegLoc] = null;
		}else{
			if (pegLoc == 0)
				return;
			pegLoc--;
			coloredSlots[guess][pegLoc] = null;
		}
	}

	public static void startGame() {
		// Take the controller class passed from main and draw the Canvas
		MasterMind.coloredSlots = new ColorPeg[13][4];
		MasterMind.guess = 1;
		MasterMind.pegLoc = 0;
		MasterMind.pegDraw = new BwPeg[13][4];
		MasterMind.ai = false;
		generateAnswer();
	}
	
	public static void startAIGame(){
		MasterMind.coloredSlots = new ColorPeg[13][4];
		MasterMind.guess = 0;
		MasterMind.pegLoc = 0;
		MasterMind.pegDraw = new BwPeg[13][4];
		MasterMind.ai = true;
	}

	public static ArrayList<BwPeg> getFeedBack() {
		
		ArrayList<BwPeg> bwFeedBack = new ArrayList<BwPeg>(4);
		if(ai){
			int i = 0;
			while(pegDraw[guess][i] != null){
				bwFeedBack.add(pegDraw[guess][i]);
			}
		}
		else{
			ColorPeg[] userGuess = coloredSlots[guess];
			ColorPeg[] answer = coloredSlots[0];
			boolean[] checkBorW = new boolean[4];
			//First Check for Black Pegs
			for (int i = 0; i < 4; i++) {
				if (userGuess[i] == null) {
					return null;
				} else if (userGuess[i].getColor().equals(answer[i].getColor())) {
					checkBorW[i] = true;
					bwFeedBack.add(new BwPeg(Color.BLACK));
				}
			}
<<<<<<< HEAD
			for(int j = 0; j < 4; j++){
				
					//if the user's guesses already right, skip this one						
				if(correct[j]){
					continue;
				}else if(userGuess[i].toString().equals(answer[j].toString())){
					bwFeedBack.add(new BwPeg(Color.WHITE));
=======
			// Then check for White pegs last
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if ((!checkBorW[j]) && userGuess[i].getColor().equals(answer[j].getColor())) {
						bwFeedBack.add(new BwPeg(Color.WHITE));
						checkBorW[j] = true;
						break;
					}
>>>>>>> TurkeyDayBranch
				}
			}

			for (int i = 0; i < bwFeedBack.size(); i++) {
				pegDraw[guess][i] = bwFeedBack.get(i);
			}
		}
		return bwFeedBack;
	}
	// Because the Constructor is private it almost stimulates a Static class though Java doesn't allow top level static classes
    // Suppress default constructor for noninstantiability
	private  MasterMind() {
        throw new AssertionError();

	}

}
