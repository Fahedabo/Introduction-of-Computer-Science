public class FilterByBalance implements Filter<BankAccount> {
	private int balanceThreshold;

	public FilterByBalance(int balanceThreshold) { // task 5c
	   
		this.balanceThreshold = balanceThreshold;
	    
	}

	@Override
public boolean accept(BankAccount elem) {// task 5c
		if (elem != null && elem.getBalance() >= balanceThreshold) {
			// checking the balance between the elem and balance....
			return true;
		}

		else
			return false;

	}
}

