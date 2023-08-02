package prerequisite;

import java.util.ArrayList;
import java.util.List;

public class SubsequencesSum {
	
	//All Kind of Patterns in Recursion
	public static void main(String[] args) {
		int[] arr= {1,2,1,3};
		int n=4;
		int sum=2;
		List<Integer> list=new ArrayList<>();
		sumSubsequences(arr,n,0,sum,0,list);
	}
	
	static void sumSubsequences(int[] arr,int n, int cur, int sum,int curSum,List<Integer> list) {
		if(cur==n) {
			if(curSum==sum) {
			for(int item:list)
				System.out.print(item+" ,");
			System.out.println();
			}
			return;
		}
		list.add(arr[cur]);
		curSum+=arr[cur];
		//pick
		sumSubsequences(arr,n,cur+1,sum,curSum,list);
		
		list.remove(list.size()-1);
		curSum-=arr[cur];
		//not pick
		sumSubsequences(arr,n,cur+1,sum,curSum,list);
	}
}
