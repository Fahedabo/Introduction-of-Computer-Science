import java.math.BigInteger;
import java.util.Random;

class Assignment3BigInteger {
	public static void main(String[] argus) {
		System.out.println(randomPrime(7));
	}

	public static BigInteger sumSmaller(BigInteger n) {// Task 1.1

		BigInteger sum = new BigInteger("0");
		BigInteger Div = new BigInteger("2");

		if (n.signum() == 1) {// the method signum i take from the API. it means n>1.
			sum = (n.multiply(n.subtract(BigInteger.ONE))).divide(Div);/// n*(n-1)/2

		} else
			return sum;

		return sum;
	}

	public static void printRandoms(int n) {// Task 1.2

		Random rdm = new Random();
		// printing n Random number from the range of int type.
		for (int i = 0; i < n; i = i + 1) {
			System.out.println(rdm.nextInt());
		}
	}

	public static boolean isPrime(BigInteger n) {// Task 1.3

		boolean ans = true;
		if (n.equals(BigInteger.ONE) | n.equals(BigInteger.ZERO)) {// if the condition is zero or one
			ans = false;
		}
		BigInteger k = new BigInteger("2");
		BigInteger p = k.multiply(k);// checking k*k until the sqrt

		while (p.compareTo(n) <= 0 & ans) {// checking the condition and convert a BigInt to Int value

			if (n.mod(k).intValue() == 0) {//// checking the modulo of the BigInt
				ans = false;
			}
			k = k.add(BigInteger.ONE);// k+1
			p = k.multiply(k);// p=k*k
		}

		return ans;
	}

	public static BigInteger randomPrime(int n) {// Task 1.4

		BigInteger randBig = new BigInteger("0");
		Random rand = new Random();

		do {
			randBig = new BigInteger(n, rand);// do another lottery for new number
												// (2^n-1) is the maximum value
		} while (!isPrime(randBig));// sending to the previous function

		return randBig;
	}

}