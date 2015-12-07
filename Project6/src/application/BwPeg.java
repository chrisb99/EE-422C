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

public class BwPeg extends GamePeg{
	
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
