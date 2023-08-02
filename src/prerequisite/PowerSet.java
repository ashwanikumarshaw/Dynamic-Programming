package prerequisite;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

	/*
	 * 
	 * Set  = [a,b,c]
power_set_size = pow(2, 3) = 8
Run for binary counter = 000 to 111

Value of Counter            Subset
   000                    -> Empty set
   001                    -> a
   010                    -> b
   011                    -> ab
   100                    -> c
   101                    -> ac
   110                    -> bc
   111                    -> abc
   
	 */
	//Recursion on Subsequences
	public static void main(String[] args) {
		int[] arr= {3,2,4};
		int n=3;
		List<Integer> list = new ArrayList<>();
		subsequences(0,arr,list,n);
		System.out.println();
		list = new ArrayList<>();
		subsequencesReverse(0,arr,list,n);
	}
	
	static void subsequences(int cur,int[] arr,List<Integer> list,int n) {
		//base case : 
		if(cur==n) {
			System.out.println();
			for(int item:list) {
				System.out.print(item);
			}
			if(list.size()==0)System.out.print("{}");
			return;
		}
		//with cur item
		list.add(arr[cur]);
		subsequences(cur+1,arr,list,n);
		list.remove(list.size()-1);
		//without cur item
		subsequences(cur+1,arr,list,n);
	}
	
	static void subsequencesReverse(int cur,int[] arr,List<Integer> list,int n) {
		//base case : 
		if(cur==n) {
			System.out.println();
			for(int item:list) {
				System.out.print(item);
			}
			if(list.size()==0)System.out.print("{}");
			return;
		}
		//without cur item
		subsequencesReverse(cur+1,arr,list,n);
		//with cur item
		list.add(arr[cur]);
		subsequencesReverse(cur+1,arr,list,n);
		list.remove(list.size()-1);
		
	}
}
