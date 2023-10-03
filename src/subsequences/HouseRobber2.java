package subsequences;

import java.util.Arrays;

public class HouseRobber2 {
	/*
	 * Mr. X is a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money hidden. All houses along this street are
	 * arranged in a circle. That means the first house is the neighbor of the last
	 * one. Meanwhile, adjacent houses have a security system connected, and it will
	 * automatically contact the police if two adjacent houses were broken into on
	 * the same night.
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,1,3};
		System.out.println(robUsingMemoization(arr));
		System.out.println(robUsingTabular(arr));
	}

	public static int robUsingMemoization(int[] nums) {
		if (nums.length == 1)
			return nums[0];
		int[] dp = new int[nums.length + 1];
		int[] arr1 = new int[nums.length];
		int[] arr2 = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i != 0)
				arr1[i] = nums[i];
			if (i != nums.length - 1)
				arr2[i] = nums[i];
		}
		Arrays.fill(dp, -1);
		int sol1 = solUsingMemoization(arr1, arr1.length - 1, dp);
		Arrays.fill(dp, -1);
		return Math.max(sol1, solUsingMemoization(arr2, arr2.length - 1, dp));

	}

	// Recursion implementation Time: O(n) Space : O(2^n)
	int solUsingRecursion(int[] num, int ind) {
		if (ind == 0)
			return num[ind];
		if (ind < 0)
			return 0;

		int pick = num[ind] + solUsingRecursion(num, ind - 2);
		int notPick = solUsingRecursion(num, ind - 1);
		return Math.max(pick, notPick);
	}

	// Memoization implementation Time: O(n) Space : O(n)+ hash memory 
	static int solUsingMemoization(int[] num, int ind, int[] dp) {
		if (ind == 0)
			return num[ind];
		if (ind < 0)
			return 0;
		if (dp[ind] != -1)
			return dp[ind];

		int pick = num[ind] + solUsingMemoization(num, ind - 2, dp);
		int notPick = solUsingMemoization(num, ind - 1, dp);
		return dp[ind] = Math.max(pick, notPick);
	}

	public static int robUsingTabular(int[] nums) {
		if (nums.length == 1)
			return nums[0];

		int[] arr1 = new int[nums.length];
		int[] arr2 = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i != 0)
				arr1[i] = nums[i];
			if (i != nums.length - 1)
				arr2[i] = nums[i];
		}

		// return Math.max(solUsingTabular(arr1),solUsingTabular(arr2));

		return Math.max(solTabularOptimized(arr1), solTabularOptimized(arr2));

	}

	// Tabular implement Time: O(n) Space : O(n) 
	public int solUsingTabular(int[] nums) {

		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++)
			dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
		return dp[nums.length - 1];
	}

	// Tabular optimized sol Time: O(n) Space : O(1)
	static int solTabularOptimized(int[] num) {
		int prev = num[0], prev1 = 0;
		for (int i = 1; i < num.length; i++) {
			int pick = num[i];
			if (i > 1)
				pick += prev1;
			prev1 = prev;
			prev = Math.max(pick, prev1);
		}
		return prev;
	}

}
