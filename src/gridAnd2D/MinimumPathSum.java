package gridAnd2D;

import java.util.Arrays;

public class MinimumPathSum {
	/*
	 * Ninjaland is a country in the shape of a 2-Dimensional grid 'GRID', with 'N'
	 * rows and 'M' columns. Each point in the grid has some cost associated with
	 * it.
	 * 
	 * Find a path from top left i.e. (0, 0) to the bottom right i.e. ('N' - 1, 'M'
	 * - 1) which minimizes the sum of the cost of all the numbers along the path.
	 * You need to tell the minimum sum of that path.
	 * 
	 * You can only move down or right at any point in time.
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = { { 10, 8, 2 }, { 10, 50, 80 }, { 1, 1, 2 } };

		System.out.println(solRecursion(grid, grid.length - 1, grid[0].length - 1));
		int[][] dp = new int[grid.length][grid[0].length];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		System.out.println(solMemoizatio(grid, grid.length - 1, grid[0].length - 1, dp));
		System.out.println(solTabulartion(grid, grid.length - 1, grid[0].length - 1));
		System.out.println(solOptimization(grid, grid.length - 1, grid[0].length - 1));
	}

	/*
	 * 1. express (i,j) 2. explore all paths 3. take the min path
	 * 
	 */
	// Recursion Time O(2^(N*M)) Space O(N*M)+Stack memory O((M-1)+(N-1))
	static int solRecursion(int[][] grid, int m, int n) {
		if (m == 0 && n == 0)
			return grid[0][0];
		if (m < 0 || n < 0)
			return (int) Math.pow(10, 9);// Integer.MAX_VALUE;//so that it doesn't select
		int up = grid[m][n] + solRecursion(grid, m - 1, n);
		int left = grid[m][n] + solRecursion(grid, m, n - 1);
		return Math.min(up, left);
	}

	// Memoizatio Time O(N*M) Space O(N*M)+Stack memory O((M-1)+(N-1))
	static int solMemoizatio(int[][] grid, int m, int n, int[][] dp) {
		if (m == 0 && n == 0)
			return grid[0][0];
		if (m < 0 || n < 0)
			return (int) Math.pow(10, 9);// Integer.MAX_VALUE;//so that it doesn't select
		if (dp[m][n] != -1)
			return dp[m][n];
		int up = grid[m][n] + solMemoizatio(grid, m - 1, n, dp);
		int left = grid[m][n] + solMemoizatio(grid, m, n - 1, dp);
		return dp[m][n] = Math.min(up, left);
	}

	// Tabulartion Time O(N*M) Space O(N*M)
	static int solTabulartion(int[][] grid, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {

				if (i == 0 && j == 0)
					dp[0][0] = grid[0][0];
				else {
					int up = grid[i][j], left = grid[i][j];
					if (i > 0)
						up += dp[i - 1][j];
					else
						up += (int) Math.pow(10, 9);
					if (j > 0)
						left += dp[i][j - 1];
					else
						left += (int) Math.pow(10, 9);
					dp[i][j] = Math.min(up, left);
				}
			}
		}

		return dp[m][n];
	}

	// Optimization Time O(N*M) Space O(N)
	static int solOptimization(int[][] matrix ,int m, int n ) {
		// Initialize an array to store the previous row values
		int prev[] = new int[m+1];

		for (int i = 0; i <= n; i++) {
			// Create a temporary array to store the current row values
			int temp[] = new int[m+1];

			for (int j = 0; j <= m; j++) {
				if (i == 0 && j == 0)
					temp[j] = matrix[i][j]; // If we're at the top-left cell, the minimum sum is its value
				else {
					int up = matrix[i][j];
					if (i > 0)
						up += prev[j]; // Add the value from above if it's not out of bounds
					else
						up += (int) Math.pow(10, 9); // Add a large value if out of bounds in the up direction

					int left = matrix[i][j];
					if (j > 0)
						left += temp[j - 1]; // Add the value from the left if it's not out of bounds
					else
						left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

					// Store the minimum of the two possible paths in the current cell
					temp[j] = Math.min(up, left);
				}
			}
			// Update the previous row with the values of the current row
			prev = temp;
		}

		// The final result is stored in the last element of the previous row
		return prev[m ];
	}
}
