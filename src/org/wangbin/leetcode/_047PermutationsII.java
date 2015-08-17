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
 * @date 2015-8-8 ����12:05:05
 */
public class _047PermutationsII {
	public static void main(String[] args) {
		int []ints = new int[]{1,1,2};
		Util.showListInListInteger(new _047PermutationsII().permuteUnique(ints));
		
	}
	public List<List<Integer>> permuteUnique(int[] num) {
		int len = 10;
		if(num.length > 3){
			//�Ѿ�����list�ĳ�ʼ����ֵ��,�������Ϊnum.length!
			int temp = 1;
			for(int i = num.length;i >=1;i--){
				temp *=i;
			}
			len = temp;
		}
		//Ϊ��ȥ�أ���������ǰ����
		Arrays.sort(num);
		//��ʼ����С��ʱ�򣬿�������num.length�Ľ׳ˡ�������ʱ����û��Ӱ��
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
			//ȥ�ؿ�����һ����Ԫ�أ�ע�����û���ʹ�����ȥ��
			if(i >0 &&(num[i] ==num[i-1]) && !visisted[i-1]){
				continue;
			}
			if(!visisted[i]){//û�б����ʹ�
				visisted[i]=true;
				item.add(num[i]);
				next_permute(num, result, item, visisted);
				item.remove(item.size()-1);
				visisted[i]=false;
			}
		}
	}
}
