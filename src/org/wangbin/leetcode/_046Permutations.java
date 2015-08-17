package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
 * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * @author wb
 * @date 2015-8-8 上午11:45:43
 */
public class _046Permutations {
	public static void main(String[] args) {
		int []ints = new int[]{1,2,3};
		Util.showListInListInteger(new _046Permutations().permute(ints));
	}
	/**
	 * 先来个递归
	 * @param num
	 * @return
	 */
	public List<List<Integer>> permute(int[] num) {
		int len = 10;
		if(num.length > 3){
			//已经大于list的初始化的值了,结果容量为num.length!
			int temp = 1;
			for(int i = num.length;i >=1;i--){
				temp *=i;
			}
			len = temp;
		}
		
		//初始化大小的时候，可以试试num.length的阶乘。看看对时间有没有影响
		List<List<Integer>> result = new ArrayList<List<Integer>>(len);
		List<Integer> item = new ArrayList<Integer>(num.length);
		boolean []visisted = new boolean[num.length];
		next_permute(num,result,item,visisted);
		return result;
	}
	/**
	 * 递归过程
	 * @param num 
	 * @param result
	 * @param item
	 * @param visisted
	 */
	private void next_permute(int[] num, List<List<Integer>> result, List<Integer> item,
			boolean[] visisted) {
		// TODO Auto-generated method stub
		if(item.size()==visisted.length){
			result.add(new ArrayList<Integer>(item));
			return;
		}
		for(int i = 0;i <visisted.length;i++){
			if(!visisted[i]){//没有被访问过
				visisted[i]=true;
				item.add(num[i]);
				next_permute(num, result, item, visisted);
				item.remove(item.size()-1);
				visisted[i]=false;
			}
		}
	}
	
	
}
