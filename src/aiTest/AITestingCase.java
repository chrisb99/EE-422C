package aiTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import javafx.scene.paint.Color;

public class AITestingCase {

	/**
	 * First test case we used to prove out 
	 * */
	
	@Test
	public void testAI() {
		Color[] testSolution = { Color.RED, Color.ORANGE, Color.YELLOW, Color.PURPLE };
		int numOfBlacks = MasterMindAITesting.mainAI(testSolution);
		assertEquals(numOfBlacks, 4);
	}
	/**
	 * Asserts that our AI coould eventually find the solution.
	 * Will also print out to the console a percentage of losses. 
	 * 
	 * */
	@Test
	public void testAllPermutations() {
		Set<Color[]> colorPerms = new HashSet<Color[]>();
		ArrayList<Color> pCols = new ArrayList<Color>();
		pCols.add(Color.GREEN);
		pCols.add(Color.RED);
		pCols.add(Color.BLUE);
		pCols.add(Color.YELLOW);
		pCols.add(Color.PURPLE);
		pCols.add(Color.ORANGE);
		// Color [] possibleSolutions = new
		Color[] testSolution = new Color[4];
		for (int i = 0; i < pCols.size(); i++) {
			for (int j = 0; j < pCols.size(); j++) {
				for (int k = 0; k < pCols.size(); k++) {
					for (int l = 0; l < pCols.size(); l++) {
						testSolution = new Color[4];
						testSolution[0]=pCols.get(i);
						testSolution[1]=pCols.get(j);
						testSolution[2]=pCols.get(k);
						testSolution[3]=pCols.get(l);
						colorPerms.add(testSolution);
					}
				}
			}

		}
		int numLosses = 0;
		for(Color [] e: colorPerms){
			int numBlacks = MasterMindAITesting.mainAI(e);
			assertEquals(numBlacks,4);
			if(MasterMindAITesting.guessNo>12){
				numLosses ++;
			}
			
		}
		double lossPercent = (double)numLosses/colorPerms.size();
		System.out.println(lossPercent);
		
		
	}

}
