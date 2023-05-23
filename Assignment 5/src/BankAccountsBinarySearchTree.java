import java.util.Comparator;
import java.util.Iterator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount> {

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}

	// Complete the following methods
	public void balance() {
		// task 5b
		LinkedList<BankAccount> list = new LinkedList<BankAccount>();
		BinaryTreeInOrderIterator<BankAccount> InorderInsert = new BinaryTreeInOrderIterator<BankAccount>(root);// sending
																												// to
																												// inorder
																												// class

		// Iterator<BankAccount> iter=new iterator<BankAccount>();
		while (InorderInsert.hasNext()) {// if there are next to insert
			list.add(InorderInsert.next());// add elements to the list that inserted in inorder class
			this.root = null;// began the root with null
		}
		// System.out.println(list.toString());
		buildBalancedTree(list, 0, list.size() - 1);// sending for help from the recursion function
	}

	private void buildBalancedTree(List<BankAccount> list, int low, int high) {// task 5b
		if (low > high) {
			return;
		}
		int NewRoot = ((((high + low) + 1)) / 2);// getting the root On InOrder
		super.insert(list.get(NewRoot));// inserting the new dividing list that i should working with it
		buildBalancedTree(list, low, NewRoot - 1);// left side of root
		buildBalancedTree(list, NewRoot + 1, high);// right side of root

	}
}
