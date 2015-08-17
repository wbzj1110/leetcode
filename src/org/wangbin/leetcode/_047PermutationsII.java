package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [1,1,2],
 * [1,2,1], and [2,1,1].
 * 
 * @author wb
 * @date 2015-8-8 下午12:05:05
 */
public class _047PermutationsII {
	public static void main(String[] args) {
		int []ints = new int[]{1,1,2};
		Util.showListInListInteger(new _047PermutationsII().permuteUnique(ints));
		
	}
	public List<List<Integer>> permuteUnique(int[] num) {
		int len = 10;
		if(num.length > 3){
			//已经大于list的初始化的值了,结果容量为num.length!
			int temp = 1;
			for(int i = num.length;i >=1;i--){
				temp *=i;
			}
			len = temp;
		}
		//为了去重，所以先提前排序
		Arrays.sort(num);
		//初始化大小的时候，可以试试num.length的阶乘。看看对时间有没有影响
		List<List<Integer>> result = new ArrayList<List<Integer>>(len);
		List<Integer> item = new ArrayList<Integer>(num.length);
		boolean []visisted = new boolean[num.length];
		next_permute(num,result,item,visisted);
		return result;
		
	}
	private void next_permute(int[] num, List<List<Integer>> result,
			List<Integer> item, boolean[] visisted) {
		// TODO Auto-generated method stub
		if(item.size()==visisted.length){
			result.add(new ArrayList<Integer>(item));
			return;
		}
		for(int i = 0;i <visisted.length;i++){
			//去重靠近的一样的元素，注意如果没访问过不用去重
			if(i >0 &&(num[i] ==num[i-1]) && !visisted[i-1]){
				continue;
			}
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
