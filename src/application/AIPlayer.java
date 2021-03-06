package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.scene.paint.Color;

public class AIPlayer {

	public int aiBlackCounter(Color[] computersGuess) {
		for (Color e : computersGuess) {
			MasterMind.addColoredPeg(new ColorPeg(e));
		}
		int bCount = 0;
		ArrayList<BwPeg> pegStats = MasterMind.getFeedBack();
		for (BwPeg e : pegStats) {
			if (e.toString().equals("b")) {
				bCount++;
			}
		}

		MasterMind.incrementGuess();
		return bCount;
	}

	/**
	 * Runs the AI which can guess the correct solution with a 16 percent
	 * chance of error
	 */
	public boolean play() {
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
		int bCount = 0;
		while (solsItter.hasNext() && bCount < 4 && MasterMind.getGuess() <= 12) {
			bCount = aiBlackCounter(solsItter.next());
		}
		if (bCount == 4) {
			return true;
		} else {
			return false;
		}
	}

}
