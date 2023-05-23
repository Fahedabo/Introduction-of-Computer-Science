import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber> {
	private static final BinaryNumber ZERO = new BinaryNumber(0);
	private static final BinaryNumber ONE = new BinaryNumber(1);
	private BitList bits;

	// Copy constructor
	// Do not change this constructor
	public BinaryNumber(BinaryNumber number) {
		bits = new BitList(number.bits);
	}

	// Do not change this constructor
	private BinaryNumber(int i) {
		bits = new BitList();
		bits.addFirst(Bit.ZERO);
		if (i == 1)
			bits.addFirst(Bit.ONE);
		else if (i != 0)
			throw new IllegalArgumentException("This Constructor may only get either zero or one.");
	}

	// Do not change this method
	public int length() {
		return bits.size();
	}

	// Do not change this method
	public boolean isLegal() {
		return bits.isNumber() & bits.isReduced();
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1
	// ================================================
	public BinaryNumber(char c) {// we solve on the Tigbur
		// casting from decimal number to binary
		bits = new BitList();
		if ((c < '0' | c > '9')) {
			throw new IllegalArgumentException("This number isn't legal");
		}
		int Cvalue = c - '0';
		while (Cvalue > 0) {
			int digit = Cvalue % 2;
			bits.addLast(new Bit(digit));
			Cvalue = Cvalue / 2;
		}
		bits.addLast(Bit.ZERO);
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2
	// ================================================
	public String toString() {
		// Do not remove or change the next two lines
		if (!isLegal()) // Do not change this line
			throw new RuntimeException("I am illegal.");// Do not change this line

		String a = bits.toString();
		return a.substring(1, a.length() - 1);// printing the string without the first and last char the (><).

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3
	// ================================================
	public boolean equals(Object other) {

		boolean answer = true;
		if (!(other instanceof BinaryNumber)) {// if other aren't the same type of this
			answer = false;
		}

		// casting object type to BN

		else {
			if (((BinaryNumber) other).bits.size() != this.bits.size()) {// IF THE SIZE NOT EQUAL THERE ARE NO REASON
				// TO COUNTINE THE CODE
				answer = false;
			}

			for (int i = 0; i < this.bits.size() & answer; i = i + 1) {
				if (this.bits.get(i).toInt() != ((BinaryNumber) other).bits.get(i).toInt()) {// checking if the values
																								// equals
					answer = false;
				}
			}

		}

		return answer;

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4
	// ================================================
	public BinaryNumber add(BinaryNumber addMe) {

		if (addMe == null)
			throw new IllegalArgumentException("I am null.");
		int addmelength = addMe.bits.size() + 1;// keep the length in virable because the length always changing.
		int thislength = this.bits.size() + 1;
		if (addMe.bits.size() > this.bits.size()) {// the length of my new list using padding method
			this.bits.padding(addmelength);
			addMe.bits.padding(addmelength);
		} else if (addMe.bits.size() < this.bits.size()) {
			this.bits.padding(thislength);
			addMe.bits.padding(thislength);

		} else {// if the length equal
			this.bits.padding(thislength);
			addMe.bits.padding(thislength);

		}
		BitList list = new BitList();
		Bit cin = Bit.ZERO;
		for (int i = 0; i < addMe.bits.size(); i = i + 1) {// loop that work to all the elements on the (this).
			list.addLast(Bit.fullAdderSum(this.bits.get(i), addMe.bits.get(i), cin));// sending to the sum and add the
																						// value
			cin = Bit.fullAdderCarry(this.bits.get(i), addMe.bits.get(i), cin);// take the carry on account

		} /// maybe the method get (i) not
		BinaryNumber addme = new BinaryNumber(ZERO);
		list.reduce();// REDUCE my list
		addme.bits = list;
		// reduce the value of the list

		this.bits.reduce();// return to the first value
		addMe.bits.reduce();// return to the first value

		return addme;

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5
	// ================================================
	public BinaryNumber negate() {
		BinaryNumber negatee = new BinaryNumber(ZERO);
		BitList negat = new BitList();

		negat = this.bits.complement();// reflects the number from 1 to 0 and from 0 to 1

		negatee.bits = negat;// convert from BitList to BinaryNumber

		return negatee.add(new BinaryNumber(ONE));// return the list with adding one

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6
	// ================================================
	public BinaryNumber subtract(BinaryNumber subtractMe) {
		if (subtractMe == null) {
			throw new IllegalArgumentException("I am null.");
		}
		BinaryNumber subtractme = subtractMe.negate();// make the substractme with negative value.

		return this.add(subtractme);// sending the value to addme with negative
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7
	// ================================================
	public int signum() {
		int signum = 0;
		if (this.bits.size() > 1) {// if the size wasn't bigger that 1 that says signum is 0
			if (this.bits.getLast().toInt() == 0) {// if the last number equals to 0 thats says the number is positive
				signum = 1;
			} else {// if the last number is equals to 1 is symbol to negative
				signum = -1;
			}
		}
		return signum;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8
	// ================================================
	public int compareTo(BinaryNumber other) {
		if (other == null) {
			throw new IllegalArgumentException("I am null.");
		}
		int output = this.subtract(other).signum();// using the methods that in the previous functions to make the
													// result easy
		return output;

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9
	// ================================================
	public int toInt() {
		// Do not remove or change the next two lines
		if (!isLegal()) { // Do not change this line
			throw new RuntimeException("I am illegal.");// Do not change this line
		}

		BinaryNumber convertnumber = new BinaryNumber(this);
		int value = 0;
		int power = 1;

		if (convertnumber.signum() == -1) {// if the number is positive convert it
			convertnumber = convertnumber.negate();
			power = power * -1;
			/// return the basic negative value with -

		}

		for (int i = 0; i < convertnumber.bits.size(); i = i + 1) {
			int binary = convertnumber.bits.get(i).toInt();
			if (binary == 1) {
				value = value + power;// double the value by 2 to convert from binary to int
			}
			power = power * 2;
			if ((value < 0 && this.signum() == 1) || (this.signum() == -1 && value > 0)) {// the conditions when the
																							// program pass max value on
																							// the positive and negative
				throw new RuntimeException(" illegal input");
			}

		}

		return value;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10
	// ================================================
	// Do not change this method
	public BinaryNumber multiply(BinaryNumber multiplyMe) {
		if (multiplyMe == null) {
			throw new IllegalArgumentException("I am null.");
		}
		BinaryNumber ans = new BinaryNumber('0');

		if (multiplyMe.signum() == -1 & this.signum() == 1) {// if there are one negative
			ans = multiplyPositive(multiplyMe.negate()).negate();
		} else if (this.signum() == -1 & multiplyMe.signum() == 1) {
			ans = this.negate().multiply(multiplyMe).negate();
		} else if (multiplyMe.signum() == -1 & this.signum() == -1) {// if the two numbers is negative the result should
																		// be negative
			ans = this.negate().multiplyPositive(multiplyMe.negate());
		} else {
			ans = multiplyPositive(multiplyMe);// if the two numbers was positive

		}
		ans.bits.reduce();
		return ans;

	}

	private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
		BinaryNumber thiss = new BinaryNumber(this);
		BinaryNumber multiplyme = new BinaryNumber(multiplyMe);
		BinaryNumber answer = new BinaryNumber('0');

		for (int i = 0; i < multiplyme.bits.size() - 1; i = i + 1) {
			if (multiplyme.bits.get(i).toInt() == 1) {
				answer = answer.add(thiss);// where we saw number 1 add the one to my list

			}
			thiss.bits.shiftLeft();// *2 i use this method because i face dificult on using multiplyBy2 function
									// that was given to us.
		}
		return answer;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11
	// ================================================
	// Do not change this method
	public BinaryNumber divide(BinaryNumber divisor) {
		if (divisor == null)
			throw new IllegalArgumentException("I am null.");
		// Do not remove or change the next two lines
		if (divisor.equals(ZERO)) // Do not change this line
			throw new RuntimeException("Cannot divide by zero."); // Do not change this line

		else {

			BinaryNumber ans = new BinaryNumber('0');
			if (divisor.signum() == -1 & this.signum() == 1) {// if there are one negative
				ans = dividePositive(divisor.negate()).negate();
			} else if (this.signum() == -1 & divisor.signum() == 1) {
				ans = this.negate().dividePositive(divisor).negate();
			} else if (divisor.signum() == -1 & this.signum() == -1) {// if the two numbers is negative the result
																		// should
																		// be negative
				ans = this.negate().dividePositive(divisor.negate());
			} else {
				ans = dividePositive(divisor);// if the two numbers was positive

			}
			ans.bits.reduce();
			return ans;
		}
	}

	private BinaryNumber dividePositive(BinaryNumber divisor) {

		BinaryNumber thiss = this;
		BinaryNumber diviser = divisor;
		BinaryNumber result = new BinaryNumber('0');
		BinaryNumber comparing = new BinaryNumber('0');
		for (int i = thiss.bits.size() - 1; i >= 0; i = i - 1) {
			comparing.bits.addFirst(thiss.bits.get(i));// add the last char and in all time we take the number after
			if (diviser.compareTo(comparing) == 1) {// if divisor bigger that the comparing
				result.bits.addFirst(Bit.ZERO);
			} else {
				comparing = comparing.subtract(diviser);// subtracting the value that not bigger that the divisor
				result.bits.addFirst(Bit.ONE);
			}
		}
		// return the basic values
		result.bits.reduce();
		comparing.bits.reduce();
		return result;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12
	// ================================================
	public BinaryNumber(String s) {
		if (s == null || (s.charAt(0) == '-' & s.length() == 1)) {
			throw new IllegalArgumentException("wrong input.");
		}
		BinaryNumber result = new BinaryNumber('0');

		BinaryNumber multi10 = new BinaryNumber('5').add(new BinaryNumber('5'));
		BinaryNumber power = new BinaryNumber('1');
		if (s.charAt(0) == '-') {// if the string that we take beginning with negative
			for (int i = s.length() - 1; i > 0; i = i - 1) {// not equals zero because we don't want the -
				BinaryNumber temp = new BinaryNumber(s.charAt(i));
				result = result.add(temp.multiply(power));// multiply the char and add it to the remainder of the string
				power = power.multiply(multi10);
			}
			result = result.negate();
			this.bits = result.bits;

		} else {
			for (int i = s.length() - 1; i >= 0; i = i - 1) {
				BinaryNumber temp = new BinaryNumber(s.charAt(i));// same comments that on previous condition
				result = result.add(temp.multiply(power));
				power = power.multiply(multi10);
			}
			this.bits = result.bits;// but the value on this to return it
		}

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13
	// ================================================
	public String toIntString() {
		// Do not remove or change the next two lines
		if (!isLegal()) // Do not change this line
			throw new RuntimeException("I am illegal.");// Do not change this line
		//

		BinaryNumber basicValue = new BinaryNumber(this);// convert the this to value that i want to use without
															// changing this value

		BinaryNumber tempValue = new BinaryNumber('1');
		BinaryNumber subtractAns = new BinaryNumber('0');// the value of the subtracting
		String result = "";
		BinaryNumber numTen = new BinaryNumber('5').multiplyBy2();// i chose to multiply the 5 by 2 because BN take char

		if (basicValue.signum() == -1) {// if the number is negative
			return result = "-" + basicValue.negate().toIntString();// adding the - to the first of the string
		}

		if (basicValue.equals(ZERO)) {
			result = 0 + result;
		} else {
			for (; !(basicValue.equals(ZERO)); basicValue = basicValue.divide(numTen)) {// while the basic value not
																						// become to 0 when i call him
				tempValue = new BinaryNumber(basicValue);

				tempValue = tempValue.divide(numTen);// divide by 10 to make the number smaller to use

				tempValue = tempValue.multiply(numTen);

				subtractAns = basicValue.subtract(tempValue);/// subtract the basicValue from the temporary value

				result = subtractAns.toInt() + result;

			}

		}

		return result;
	}

	// Returns this * 2
	public BinaryNumber multiplyBy2() {
		BinaryNumber output = new BinaryNumber(this);
		output.bits.shiftLeft();
		output.bits.reduce();
		return output;
	}

	// Returens this / 2;
	public BinaryNumber divideBy2() {
		BinaryNumber output = new BinaryNumber(this);
		if (!equals(ZERO)) {
			if (signum() == -1) {
				output.negate();
				output.bits.shiftRight();
				output.negate();
			} else
				output.bits.shiftRight();
		}
		return output;
	}



}