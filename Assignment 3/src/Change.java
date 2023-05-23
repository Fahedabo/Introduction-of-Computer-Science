class Change {

	public static boolean change(int[] coins, int n) {// Task 2.1
		boolean ans = false;
		ans = (change2(coins, n, 0));
		return ans;
	}

	public static boolean change2(int[] coins, int n, int i) {// helper function
		boolean ans = false;
		if (n == 0) {// stop condition
			ans = true;
		} else if (n < 0 | i >= coins.length) {// return false on illegal condition
			ans = false;
		}

		else {
			/// recursion method to check the remainder in all the statuses
			ans = change2(coins, n - coins[i], i) || change2(coins, n, i + 1);// with and without n.

		}
		return ans;
	}

	public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse) {// Task 2.2
		boolean ans = false;
		ans = changeLimited(coins, n, 0, numOfCoinsToUse);
		return ans;
	}

	public static boolean changeLimited(int[] coins, int n, int i, int numOfCoinsToUse) {// helper function
		boolean ans = false;
		if (n == 0 & numOfCoinsToUse >= 0) {// stop condition
			ans = true;
		} else if (n < 0 | numOfCoinsToUse < 0 | i >= coins.length) {// return false on illegal condition
			ans = false;
		} else {
			// recursion looks like which in previous function but if we find that there are
			// option we abstract the numOfCoinsToUse by 1.
			ans = changeLimited(coins, n - coins[i], i, numOfCoinsToUse - 1)
					|| changeLimited(coins, n, i + 1, numOfCoinsToUse);
			// with and without the the the divider of n.
		}
		return ans;
	}

	public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse) {// Task 2.3
		printChangeLimited(coins, n, 0, numOfCoinsToUse, "");// sending to function with stop condition.
	}

	public static boolean printChangeLimited(int[] coins, int n, int i, int numOfCoinsToUse, String acc) {// helper
		boolean ans = false;/// this diffinition to stop the searching more when we found one solution

		if (n == 0 & numOfCoinsToUse >= 0) {
			System.out.println(acc.substring(1));// printing 1 option if we found according to the question "or".
			ans = true;
		} else if (n < 0 | numOfCoinsToUse < 0 | i >= coins.length) {
			ans = false;
		} else {
			// recursion that passing on all the situations and printing the options.
			ans = printChangeLimited(coins, n - coins[i], i, numOfCoinsToUse - 1, acc + "," + coins[i])
					|| printChangeLimited(coins, n, i + 1, numOfCoinsToUse, acc);
		}
		return ans;
	}

	public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse) {// Task 2.4
		int ans = 0;
		ans = countChangeLimited(coins, n, 0, numOfCoinsToUse);
		return ans;
	}

	public static int countChangeLimited(int[] coins, int n, int i, int numOfCoinsToUse) {// helper function
		int count = 0;
		if (n == 0 & numOfCoinsToUse >= 0) {// if we arrive to this status counter=+1;
			count = count + 1;
		} else if (n < 0 | numOfCoinsToUse < 0 | i >= coins.length) {// return 0 on illegal condition
			count = 0;
		} else {
			// add all the options that we got n==0 or at most numOfCoinsToUse together.
			count = countChangeLimited(coins, n - coins[i], i, numOfCoinsToUse - 1)
					+ countChangeLimited(coins, n, i + 1, numOfCoinsToUse);
		}
		return count;
	}

	public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse) {// Task 2.5
		printAllChangeLimited(coins, n, 0, numOfCoinsToUse, "");
	}

// same thing that what in 2.3 just here we don't need to stop searching. we want to print all the options
	public static void printAllChangeLimited(int[] coins, int n, int i, int numOfCoinsToUse, String acc) {// helper
		if (n == 0 & numOfCoinsToUse >= 0) {
			System.out.println(acc.substring(1));// printing all the options from first until the end.
		} else if (n < 0 | numOfCoinsToUse < 0 | i >= coins.length) {

		} else {
			// recursion that passing on all the situations and printing the options.
			printChangeLimited(coins, n - coins[i], i, numOfCoinsToUse - 1, acc + "," + coins[i]);
			printChangeLimited(coins, n, i + 1, numOfCoinsToUse, acc);

		}
	}

}
