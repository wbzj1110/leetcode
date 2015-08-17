package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If nums = [1,2,3], a
 * solution is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * @author wb
 * @date 2015-8-15 下午12:55:59
 */
public class _078Subsets {
	public static void main(String[] args) {
		int []ints = new int[]{1,2,3};
		Util.showListInListInteger(new _078Subsets().subsets(ints));
		ints = new int[]{0};
		Util.showListInListInteger(new _078Subsets().subsets(ints));
		ints = new int[]{4,1,0};
		Util.showListInListInteger(new _078Subsets().subsets(ints));
	}
	/**
	 * 077Combinations的增强版，加个循环搞定
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		if(nums==null||nums.length==0){
			return Collections.EMPTY_LIST;
		}
		
		int k= nums.length;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> item = new ArrayList<Integer>(nums.length);
		result.add(new ArrayList<Integer>());
		Arrays.sort(nums);
		for(int i = 0;i <k;i++){
			helper(i+1,0,nums,result,item);
		}
		return result;
	}

	private void helper(int target, int start, int[] nums, List<List<Integer>> result,
			List<Integer> item) {
		// TODO Auto-generated method stub
		if(item.size()==target){
			//可以一开始让数组先排序了
//			List<Integer> temp = new ArrayList<Integer>(item);
//			Collections.sort(temp);
//			result.add(temp);
			result.add(new ArrayList<Integer>(item));
			return;
		}
		for(int i = start;i <nums.length;i++){
			item.add(nums[i]);
			helper(target, i+1, nums, result, item);
			item.remove(item.size()-1);
		}
	}
}
