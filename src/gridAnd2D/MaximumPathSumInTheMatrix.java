package gridAnd2D;

import java.util.Arrays;

public class MaximumPathSumInTheMatrix {
	/*
	 * You have been given an N*M matrix filled with integer numbers, find the
	 * maximum sum that can be obtained from a path starting from any cell in the
	 * first row to any cell in the last row. From a cell in a row, you can move to
	 * another cell directly below that row, or diagonally below left or right. So
	 * from a particular cell (row, col), we can move in three directions i.e.
	 * 
	 * Down: (row+1,col) 
	 * Down left diagonal: (row+1,col-1) 
	 * Down right diagonal:(row+1, col+1)
	 * 
	 */
	public static void main(String[] args) {
		int matrix[][] = { 
				{ 1, 2, 10, 4 }, 
				{ 100, 3, 2, 1 }, 
				{ 1, 1, 20, 2 }, 
				{ 1, 2, 2, 1 } 
				};

		System.out.println(solRecursion(matrix));

		
		System.out.println(solMemoizatio( matrix));

		System.out.println(solTabulartion(matrix));

		System.out.println(solOptimization(matrix));
	}

	private static int solRecursion(int[][] matrix) {

		int m = matrix.length, n = matrix[0].length;
		// recursion
		int sol = 0;
		for (int i = 0; i < n; i++)
			sol = Math.max(sol, recursion(matrix, m - 1, i));
		return sol;
	}

	private static int recursion(int[][] matrix, int m, int n) {
		// index
		if (n < 0 || n > matrix[0].length - 1)
			return Integer.MIN_VALUE;
		if (m == 0)
			return matrix[m][n];

		// do all suff
		int u = matrix[m][n] + recursion(matrix, m - 1, n);
		int ul = matrix[m][n] + recursion(matrix, m - 1, n - 1);
		int ur = matrix[m][n] + recursion(matrix, m - 1, n + 1);
		// sum of all : max
		return Math.max(u, Math.max(ul, ur));
	}
	private static int solMemoizatio(int[][] matrix) {

		int m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		int sol = 0;
		for (int i = 0; i < n; i++)
			sol = Math.max(sol, memoizatio(dp,matrix, m - 1, i));
		return sol;
	}
	private static int memoizatio(int[][] dp, int[][] matrix, int m, int n) {
		// index
		if (n < 0 || n > matrix[0].length - 1)
			return Integer.MIN_VALUE;
		if (m == 0)
			return matrix[m][n];
		//check prev solved
		if(dp[m][n]!=-1)return dp[m][n];
		// do all suff
		int u = matrix[m][n] + recursion(matrix, m - 1, n);
		int ul = matrix[m][n] + recursion(matrix, m - 1, n - 1);
		int ur = matrix[m][n] + recursion(matrix, m - 1, n + 1);
		// sum of all : max : store for future
		return dp[m][n]=Math.max(u, Math.max(ul, ur));
	}

	private static int solTabulartion(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		//init dp
		int[][] dp = new int[m][n];
		//make base case as stating point
		for(int i=0;i<n;i++)dp[0][i]=matrix[0][i];
		//change recursion to loop
		for(int i=1;i<m;i++) {//0 ind covered in base case
			for(int j=0;j<n;j++) {
				
				//copy recursion and change f(i,j)->dp[i][j]
				int down=matrix[i][j]+dp[i-1][j];
				
				int downleft=matrix[i][j];
				if(j-1>=0)downleft+=dp[i-1][j-1];
				else downleft+=Integer.MIN_VALUE;
				
				int downright=matrix[i][j];
				if(j+1<n)downright+=dp[i-1][j+1];
				else downright+=Integer.MIN_VALUE;
				
				dp[i][j]=Math.max(down, Math.max(downleft, downright));
				
			}
		}
		int max=0;
		for(int i=0;i<n;i++)max=Math.max(max, dp[m-1][i]);
		
		return max;
	}

	private static int solOptimization(int[][] matrix) {
		int m=matrix.length, n=matrix[0].length;
		int[][] dp=new int[m][n];

		int max=Integer.MIN_VALUE;

		for(int j=0;j<n;j++)dp[0][j]=matrix[0][j];
		
		for(int i=1;i<m;i++){
			for(int j=0;j<n;j++){
				{
					int d=0,dl=matrix[i][j],dr=matrix[i][j];
					d=matrix[i][j] + dp[i - 1][j];

					if(j-1>=0) dl+=dp[i-1][j-1];
					else dl+= (int) Math.pow(-10, 9);

					if(j+1<n) dr+=dp[i-1][j+1];
					else dr+= (int) Math.pow(-10, 9);

					dp[i][j]=Math.max(d,Math.max(dl,dr));
			
				}

			}
		}
		for(int j=0;j<n;j++)max=Math.max(max, dp[m-1][j]);
		return max;
	}

}
