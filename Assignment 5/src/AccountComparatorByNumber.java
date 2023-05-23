import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount> {

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2b
		if (account1.getAccountNumber() > account2.getAccountNumber())// if account 1 number bigger
			return 1;
		else if (account1.getAccountNumber() < account2.getAccountNumber())// if account 1 number smaller
			return -1;
		else {
			return 0;//if the two accounts equals
		}

	}

}
