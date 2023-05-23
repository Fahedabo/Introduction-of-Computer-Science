import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
	private Filter<BankAccount> filter;
	private BankAccount current;

	public FilteredBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, Filter<BankAccount> filter) { // task																											// 5c
		this.filter = filter;
		this.iterator = (BinaryTreeInOrderIterator<BankAccount>) bankAccountsTree.iterator();/*----------------*/
	boolean Thsoultion = false;
		BankAccount temp=current;
		while (iterator.hasNext() && !Thsoultion) {
		    	temp = iterator.next();
			if (filter.accept(temp)) {
				//this.current = temp;
				Thsoultion = true;
			}
			if (!Thsoultion)
				temp = null;
		}
		current=temp;
			
			
		}
		// this.current = iterator.next();

	

	// Complete the following method
	@Override
	public boolean hasNext() {// task 5c
		return current != null;
	}

	// Complete the following method
	@Override
	public BankAccount next() { // task 5c
		if (!hasNext()) {
			throw new NoSuchElementException("there are no BankAccount");
		}
		BankAccount next = current;
		boolean Thsoultion = false;
		//BankAccount temp = iterator.next();
		while (iterator.hasNext() && !Thsoultion) {
		    current = iterator.next();
			if (filter.accept(current)) {
			//	this.current = temp;
				Thsoultion = true;
			}
			if (!Thsoultion)
				current= null;
		}
			
		
		return next;
	}
}
