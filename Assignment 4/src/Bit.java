public class Bit {

	private boolean value;
	public static final Bit ONE = new Bit(true);
	public static final Bit ZERO = new Bit(false);

	public Bit(boolean value) {
		this.value = value;
	}

	public Bit(int intValue) {
		if (intValue == 0)
			value = false;
		else {
			if (intValue == 1)
				value = true;
			else
				throw new IllegalArgumentException(value + " is neither 0 nor 1.");
		}
	}

	public String toString() {
		return "" + toInt();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bit))
			return false;
		else
			return value == ((Bit) obj).value;
	}

	public Bit negate() {
		Bit output;
		if (value)
			output = ZERO;
		else
			output = ONE;
		return output;
	}

	public int toInt() {
		int output;
		if (value)
			output = 1;
		else
			output = 0;
		return output;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 1.1
	// ================================================
	public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {

		int temp = bit1.toInt() + bit2.toInt() - 2 * (bit1.toInt() * bit2.toInt());// using Xor method to sum the
																					// "Hefrash Semetre" between A.B and
																					// AB.C
		int temp2 = (temp + bit3.toInt()) - 2 * (temp * bit3.toInt());// same comment below
		Bit TempxorC = new Bit(temp2);

		return TempxorC;
	}

	public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
		Bit carry = Bit.ZERO;
		if (bit1.toInt() + bit2.toInt() + bit3.toInt() > 1) {// if the sum > 1 returns 1 that in the hand else return 0
			carry = Bit.ONE;
		}
		return carry;

	}

}