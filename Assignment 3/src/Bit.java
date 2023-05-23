public class Bit {

	private boolean value;

	public Bit(boolean value) { // Task 4.1
		this.value = value;// filed

	}

	public int toInt() {// Task 4.2
		int ans = 0;
		if (this.value == true) {// constructors
			return 1;
		} else {
			return ans;
		}

	}

	public String toString() {// Task 4.3
		String ans = "0";
		if (this.value == true) {// constructors
			return "1";
		}

		return ans;
	}

}
