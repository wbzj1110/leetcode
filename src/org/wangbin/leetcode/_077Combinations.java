package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 * 
 * For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 * @author wb
 * @date 2015-8-15 下午12:36:22
 */
public class _077Combinations {
	public static void main(String[] args) {
		int n = 4;
		int k = 2;
		Util.showListInListInteger(new _077Combinations().combine(n, k));
	}
	/**
	 * k个的组合就是k-1的组合加上额外的那个数字,所以递归喽
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		if(n <k){
			return null;
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int []ints = new int[n];
		for(int i = 0;i <n;i++){
			ints[i] = i+1;
		}
		List<Integer> item = new ArrayList<Integer>(k);
		StringBuilder sb = new StringBuilder();
		helper(k,0,ints,sb,item,result);
		
		return result;
	}

	private void helper(int target, int start, int[] ints, StringBuilder sb,
			List<Integer> item, List<List<Integer>> result) {
		// TODO Auto-generated method stub
		if(item.size()==target){
			result.add(new ArrayList<Integer>(item));
			return;
		}
		for(int i = start;i <ints.length;i++){
			item.add(ints[i]);
			helper(target, i+1, ints, sb, item, result);
			item.remove(item.size()-1);
		}
	}
}
