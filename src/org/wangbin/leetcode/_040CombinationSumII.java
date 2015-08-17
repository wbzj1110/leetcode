package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note: All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, �� , ak) must be in non-descending order. (ie, a1 �� a2 ��
 * �� �� ak). The solution set must not contain duplicate combinations. For
 * example, given candidate set 10,1,2,7,6,1,5 and target 8, A solution set is:
 * [1, 7] [1, 2, 5] [2, 6] [1, 1, 6] ��39һ��~����,���ǲ���ʹ���ظ��Ĳ���
 * 
 * @author wb
 * @version 2015-8-5 ����8:29:15
 */
public class _040CombinationSumII {
	public static void main(String[] args) {
		int[] candidates = new int[] { 10, 1, 2, 7, 6, 1, 5 };
		// candidates = new int[] { 1,1,2,5,7 };
		int target = 8;
		List<List<Integer>> ls = new _040CombinationSumII().combinationSum2(
				candidates, target);
		for (List<Integer> l : ls) {
			for (Integer i : l) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) {
			return Collections.EMPTY_LIST;
		}

		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> item = new ArrayList<Integer>();

		Arrays.sort(candidates);
		Map<Integer, Integer> mapAll = new HashMap<Integer, Integer>();
		for (Integer i : candidates) {
			if (mapAll.containsKey(i)) {
				mapAll.put(i, mapAll.get(i) + 1);
			} else {
				mapAll.put(i, 1);
			}
		}
		Map<Integer, Integer> mapNow = new HashMap<Integer, Integer>();
		helper(candidates, target, 0, item, res, mapNow, mapAll);
		return res;

	}

	private void helper(int[] candidates, int target, int start,
			List<Integer> item, List<List<Integer>> res,
			Map<Integer, Integer> mapNow, Map<Integer, Integer> mapAll) {
		// TODO Auto-generated method stub
		if (target < 0) {
			return;
		}
		if (target == 0) {
			// ����ط�ע���¿�����û��res����Ѿ����ظ���item��
			eitherAdd(res, new ArrayList<Integer>(item));
			return;
		}
		for (int i = start, len = candidates.length; i < len; i++) {
			// ÿ������ֻ�ܱ�ʹ��һ��,���ǿ�����2��һ�������ְ�
			if (mapNow.containsKey(candidates[i])) {
				int frequency = mapNow.get(candidates[i]);
				if (frequency >= mapAll.get(candidates[i])) {
					continue;
				} else {
					mapNow.put(candidates[i], frequency + 1);
				}
			} else {
				mapNow.put(candidates[i], 1);

			}
			item.add(candidates[i]);
			int newtarget = target - candidates[i];
			start = i + 1;
			helper(candidates, newtarget, start, item, res, mapNow, mapAll);
			if (item.size() > 0) {
				int toDel = item.get(item.size() - 1);
				if (mapNow.containsKey(toDel)) {
					int frequency = mapNow.get(toDel);
					if (frequency > 1) {
						mapNow.put(toDel, frequency - 1);
					} else if (frequency == 1) {
						mapNow.remove(toDel);
					}
				}
				item.remove(item.size() - 1);

			}
		}
	}

	private void eitherAdd(List<List<Integer>> res, ArrayList<Integer> arrayList) {
		// TODO Auto-generated method stub
		boolean result = true;
		for (List<Integer> temp : res) {
			if (temp.size() == arrayList.size()) {
				if (temp.equals(arrayList)) {
					result = false;
					break;
				}
			}
		}
		if (result) {
			res.add(arrayList);
		}
	}
	/**
	 * �±ߵķ������죬�����ڷ������ս��֮ǰ�ж� ������combination sum���ʵĲ����ǵ�ǰ�Ѿ���������Ԫ��ֻ�ܳ���һ�Ρ�
	 * 
	 * ������Ҫ��ÿ��candidateһ��visited������ʶ�Ƿ��Ѿ�visited�ˡ�
	 * 
	 * �����˵�ʱ�򣬼ǵ�Ҫ��visitedһ��Ҳ�����ˡ�
	 */
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {  
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> item = new ArrayList<Integer>();
        if(candidates == null || candidates.length==0)  
            return res; 
            
        Arrays.sort(candidates);  
        boolean [] visited = new boolean[candidates.length];
        helper(candidates,target, 0, item ,res, visited);  
        return res;  
    }  
    
    private static void helper(int[] candidates, int target, int start, List<Integer> item,   
    List<List<Integer>> res, boolean[] visited){  
        if(target<0)  
            return;  
        if(target==0){  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }
        
        for(int i=start;i<candidates.length;i++){
            if(!visited[i]){
                if(i>0 && candidates[i] == candidates[i-1] && visited[i-1]==false)//deal with dupicate
                    continue;  
                item.add(candidates[i]);
                visited[i]=true;
                int newtarget = target - candidates[i];
                helper(candidates,newtarget,i+1,item,res,visited);  
                visited[i]=false;
                item.remove(item.size()-1);  
            }
        }  
    }

}
