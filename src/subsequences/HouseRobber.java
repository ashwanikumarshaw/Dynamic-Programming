package subsequences;

public class HouseRobber {
	/*
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping you
	 * from robbing each of them is that adjacent houses have security systems
	 * connected and it will automatically contact the police if two adjacent houses
	 * were broken into on the same night.
	 * 
	 * Given an integer array nums representing the amount of money of each house,
	 * return the maximum amount of money you can rob tonight without alerting the
	 * police.
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,1,3,8};
		System.out.println(rob(arr));
	}
	 public static int rob(int[] num) {
         int prev=num[0],prev1=0;
       for(int i=1;i<num.length;i++){
           int pick=num[i];
           if(i>1)pick+=prev1;
           prev1=prev;
           prev=Math.max(pick,prev1);
       }
       return prev;
    }
	 //for more details check HouseRobber2

}
