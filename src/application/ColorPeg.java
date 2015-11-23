package application;

import javafx.scene.paint.Color;

public class ColorPeg extends GamePeg{
	
	private Color pegColor;
	private char pegChar;

	@Override
	public Color getColorMethod() {
		return pegColor;
	}

	@Override
	public String toString() {
		String s = new String();
		s = pegChar + "";
		return s;
	}
	
	public ColorPeg(Color c){
		this.pegColor = c;
		this.pegChar = c.toString().charAt(0);
	}

}
