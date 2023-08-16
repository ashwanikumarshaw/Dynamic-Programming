package fibonacce;

public class FrogJump {
	/*
	 * There is a frog on the '1st' step of an 'N' stairs long staircase. The frog
	 * wants to reach the 'Nth' stair. 'HEIGHT[i]' is the height of the '(i+1)th'
	 * stair.If Frog jumps from 'ith' to 'jth' stair, the energy lost in the jump is
	 * given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ). If the Frog is on
	 * 'ith' staircase, he can jump either to '(i+1)th' stair or to '(i+2)th' stair.
	 * Your task is to find the minimum total energy used by the frog to reach from
	 * '1st' stair to 'Nth' stair.
	 */
	public static void main(String[] args) {
		
		int H[]= {10,2,20,40,100,10};
		int n=H.length-1;
		int dp[]=new int[H.length];
		for(int i=0;i<=n;i++)dp[i]=-1;
		//recursion
		System.out.println(
				recu_fun(n,H)
				);
		//memoize
		System.out.println(
				memo_fun(n,H,dp)
				);
		//tabulation
		System.out.println(
				tabu_fun(n,H)
				);
		//optimize
		System.out.println(
				opti_fun(n,H)
				);
	}



	private static int recu_fun(int n, int[] h) {
		if(n==0) return 0;
		int fs=recu_fun(n-1,h)+Math.abs(h[n]-h[n-1]);
		int ss=Integer.MAX_VALUE;
		if(n>1)
			ss=recu_fun(n-2,h)+Math.abs(h[n]-h[n-2]);
		return Math.min(fs, ss);
		//TC O(2^n)
		//SC O(n)
	}

	private static int memo_fun(int n, int[] h, int[] dp) {
		if(n==0) return 0;
		if(dp[n]!=-1)return dp[n];
		int fs=recu_fun(n-1,h)+Math.abs(h[n]-h[n-1]);
		int ss=Integer.MAX_VALUE;
		if(n>1)
			ss=recu_fun(n-2,h)+Math.abs(h[n]-h[n-2]);
		return dp[n]=Math.min(fs, ss);
		//TC O(n)
		//SC O(n+n)
	}

	
	private static int tabu_fun(int n, int[] h) {
		if(n==0) return 0;
		int[] dp=new int[n+1];
		dp[0]=0;
		for(int i=1;i<=n;i++) {
		int fs=dp[i-1]+Math.abs(h[i]-h[i-1]);
		int ss=Integer.MAX_VALUE;
		if(i>1)
			ss=dp[i-2]+Math.abs(h[i]-h[i-2]);
		dp[i]= Math.min(fs, ss);
		}
		return dp[n];
		//TC O(n)
		//SC O(n)
	}
	
	private static int opti_fun(int n, int[] h) {
		if(n==0) return 0;
		int cur=0,prev1=0,prev2=0;
		for(int i=1;i<=n;i++) {
		int fs=prev1+Math.abs(h[i]-h[i-1]);
		int ss=Integer.MAX_VALUE;
		if(i>1)
			ss=prev2+Math.abs(h[i]-h[i-2]);
		cur= Math.min(fs, ss);
		prev2=prev1;
		prev1=cur;
		}
		return cur;
		//TC O(n)
		//SC O(1)
	}

}
