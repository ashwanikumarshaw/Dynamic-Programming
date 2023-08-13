package fibonacce;

public class Fibon {

	public static void main(String[] args) {
		int n=8;
		int dp[]=new int[n+1];
		for(int i=0;i<=n;i++)dp[i]=-1;
		//recursion
		System.out.println(
				recu_fun(n)
				);
		//memoize
		System.out.println(
				memo_fun(n,dp)
				);
		//tabulation
		System.out.println(
				tabu_fun(n)
				);
		//optimize
		System.out.println(
				opti_fun(n)
				);

	}


	private static int recu_fun(int n) {
		//base case
		if(n<=1)return n;
		return recu_fun(n-1)+recu_fun(n-2);
		//TC O(2^n)
		//SC O(n)
	}


	private static int memo_fun(int n, int[] dp) {
		if(n<=1) return n;
		if(dp[n]!=-1)return dp[n];
		return dp[n]=memo_fun(n-1,dp)+memo_fun(n-2,dp);
		//TC O(n)
		//SC O(n+n)= O(n)
	}

	private static int tabu_fun(int n) {
		if(n<=1)return n;
		int[] dp=new int[n+1];
		dp[0]=0;
		dp[1]=1;
		for(int i=2;i<=n;i++)
			dp[i]=dp[i-1]+dp[i-2];
		return dp[n];
		//TC O(n)
		//SC O(n)
	}

	private static int opti_fun(int n) {
		
		int prev2=0;
		int prev1=1;
		int cur=0;
		for(int i=2;i<=n;i++) {
			cur=prev2+prev1;
			prev2=prev1;
			prev1=cur;
		}
		return cur;
		//TC O(n)
		//SC O(1)
	}
}
