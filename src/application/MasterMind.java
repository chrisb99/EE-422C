package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class MasterMind {
	private ColorPeg[][] coloredSlots = new ColorPeg[13][4];
	private FeedBack[] checking = new FeedBack[13];
	private static int guess;
	
	
	
	

	private void generateAnswer(){
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
	public static void addColoredPeg(ColorPeg lastSelectedPin){
		//Take the Pin the ColoredPeg passed in and add it colorSlots matrix
	}
	public static void startGame(MMController mmCont){
		//Take the controller class passed from main and draw the Canvas
		mmCont.drawBoard();
	}
	public static int getGuess(){
		//Will return the number of the guess we are currently on.
		return guess;}
	public static int pegsInGuess(){
		//This will return the number of pegs that are in the current guess
		return 0;
	}
	
	public void getFeedBack(){
		FeedBack userGuess = new FeedBack(coloredSlots[guess]);
	}
	
	
	//see if the game is finished, and with player wins
	public String checkStatus(){
		return null;
	}
	
	private class FeedBack{
		private ArrayList<BwPeg> bwStatus = new ArrayList<BwPeg>(4);
		
		public FeedBack(ColorPeg[] userGuess){
			ColorPeg[] answer = coloredSlots[0];
			boolean[] correct = new boolean[4];
			
			//check back later
			for(int i=0; i<4; i++){
				if(userGuess[i].equals(answer[i])){
					bwStatus.add(new BwPeg(Color.BLACK));
				}else{	
					for(int j = 0; j < 4; j++){
						if(userGuess[i].equals(answer[j])){
							bwStatus.add(new BwPeg(Color.WHITE));
						}
					}
				}
			}
			
			
			
//			if(userGuess.equals(answer)){
//				for(int i = 0; i < 4; i ++){
//					status.add(new BwPeg(Color.BLACK));
//				}
//			}
			
			
			
			
		}
	}
}
