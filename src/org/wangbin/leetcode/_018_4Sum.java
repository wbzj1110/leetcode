package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 * 
 * Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order.
 * (ie, a ¡Ü b ¡Ü c ¡Ü d) The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 * A solution set is: (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2)
 * 
 * @author wb
 * @version 2015-7-30 ÉÏÎç9:06:44
 */
public class _018_4Sum {
	public static void main(String[] args) {
//		int []ints = new int []{1 ,0 ,-1, 0, -2, 2};
		int []ints = new int []{-3,-2,-1,0,0,1,2,3};
		 List<List<Integer>> result = new _018_4Sum().fourSum(ints, 0);
		 for(List<Integer> ls :result){
			 for(Integer i :ls){
				 System.out.print(i + "~~~");
			 }
			 System.out.println();
		 }
	}
	public List<List<Integer>> fourSum(int[] num, int target) {
		if(num==null||num.length<4){
			return Collections.EMPTY_LIST;
		}
		Arrays.sort(num);
		int sum = 0;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashSet<List<Integer>> set = new HashSet<List<Integer>>();
		List<Integer>temp = null;
		for(int i =0,len = num.length;i < num.length;i++){
			for(int j = i+1;j <len;j++){
				int left = j+1;
				int right = len -1;
				while(left<right ){
//					System.out.println("i :" +i + "~~~~j :" + j + "~~~left :" + left + "~~~right:" + right );
					sum = num[i] + num[j] + num[left] + num[right];
					if(sum<target){
						left++;
					}else if(sum>target){
						right--;
					}else{
						temp = new ArrayList<Integer>(4);
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[left]);
						temp.add(num[right]);
						if(!set.contains(temp)){
							set.add(temp);
							result.add(temp);
						}
						left++;
						right--;
					}
				}
			}
		}
		
		return result;
	}
	
}
