public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;

	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name) {
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero
		// balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount) namesTree.findData(lookFor);
	}

	public BankAccount lookUp(int accountNumber) {
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber, 0);
		return (BankAccount) accountNumbersTree.findData(lookFor);
	}

	// END OF Given code -----------------------------------

	// Complete the methods from here on

	public boolean add(BankAccount newAccount) {// task 6a
		//boolean answer = false;
		// Iterator<BankAccount> aIterator= this.iterator;
		boolean containName=namesTree.contains(newAccount);
		boolean containAcocountNum=accountNumbersTree.contains(newAccount);
		if ((containName==true||containAcocountNum )) {// if the New
																							// account doesn't
																							// in the tree
			return false;
		}
		else {
			namesTree.insert(newAccount);// add the new account to the two trees
			accountNumbersTree.insert(newAccount);
			return true;
		}
		
	}

	public boolean delete(String name) {
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:
		boolean answer = false;
		// task 6b
		if (toRemove != null) {// if there are an name in the tree
			namesTree.remove(toRemove);// remove it from the first tree
			 accountNumbersTree.remove(toRemove);// remove it from the second tree
			answer = true;
		}
		return answer;
	}

	public boolean delete(int accountNumber) {
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:
		boolean answer = false;// same code that in 6b
		// task 6c
		if (toRemove != null) {// if there are an name in the tree
			 namesTree.remove(toRemove);// remove it from the first tree
			accountNumbersTree.remove(toRemove);// remove it from the second tree
			answer = true;
		}
		return answer;
	}

	public boolean depositMoney(int amount, int accountNumber) {
		// task 6d
		boolean done = false;
		BankAccount SameAccount = lookUp(accountNumber);// bringing the order account
		if (SameAccount != null) {// there are sameaccount
			SameAccount.depositMoney(amount);// add the amount to the order account
			done = true;
		}

		return done;

	}

	public boolean withdrawMoney(int amount, int accountNumber) {
		// task 6e
		boolean done = false;// approximately same code that on the previous question
		BankAccount SameAccount = lookUp(accountNumber);
		if (SameAccount != null) {
			done=SameAccount.withdrawMoney(amount);// subtract the amount
			//done = true;
		}

		return done;

	}

	public boolean transferMoney(int amount, int accountNumber1, int accountNumber2) {
		// task 6f
		boolean transfer = false;
		if (lookUp(accountNumber1).getBalance() >= amount) {// if the balance of account1 bigger than the amount
			lookUp(accountNumber1).withdrawMoney(amount);// reduce the amount from account1
			lookUp(accountNumber2).depositMoney(amount);// add the amount to the account2
			transfer = true;
		}
		return transfer;
	}

	public boolean transferMoney(int amount, int accountNumber, String name) {
		// task 6g
		boolean transfer = false;
		if (lookUp(accountNumber).getBalance() >= amount) {// same previous code
			lookUp(accountNumber).withdrawMoney(amount);
			lookUp(name).depositMoney(amount);// transfer the amount to the name of the account
			transfer = true;
		}
		return transfer;
	}
}
