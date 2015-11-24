package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class MasterMind {
	private static ColorPeg[][] coloredSlots;
	private static int guess;
	private static int pegLoc;
	
	public static int getGuess(){
		//Will return the number of the guess we are currently on.
		return guess;}
	
	public static int getPegLoc(){
		return pegLoc;
	}
	
	public static void incrementGuess(){
		guess += 1;
		pegLoc = 0;
	}
	
	//generate answers
	private static void generateAnswer(){
		Random rand = new Random();
		for(int i = 0; i < 4; i ++){
			int c = rand.nextInt(6);
			switch(c){
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
	public static void addColoredPeg(ColorPeg lastSelectedPeg){
		//Take the Pin the ColoredPeg passed in and add it colorSlots matrix
		
		if(pegLoc > 3) return;
		coloredSlots[guess][pegLoc] = lastSelectedPeg;
		pegLoc ++;
	}
	
	
	public static void startGame(MMController mmCont){
		//Take the controller class passed from main and draw the Canvas
		mmCont.drawBoard();
	}
	
	
	public static ArrayList<BwPeg> getFeedBack(){
		ArrayList<BwPeg> bwFeedBack = new ArrayList<BwPeg>(4);		
		ColorPeg[] userGuess = coloredSlots[guess];
		ColorPeg[] answer = coloredSlots[0];
		boolean[] correct = new boolean[4];
		
		
		//check black pegs first
		for(int i = 0; i < 4; i ++){
			if(userGuess[i].equals(answer[i])){
				correct[i] = true;
				bwFeedBack.add(new BwPeg(Color.BLACK));
			}
		}
		
		//check white pegs last
		for(int i=0; i<4; i++){
			if(correct[i]){
				continue;
			}
			for(int j = 0; j < 4; j++){
				
					//if the user's guesses already right, skip this one						
				if(correct[j]){
					continue;
				}else if(userGuess[i].equals(answer[j])){
					bwFeedBack.add(new BwPeg(Color.WHITE));
				}
			}
			
		}	
		return bwFeedBack;
	}
	
	//constructor for master mind
	public MasterMind(){
		this.coloredSlots = new ColorPeg[13][4];
		this.guess = 1;
		this.pegLoc = 0;
	}
	
	
}
