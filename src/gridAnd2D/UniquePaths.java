package gridAnd2D;

import java.util.Arrays;

public class UniquePaths {
	/*
	 * There is a robot on an m x n grid. The robot is initially located at the
	 * top-left corner (i.e., grid[0][0]). The robot tries to move to the
	 * bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
	 * either down or right at any point in time.
	 * 
	 * Given the two integers m and n, return the number of possible unique paths
	 * that the robot can take to reach the bottom-right corner.
	 * 
	 * The test cases are generated so that the answer will be less than or equal to
	 * 2 * 109.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int m = 3;
		int n = 7;
		System.out.println(solRecursion(m - 1, n - 1));// 0 indexing

		int dp[][] = new int[m][n];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		System.out.println(solMemoizantion(m - 1, n - 1, dp));// 0 indexing

		System.out.println(solTabulartion(m, n));//

		System.out.println(solOptimized(m, n));//

	}

	// Recursion Time O(2^(m*n) ) Space O(m+n)+O(m*n)
	static int solRecursion(int m, int n) {
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;
		int up = solRecursion(m - 1, n);
		int left = solRecursion(m, n - 1);

		return up + left;
	}

	// Memoizantion Time O(m*n) Space O(m+n)+O(m*n)
	static int solMemoizantion(int m, int n, int[][] dp) {
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;
		if (dp[m][n] != -1)
			return dp[m][n];
		int up = solMemoizantion(m - 1, n, dp);
		int left = solMemoizantion(m, n - 1, dp);

		return dp[m][n] = up + left;
	}

	// Tabulartion Time O(m*n) Space O(m*n)
	static int solTabulartion(int m, int n) {
		int dp[][] = new int[m][n];
		dp[0][0] = 1;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				int up = 0, left = 0;
//				if(m<0||n<0)return 0;
				if (i > 0)
					up = dp[i - 1][j];
				if (j > 0)
					left = dp[i][j - 1];
				dp[i][j] = up + left;
			}
		}

		return dp[m - 1][n - 1];
	}

	// Optimized Time O(m*n) Space O(n)
	static int solOptimized(int m, int n) {
		int[] dp = new int[n];
		for (int i = 0; i < m; i++) {
			int[] temp = new int[n];
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					dp[i] = 1;
					temp[i] = 1;
				} else {
					if (j > 0)
						temp[j] = temp[j - 1];
					temp[j] += dp[j];
				}
			}
			dp = temp;
		}
		return dp[n - 1];
	}

}
