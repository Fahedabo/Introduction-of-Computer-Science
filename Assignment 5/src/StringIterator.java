import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringIterator implements Iterator<String> {

	private String MyAlphabet;// my field
	private String a;
	
	// Complete the following methods
	public StringIterator(String start) {
		this.MyAlphabet = start;// Constructor
		this.a=start;
		

	}

	public boolean hasNext() {
		return MyAlphabet.length() < 32;// while current we have a next char in the string
	}

	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		MyAlphabet=a;
		return  nexthelp(); 
	}

	private String nexthelp() {
		String next = "";
		if (a == "") {// first condition
			next = "a";// add the first letter on alphabet
			this.a = next;
			return "";
		}
		// this types to make the code more easier
		String CutStr = a.substring(0, a.length() - 1);
		char LastCh = a.charAt(a.length() - 1);

		if (LastCh == 'z') {// condition 2
			next = CutStr + "A";// add the cutting string to the char that we changed

			this.a = next;

			return MyAlphabet;
		} else if (LastCh == 'Z') {// 3
			if (a.length() > 1) {// if my alphabet contains more than char
				StringIterator s = new StringIterator(CutStr);
				next = s.next() + "a";// add another char if it was capital z

				this.a = next;
				return MyAlphabet;
			} else {
				next = CutStr + "aa";// add double a because we arrive to Z

				this.a = next;

				return MyAlphabet;
			}
		} else if (LastCh >= 'a' && LastCh < 'z') {// condition 4 that the letter between a-z
			boolean hasChanged = false;
			for (char i = 'a'; i < 'z' && !hasChanged; i++) {// if the last char between a-z without z
				if (LastCh == i) {// which letter equals the last char
					char ch = (char) (i + 1);// casting the another char that after the mention letter
					next = CutStr + ch;// add the cutting string to the char that we changed
					hasChanged = true;// if it has changed that's it
				}

			}
			this.a = next;

			return MyAlphabet;
		} else if (LastCh >= 'A' && LastCh < 'Z') {// condition 5 that the letter between A-Z
			boolean hasChanged = false;
			for (char i = 'A'; i < 'Z' && !hasChanged; i++) {// if the last char between A-Z without Z
				if (LastCh == i) {// which letter equals the last char
					char ch = (char) (i + 1);// casting the another char that after the mention letter
					next = CutStr + ch;// add the cutting string to the char that we changed
					hasChanged = true;// if it has changed that's it
				}

			}
			this.a = next;

			return MyAlphabet;
		}

		return MyAlphabet;
	}



}
