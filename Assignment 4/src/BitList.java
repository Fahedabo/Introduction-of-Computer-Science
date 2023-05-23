import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
	private int numberOfOnes;
	

	// Do not change the constructor
	public BitList() {
		numberOfOnes = 0;
	}

	// Do not change the method
	public int getNumberOfOnes() {
		return numberOfOnes;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1
	// ================================================

	public void addLast(Bit element) {
		if ((element == null)) {
			throw new IllegalArgumentException("input argument is null");
		}
		if (element.toInt() == 1) {
			numberOfOnes = numberOfOnes + 1;
		}
		super.add(element);// calling the father that contain the method
	}

	public void addFirst(Bit element) {
		if ((element == null)) {
			throw new IllegalArgumentException("input argument is null");
		}
		if (element.toInt() == 1) {
			numberOfOnes = numberOfOnes + 1;// if we found the element 1 add one to the option
		}
		super.addFirst(element);// also calling the methods that are in linkedlist

	}

	public Bit removeLast() {
		Bit value = super.removeLast();// calling the father that contain the method
		if (value.toInt() == 1) {
			numberOfOnes = numberOfOnes - 1;// if we remove the last and was 1 abstract the options for the number 1
		}

		return value;
	}

	public Bit removeFirst() {
		Bit value = super.removeFirst();// calling the father that contain the method
		if (value.toInt() == 1) {
			numberOfOnes = numberOfOnes - 1;// if we remove the first and was 1 abstract the options for the number 1
		}

		return value;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2
	// ================================================
	public String toString() {
		String output = ">";
		Iterator<Bit> numbers = super.iterator();// calling the iterator that gives us a elements

		while (numbers.hasNext()) {// while there are element
			output = numbers.next() + output;
		}
		output = "<" + output;
		return output;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3
	// ================================================
	public BitList(BitList other) {
		if ((other == null)) {
			throw new IllegalArgumentException("input argument is null");
		}

		for (Bit numbers : other) {// copy the elements after that add the next elements
			addLast(numbers);
		}
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4
	// ================================================
	public boolean isNumber() {
		boolean ans;
		if (super.size() == 0) {// checking legal input according the definition
			ans = false;
		} else if (super.size() > 1 & super.getLast().toInt() == 1 & numberOfOnes == 1) {
			ans = false;

		} else if (super.size() >= 1 | (numberOfOnes > 1 & super.getLast().toInt() == 0)) {

			ans = true;

		} else

			ans = false;

		return ans;

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5
	// ================================================
	public boolean isReduced() {
		boolean answer = false;
		if (isNumber()) {// IF is legal number interning to do the conditions
			if (((super.size() == 1 && super.getFirst().toInt() == 0))// double & in order to not fall the project
					|| (super.size() == 2 && super.getFirst().toInt() == 1)// condition 1
					|| (super.size() == 2 & numberOfOnes == 2)) {
				answer = true;
			} else if (super.size() >= 3 && ((super.getLast().toInt() == 1 & (get(size() - 2).toInt() == 0))// condition
																											// 2
					| (super.getLast().toInt() == 0 & (get(size() - 2).toInt() == 1)))) {
				answer = true;
			} else if (super.size() >= 3
					&& numberOfOnes == 2 & super.getLast().toInt() == 1 & (get(size() - 2).toInt() == 1)) {// condition
																											// 3 that
																											// the link
																											// has twice
																											// 1 in the
																											// left side
				answer = true;
			} else
				answer = false;

		}
		return answer;

	}

	public void reduce() {
		if (super.size() == 0 | !isNumber()) {// if illegal input
			throw new IllegalArgumentException("input argument is null");
		}

		boolean temp = isReduced();
		if (!temp) {
			Iterator<Bit> mylist = super.iterator();
			while (mylist.hasNext() & !temp) {// while there are element after that and the reduce illegal

				this.removeLast();
				temp = isReduced();

			}
		}

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6
	// ================================================
	public BitList complement() {
		BitList list = new BitList();
		Iterator<Bit> mylist = super.iterator();
		while (mylist.hasNext()) {// while i have some element
			if (mylist.next().toInt() == 1) {// if the number is 1 convert to 0
				list.addLast(Bit.ZERO);
			} else {// if the number 0 convert to 1
				list.addLast(Bit.ONE);
			}
		}
		return list;
	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7
	// ================================================
	public Bit shiftRight() {
		Bit Right;// to not use two return (just 1)
		Iterator<Bit> mylist = super.iterator();
		if (!mylist.hasNext()) {// if the length ==0 return null
			Right = null;

		} else {
			Right = removeFirst();// remove the first cell from the right

		}
		return Right;

	}

	public void shiftLeft() {
		super.addFirst(Bit.ZERO);// add 0 on the right side

	}

	// =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8
	// ================================================
	public void padding(int newLength) {
		if (size() == 0) {// we solve on the Tigbur
			throw new IllegalArgumentException("input argument is null");
		}
		while (newLength > size()) {
			addLast(getLast());// if the last number equals to 1 give me until the newLength the number 1
								// if the last number equals to 0 give me until the newLength the number 0
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	// The following overriding methods must not be changed.
	// ----------------------------------------------------------------------------------------------------------
	public boolean add(Bit e) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public void add(int index, Bit element) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public Bit remove(int index) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public boolean offer(Bit e) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public boolean offerFirst(Bit e) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public boolean offerLast(Bit e) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public Bit set(int index, Bit element) {
		throw new UnsupportedOperationException("Do not use this method!");
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Do not use this method!");
	}
}