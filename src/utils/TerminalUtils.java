package utils;

import spells.Element;

import java.io.PrintStream;

public class TerminalUtils {
	private static final String reset = (char) 27 + "[0m";
	public static void printWithColor(PrintStream out, String toPrint, Colors color) {
		out.println(color.colorCode() + toPrint + reset);
	}
	public static void printWithElement(PrintStream out, String toPrint, Element elem) {
		switch (elem) {
			case FIRE:
				printWithColor(out, toPrint, Colors.RED);
				break;
			case LEAF:
				printWithColor(out, toPrint, Colors.GREEN);
				break;
			case WATER:
				printWithColor(out, toPrint, Colors.BLUE);
				break;
			case ROCK:
				printWithColor(out, toPrint, Colors.BROWN);
				break;
			case THUNDER:
				printWithColor(out, toPrint, Colors.YELLOW);
				break;
			case NORMAL:
				printWithColor(out, toPrint, Colors.GREY);
		}
	}

	public enum Colors {
		BLACK(30), GREY(90), RED(91), BROWN(31), GREEN(32), YELLOW(33), BLUE(94), MAGENTA(5), CYAN(6), WHITE(7);

		private int colorId;
		Colors(int i) {
			this.colorId = i; //ANSI CSI color codes are from 30 to 37
		}
		public String colorCode(){
			return (char) 27 + "[" + colorId + ";1m";
		}
	}
}
