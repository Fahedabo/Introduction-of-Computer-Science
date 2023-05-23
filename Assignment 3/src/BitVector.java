public class BitVector {
	private Bit[] bits;

	// Task 4.4
	public BitVector(String s) throws IllegalArgumentException {
		for (int i = 0; i < s.length(); i = i + 1) {
			if (s.charAt(i) != '0' & s.charAt(i) != '1') {
				throw new IllegalArgumentException("there are a mistake on the input");
			}
		}
		bits = new Bit[s.length()];// definition the length of bits
		for (int i = 0; i < s.length(); i = i + 1) {
			if (s.charAt(i) == '0')
				bits[i] = new Bit(false);// when we found 0 put on Bit false
			else {
				bits[i] = new Bit(true);// when we found 1 put on Bit true
			}
		}
	}

	public String toString() {// Task 4.5
		String ans = "";
		for (int i = 0; i < bits.length; i = i + 1) {
			ans = ans + bits[i].toString();
		}
		ans = reverse(NumericalString.binary2Decimal(ans));// calling another class that contain the methods
															// that giving the String on base 10
		return ans;
	}

	public static String reverse(String s) {// function that we solve and explain it on the lectures
		String output;
		if (s.length() == 0)
			output = s;
		else
			output = reverse(s.substring(1)) + s.charAt(0);
		return output;
	}

}
