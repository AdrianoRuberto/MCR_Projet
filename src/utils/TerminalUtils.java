package utils;

import game.Element;

public class TerminalUtils {
	private static final String reset = (char) 27 + "[0m";

	public static String colorize(String toPrint, Colors color) {
		return color.colorCode() + toPrint + reset;
	}

	public static String colorize(String toPrint, Element elem) {
		switch (elem) {
			case FIRE:
				return colorize(toPrint, Colors.RED);
			case LEAF:
				return colorize(toPrint, Colors.GREEN);
			case WATER:
				return colorize(toPrint, Colors.BLUE);
			case ROCK:
				return colorize(toPrint, Colors.BROWN);
			case THUNDER:
				return colorize(toPrint, Colors.YELLOW);
			case NORMAL:
				return colorize(toPrint, Colors.GREY);
			default:
				return toPrint;
		}
	}

	public enum Colors {
		BLACK(30), GREY(90), RED(91), BROWN(31), GREEN(32), YELLOW(33), DARKBLUE(34), BLUE(94), MAGENTA(5), CYAN(6), WHITE(7);

		private int colorId;
		Colors(int i) {
			this.colorId = i; //ANSI CSI color codes are from 30 to 37
		}
		public String colorCode(){
			return (char) 27 + "[" + colorId + ";1m";
		}
	}
}
