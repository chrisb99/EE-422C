/* CRITTERS <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * <Hasun Amarasekara>
 * <hua59>
 * <16345>
 * <Tianyi Bi>
 * <tb25947>
 * <16345>
 * Slip days used: <1>
 * Fall 2015
 */
package application;

import javafx.scene.paint.Color;

public class ColorPeg extends GamePeg{
	
	private Color pegColor;
	private char pegChar;

	@Override
	public Color getColor() {
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
		switch(c.toString()){
		case "0xff0000ff":
			this.pegChar = 'R';
			break;
		case "0x0000ffff":
			this.pegChar = 'B';
			break;
		case "0x008000ff":
			this.pegChar = 'G';
			break;
		case "0x800080ff":
			this.pegChar = 'P';
			break;
		case "0xffa500ff":
			this.pegChar = 'O';
			break;
		case "0xffff00ff":
			this.pegChar = 'Y';

		}
	}

}
