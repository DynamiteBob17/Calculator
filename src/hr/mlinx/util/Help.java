package hr.mlinx.util;

public class Help {
	
	public static final int BTN_AMOUNT = 35;
	
	public static final String[] BTN_TEXTS = new String[] {
			"<html>x<sup>2</sup></html>", "|x|", "x!", "(", ")", "<html>x<sup>-1</sup></html>", "Ans", 
			"Inv", "sin", "ln", "7", "8", "9", "÷",
			"π", "cos", "log", "4", "5", "6", "x",
			"e", "tan", "√", "1", "2", "3", "-",
			"CLR", "AC", "<html>x<sup>y</sup></html>", "0", ".", "=", "+"
	};
	
	public static final int SQUARE = 0;
	public static final int ABSOLUTE = 1;
	public static final int FACTORIAL = 2;
	public static final int LEFT_BRACKET = 3;
	public static final int RIGHT_BRACKET = 4;
	public static final int OVER_X = 5;
	public static final int ANS = 6;
	public static final int INVERSE = 7;
	public static final int SINE = 8;
	public static final int NAT_LOG = 9;
	public static final int SEVEN = 10;
	public static final int EIGHT = 11;
	public static final int NINE = 12;
	public static final int DIVISON = 13;
	public static final int PI = 14;
	public static final int COSINE = 15;
	public static final int LOG = 16;
	public static final int FOUR = 17;
	public static final int FIVE = 18;
	public static final int SIX = 19;
	public static final int MULTIPLY = 20;
	public static final int EULER = 21;
	public static final int TANGENS = 22;
	public static final int SQRT = 23;
	public static final int ONE = 24;
	public static final int TWO = 25;
	public static final int THREE = 26;
	public static final int MINUS = 27;
	public static final int CLEAR = 28;
	public static final int AC = 29;
	public static final int POW = 30;
	public static final int ZERO = 31;
	public static final int POINT = 32;
	public static final int EQUALS = 33;
	public static final int PLUS = 34;
	
	public static boolean isDarkButton(int idx) { 
		return idx == Help.TANGENS || idx == Help.SQRT || idx == Help.ABSOLUTE 
				|| idx == Help.FACTORIAL || idx == Help.SINE 
				|| idx == Help.NAT_LOG || idx == Help.PI 
				|| idx == Help.EULER || idx == Help.POW
				|| idx == Help.COSINE || idx == Help.LOG || idx == Help.SQUARE;
	}
	
	public static boolean isDigit(int idx) {
		return idx == Help.ZERO || idx == Help.ONE || idx == Help.TWO || idx == Help.THREE || idx == Help.FOUR
				|| idx == Help.FIVE || idx == Help.SIX || idx == Help.SEVEN
				|| idx == Help.EIGHT || idx == Help.NINE;
	}
	
	public static boolean isFunction(int idx) {
		return idx == Help.SINE || idx == Help.COSINE || idx == Help.TANGENS || idx == Help.ABSOLUTE
				|| idx == Help.EULER || idx == Help.PI || idx == Help.LOG || idx == Help.NAT_LOG;
	}
	
	public static boolean isComplexArithmetic(int idx) {
		return idx == Help.DIVISON || idx == Help.MULTIPLY;
	}
	
	public static boolean isSimpleArithmetic(int idx) {
		return idx == Help.MINUS || idx == Help.PLUS;
	}
	
}
