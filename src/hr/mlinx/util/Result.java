package hr.mlinx.util;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

public class Result {
	
	public static String calculate(String text, boolean isRadians) {
		text = text.replaceAll("π", "pi");
		text = text.replaceAll("log", "log10");
		text = closeExpression(text);
		Expression e = new Expression(text);
		
		if (isRadians)
			mXparser.setRadiansMode();
		else
			mXparser.setDegreesMode();
		
		String resText = Double.toString(e.calculate());
		
		if (resText.endsWith(".0"))
			return resText.substring(0, resText.length() - 2);
		else
			return resText;
	}
	
	private static String closeExpression(String text) {
		int leftParentheses = text.chars()
								.reduce(0, (count, character) -> character == '(' ? count + 1 : count),
			rightParentheses = text.chars()
				 				 .reduce(0, (count, character) -> character == ')' ? count + 1 : count);
		
		for (int i = 0; i < leftParentheses - rightParentheses; ++i) {
			text += ")";
		}
		for (int i = 0; i < rightParentheses - leftParentheses; ++i) {
			text = "(" + text;
		}
		
		return text;
	}
	
//  private static final double ZERO_THRESHOLD = 1e-13;
//	
//	private static Map<String, Function<Double, Double>> functions = new LinkedHashMap<>();
//	private static Map<String, BiFunction<Double, Double, Double>> biFunctions = new LinkedHashMap<>();
//	
//	static {
//		biFunctions.put("+", (x, y) -> {
//			double res = x + y;
//			
//			if (Double.isInfinite(res))
//				throw new IllegalArgumentException("Error! (Overflow after addition)");
//			
//			return res;
//		});
//		biFunctions.put("-", (x, y) -> {
//			double res = x - y;
//			
//			if (Double.isInfinite(res))
//				throw new IllegalArgumentException("Error! (Overflow after subtraction)");
//			
//			return res;
//		});
//		biFunctions.put("*", (x, y) -> {
//			double res = x * y;
//			
//			if (Double.isInfinite(res))
//				throw new IllegalArgumentException("Error! (Overflow after multiplication)");
//			
//			return res;
//		});
//		biFunctions.put("/", (x, y) -> {
//			if (y == 0)
//				throw new IllegalArgumentException("Error! (Attempt to divide by zero)");
//			
//			double res = x / y;
//			
//			if (Double.isInfinite(res))
//				throw new IllegalArgumentException("Error! (Overflow after division)");
//			
//			return res;
//		});
//		biFunctions.put("%", (x, y) -> {
//			if (y == 0)
//				throw new IllegalArgumentException("Error! (Attempt to divide by zero)");
//			
//			if (Double.isInfinite(x))
//				throw new IllegalArgumentException("Error! (Modulo operator undefined for arguments)");
//			
//			return x % y;
//		});
//		biFunctions.put("^", (x, y) -> {
//			double res = Math.pow(x, y);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Undefined exponential function result)");
//			
//			return res;
//		});
//		functions.put("!", x -> {
//			if (x == 0)
//				return 1.0;
//			
//			if (!isInteger(x) || x < 0)
//				throw new IllegalArgumentException("Error! (Factorial is only defined for positive integers)");
//			
//			if (x > 170)
//				throw new IllegalArgumentException("Error! (Factorial undefined for x > 170)");
//			
//			if (x >= 1)
//	            return x * functions.get("!").apply(x - 1);
//	        else
//	            return 1.0;
//		});
//		functions.put("abs", x -> {
//			if (Double.isInfinite(x))
//				throw new IllegalArgumentException("Error! (Absolute function undefined for argument)");
//			
//			return x < 0.0 ? -x : x;
//		});
//		functions.put("sqrt", x -> {
//			if (x < 0)
//				throw new IllegalArgumentException("Error! (Square root undefined for negative values on the real plane)");
//			
//			double res = Math.sqrt(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Square root undefined)");
//			
//			return res;
//		});
//		functions.put("ln", x -> {
//			if (x == 0 || x < 0)
//				throw new IllegalArgumentException("Error! (Natural logarithmic function is only defined for positive non zero arguments)");
//			
//			double res = Math.log(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Natural log undefined for argument)");
//			
//			return res;
//		});
//		functions.put("log", x -> {
//			if (x == 0 || x < 0)
//				throw new IllegalArgumentException("Error! (Logarithmic function is only defined for positive non zero arguments)");
//			
//			double res = Math.log10(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Log undefined for argument)");
//			
//			return res;
//		});
//		functions.put("sin", x -> {
//			double res = Math.sin(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Sine undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//		functions.put("cos", x -> {
//			double res = Math.cos(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Cosine undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//		functions.put("tan", x -> {
//			if (x % (Math.PI / 2) == 0)
//				throw new IllegalArgumentException("Error! (Tangens undefined for argument)");
//			
//			double res = Math.tan(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Tangens undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//		functions.put("arcsin", x -> {
//			double res = Math.asin(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Arcus sine undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//		functions.put("arccos", x -> {
//			double res = Math.acos(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Arcus cosine undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//		functions.put("arctan", x -> {
//			if (Double.isInfinite(x))
//				throw new IllegalArgumentException("Error! (Arcus tangens undefined for argument)");
//			
//			double res = Math.atan(x);
//			
//			if (!Double.isFinite(res))
//				throw new IllegalArgumentException("Error! (Arcus tangens undefined for argument)");
//			
//			return isZero(res) ? 0.0 : res;
//		});
//	}
//	
//	private static boolean isInteger(double value) {
//		return (value == Math.floor(value)) && !Double.isInfinite(value);
//	}
//	
//	private static boolean isZero(double value) {
//		return value >= -ZERO_THRESHOLD && value <= ZERO_THRESHOLD;
//	}
	
}
