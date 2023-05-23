public class NumericalString {

	public static boolean legalNumericString(String s, int b) {// Task 3.1
		boolean ans = true;
		if (s == null | b < 2 | b > 10) {
			throw new IllegalArgumentException("there are a mistake on the input");
		} else {
			for (int i = 0; i < s.length() & ans; i = i + 1) {// checking if legal b that in correct base
				if (toInt(s.charAt(i)) >= b | toInt(s.charAt(i)) == -1) {// converting String to check. the second
																			// condition that if toInt return to us -1
																			// when the string isn't number like "abc"
					ans = false;
				}
			}
		}
		return ans;
	}

	public static int toInt(char c) {
		return "0123456789".indexOf(c);
	}

	public static String decimalIncrement(String s) {// Task 3.2
		String ans = "";

		if (legalNumericString(s, 10) == false) {// if base not equal 10
			throw new IllegalArgumentException("the String isn't on base 10");
		} else {
			ans = decimalIncrement(s, 1);

		}
		return ans;
	}

	public static String decimalIncrement(String s, int i) {// helper function
		String ans = "";
		if (s.length() == 0 & i == 0) {// stop condition
			ans = "";
		} else if (s.length() == 0 & i == 1) {
			ans = "1";
		} else if (toInt(s.charAt(0)) == 9 & i == 1) {
			ans = "0" + decimalIncrement(s.substring(1), 1);
		} else {
			// recursion that add 1 to the string
			ans = (toInt(s.charAt(0)) + i) + s.substring(1);
		}
		return ans;
	}

	public static String decimalDouble(String s) {// Task 3.3
		String ans = "";
		if (legalNumericString(s, 10) == false) {// if base not equal 10
			throw new IllegalArgumentException("the String isn't on base 10");
		} else {
			ans = decimalDouble(s, 2, 0);
		}
		return ans;
	}

	public static String decimalDouble(String s, int multi, int i) {// helper function
		String ans = "";
		if (s.length() == 0 & i == 0) {// stop
			ans = "";
		} else if (s.length() == 0 & i == 1) {// when we current have 1
			ans = "1";

		} else if (toInt(s.charAt(0)) * multi > 9) {/// if on the multiply we have 1 on the hand.

			ans = (((toInt(s.charAt(0)) * multi) % 10) + i) + decimalDouble(s.substring(1), multi, 1);
		} else {
			// recursion that multiply the string by 2
			ans = ((toInt(s.charAt(0)) * multi) + i) + decimalDouble(s.substring(1), multi, 0);

		}

		return ans;
	}

	public static String binary2Decimal(String s) {// Task 3.4
		String ans = "";
		if (legalNumericString(s, 2) == false) {// if base not equal 2
			throw new IllegalArgumentException("the String isn't on base 2");
		} else {
			ans = binary2Decimal(s, 0);
		}
		return ans;
	}

	public static String binary2Decimal(String s, int i) {// helper function
		String ans = "";
		if (s.length() == 1) {// return the value+ the first char
			ans = ans + s.charAt(0);
		} else if (s.charAt(0) == '1') {// send it to the adding function to +1 and multiply it
			ans = decimalIncrement(decimalDouble(binary2Decimal(s.substring(1))));
		} else {
			ans = decimalDouble(binary2Decimal(s.substring(1)));// multiply the string with sending to binary2Decimal
																// function
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println("Good Luck! :)");

	}

}
