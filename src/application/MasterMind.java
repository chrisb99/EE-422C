package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class MasterMind {
	private ColorPeg[][] slots = new ColorPeg[13][4];
	private Status[] checking = new Status[13];
	private int guess;
	
	
	
	

	private void generateAnswer(){
		Random rand = new Random();
		for(int i = 0; i < 4; i ++){
			int c = rand.nextInt(6);
			switch(c){
			case 0:
				slots[0][i] = new ColorPeg(Color.GREEN);
				continue;
			case 1:
				slots[0][i] = new ColorPeg(Color.BLUE);
				continue;
			case 2:
				slots[0][i] = new ColorPeg(Color.ORANGE);
				continue;
			case 3:
				slots[0][i] = new ColorPeg(Color.PURPLE);
				continue;
			case 4:
				slots[0][i] = new ColorPeg(Color.RED);
				continue;
			case 5:
				slots[0][i] = new ColorPeg(Color.YELLOW);
				continue;
			}
		}
	}
	
	
	
	
	public void getStatus(){
		Status userGuess = new Status(slots[guess]);
	}
	
	
	//see if the game is finished, and with player wins
	public String checkStatus(){
		return null;
	}
	
	private class Status{
		private ArrayList<BwPeg> bwStatus = new ArrayList<BwPeg>(4);
		
		public Status(ColorPeg[] userGuess){
			ColorPeg[] answer = slots[0];
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
