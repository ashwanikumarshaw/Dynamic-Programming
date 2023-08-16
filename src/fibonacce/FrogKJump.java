package fibonacce;

public class FrogKJump {

	public static void main(String[] args) {
		int H[] = { 10, 2, 20, 40, 100, 10 };
		int n = H.length - 1;
		int k = 2;
		int dp[] = new int[H.length];
		for (int i = 0; i <= n; i++)
			dp[i] = -1;
		// recursion
		System.out.println(recu_fun(n, H, k));
		// memoize
		System.out.println(memo_fun(n, H, k, dp));
		// tabulation
		System.out.println(tabu_fun(n, H, k));
		// optimize
//		System.out.println(opti_fun(n, H, k));
	}

	private static int recu_fun(int n, int[] h, int k) {
		if (n == 0)
			return 0;

		int min = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			if (n > j - 1) {
				int ss = recu_fun(n - j, h, k) + Math.abs(h[n] - h[n - j]);
				min = Math.min(min, ss);
			}
		}
		return min;
		// TC O(2^n*k)
		// SC O(n)
	}

	private static int memo_fun(int n, int[] h, int k, int[] dp) {
		if (n == 0)
			return 0;
		if (dp[n] != -1)
			return dp[n];
		int min = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			if (n > j - 1) {
			int ss = memo_fun(n - j, h, k, dp) + Math.abs(h[n] - h[n - j]);
			min = Math.min(min, ss);
			}
		}
		return min;
		// TC O(n*k)
		// SC O(n+n)
	}

	private static int tabu_fun(int n, int[] h, int k) {
		if (n == 0)
			return 0;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= k; j++) {
				if (i > j - 1) {
				int ss = dp[i - j] + Math.abs(h[i] - h[i - j]);
				min = Math.min(min, ss);
				}
			dp[i] =min;
			}
		}
		return dp[n];
		// TC O(n*k)
		// SC O(n)
	}
//space optimization can't be possible
//	private static int opti_fun(int n, int[] h, int k) {
//		if (n == 0)
//			return 0;
//		int cur = 0, prev1 = 0, prev2 = 0;
//		for (int i = 1; i <= n; i++) {
//			int fs = prev1 + Math.abs(h[i] - h[i - 1]);
//			int ss = Integer.MAX_VALUE;
//			if (i > 1)
//				ss = prev2 + Math.abs(h[i] - h[i - 2]);
//			cur = Math.min(fs, ss);
//			prev2 = prev1;
//			prev1 = cur;
//		}
//		return cur;
//		// TC O(n)
//		// SC O(1)
//	}

}
