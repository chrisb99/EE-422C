package application;

import javafx.scene.paint.Color;

public class BwPeg extends GamePeg{
	
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
	
	public BwPeg(Color c){
		this.pegColor = c;
		switch(c.toString()){
		case "0x000000ff":
			this.pegChar = 'b';
			break;
		case "0xffffffff":
			this.pegChar = 'w';
			break;

		}
	}
}
