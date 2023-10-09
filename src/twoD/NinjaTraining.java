package twoD;

import java.util.Arrays;

public class NinjaTraining {
	/*
	 * Ninja is planing this ‘N’ days-long training schedule. Each day, he can
	 * perform any one of these three activities. (Running, Fighting Practice or
	 * Learning New Moves). Each activity has some merit points on each day. As
	 * Ninja has to improve all his skills, he can’t do the same activity in two
	 * consecutive days. Can you help Ninja find out the maximum merit points Ninja
	 * can earn?
	 * 
	 * You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding
	 * to each day and activity. Your task is to calculate the maximum number of
	 * merit points that Ninja can earn.
	 * 
	 * Example: If the given ‘POINTS’ array is [ [1,2,5], [3 ,1 ,1] ,[3,3,3] ], the
	 * answer will be 11 as 5 + 3 + 3.
	 */
	public static void main(String[] args) {
		// Define the points for each activity on each day
		int[][] points = { { 10, 40, 70 }, { 20, 50, 80 }, { 30, 60, 90 } };

		int n = points.length; // Get the number of days
		System.out.println(solRecursion(n - 1, 3, points)); // Calculate and print the maximum points using Recursion

		// Initialize a memoization table with -1 values
		int dp[][] = new int[n][4];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		System.out.println(solMemoization(n - 1, 3, points, dp)); // Calculate and print the maximum points using
																	// Memoizatio
		System.out.println(solTabulartion(n, points));// Calculate and print the maximum points using Tabulartion

		System.out.println(solOptimization(n, points));
	}

	// Recursion Space O(N^2) Time O(2^N)
	public static int solRecursion(int day, int last, int[][] points) {
		if (day == 0) {
			int maxi = 0;
			for (int task = 0; task < 3; task++) {
				if (last != task)
					maxi = Math.max(maxi, points[day][task]);
			}
			return maxi;
		}

		int maxi = 0;
		for (int task = 0; task < 3; task++) {
			if (last != task) {
				int point = points[day][task] + solRecursion(day - 1, task, points);
				maxi = Math.max(maxi, point);
			}
		}
		return maxi;
	}

	// Memoizatio Space O(N)+O(N*4) Time O(N*4)*3
	public static int solMemoization(int day, int last, int[][] points, int[][] dp) {
		if (day == 0) {
			int maxi = 0;
			for (int task = 0; task < 3; task++) {
				if (last != task)
					maxi = Math.max(maxi, points[day][task]);
			}
			return maxi;
		}
		if (dp[day][last] != -1)
			return dp[day][last];

		int maxi = 0;
		for (int task = 0; task < 3; task++) {
			if (last != task) {
				int point = points[day][task] + solMemoization(day - 1, task, points, dp);
				maxi = Math.max(maxi, point);
			}
		}
		return dp[day][last] = maxi;
	}

	// Tabulartion Space O(N*4) Time O(N*4)*3
	public static int solTabulartion(int n, int[][] points) {
		int dp[][] = new int[n][4];
		dp[0][0] = Math.max(points[0][1], points[0][2]);
		dp[0][1] = Math.max(points[0][0], points[0][2]);
		dp[0][2] = Math.max(points[0][0], points[0][1]);
		dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		for (int day = 1; day < n; day++) {
			for (int last = 0; last < 4; last++) {
				dp[day][last] = 0;
				for (int task = 0; task < 3; task++) {
					if (last != task) {
						int point = points[day][task] + dp[day - 1][task];
						dp[day][last] = Math.max(dp[day][last], point);
					}
				}
			}
		}
		return dp[n - 1][3];
	}

	//Space Optimization Space O(4) Time O(N*4)*3
	public static int solOptimization(int n, int[][] points) {
		// Initialize an array 'prev' to store the maximum points for the previous day
		int prev[] = new int[4];

		// Initialize the first day's maximum points based on the available choices
		prev[0] = Math.max(points[0][1], points[0][2]);
		prev[1] = Math.max(points[0][0], points[0][2]);
		prev[2] = Math.max(points[0][0], points[0][1]);
		prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		// Iterate through each day starting from the second day
		for (int day = 1; day < n; day++) {
			// Initialize an array 'temp' to store the maximum points for the current day
			int temp[] = new int[4];
			for (int last = 0; last < 4; last++) {
				temp[last] = 0; // Initialize the maximum points for the current day and last activity
				// Consider each possible task for the current day
				for (int task = 0; task <= 2; task++) {
					if (task != last) { // Ensure that the current task is different from the last
						// Calculate the points for the current activity and add it to the maximum
						// points from the previous day
						temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
					}
				}
			}
			// Update 'prev' to store the maximum points for the current day
			prev = temp;

		}
		return prev[3];
	}

}
