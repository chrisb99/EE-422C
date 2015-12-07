package aiTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import application.BwPeg;
import application.ColorPeg;
import application.MasterMind;
import javafx.scene.paint.Color;

public class MasterMindAITesting {

	private static Color[] correctSolution;

	private static Map<Color, String> colorMap;
	public static int guessNo = 0;

	public static int mainAI(Color[] cs) {
		guessNo = 0;
		colorMap = new HashMap<Color, String>();
		colorMap.put(Color.GREEN, "Green");
		colorMap.put(Color.RED, "Red");
		colorMap.put(Color.BLUE, "Blue");
		colorMap.put(Color.YELLOW, "Yellow");
		colorMap.put(Color.PURPLE, "Purple");
		colorMap.put(Color.ORANGE, "Orange");
		display(cs);

		correctSolution = cs;
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
			int numOfColor = solutionChecker(solutionGuess);
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
				int nmBlcks = solutionChecker(solutionGuess);
				for (int i = 0; i < (ttlOfClrInSl - nmBlcks); i++) {
					leftSide.add(e);
				}
				for (int i = 0; i < nmBlcks; i++) {
					rightSide.add(e);
				}
			}
		}

		// System.out.println("leftSide" + " " + colorMap.get(leftSide.get(0)) +
		// " " + colorMap.get(leftSide.get(1)));
		// System.out.println("rightSide" + " "+ colorMap.get(rightSide.get(0))
		// + " " + colorMap.get(rightSide.get(1)));

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
			numOfBlacks = solutionChecker(solsItter.next());
		}
		return numOfBlacks;
	}

	// Will return the number of correct bPegs.
	static int solutionChecker(Color[] possibleAnswer) {
		int bCount = 0;
		display(possibleAnswer);
		for (int i = 0; i < 4; i++) {
			bCount += possibleAnswer[i] == correctSolution[i] ? 1 : 0;
		}
		return bCount;

	}

	private static void display(Color[] colorArr) {
		for (Color e : colorArr) {
			System.out.print(colorMap.get(e) + " ");
		}
		System.out.print(guessNo);
		guessNo++;
		System.out.println();

	}

}
