package org.wangbin.leetcode;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note: All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
 * … ≤ ak). The solution set must not contain duplicate combinations. For
 * example, given candidate set 2,3,6,7 and target 7, A solution set is: [7] [2,
 * 2, 3]
 * 
 * @author wb
 * @version 2015-8-4 上午9:49:56
 */
public class _039CombinationSum {
	public static void main(String[] args) {
		int[] candidates = new int[] { 1, 2, 3, 6, 7 };
		candidates = new int[]{ 10,1,2,7,6,1,5 };
		int target = 8;
		List<List<Integer>> ls = new _039CombinationSum().combinationSum(
				candidates, target);
		for (List<Integer> l : ls) {
			for (Integer i : l) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}

		// ArrayList<ArrayList<Integer>> ls = new
		// _038CombinationSum().combinationSum2(
		// candidates, target);
		// for (List<Integer> l : ls) {
		// for (Integer i : l) {
		// System.out.print(i + "  ");
		// }
		// System.out.println();
		// }

	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) {
			return Collections.EMPTY_LIST;
		}

		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> item = new ArrayList<Integer>();

		Arrays.sort(candidates);
		helper(candidates, target, 0, item, res);
		return res;
	}

	private void helper(int[] candidates, int target, int start,
			List<Integer> item, List<List<Integer>> res) {
		if (target < 0) {
			return;
		}
		if (target == 0) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		for (int i = start, len = candidates.length; i < len; i++) {
			//过滤重复的数值 
			if (i > 0 && (candidates[i] == candidates[i - 1])) {
				continue;
			}
			item.add(candidates[i]);
			int newtarget = target - candidates[i];
			start = i;
			helper(candidates, newtarget, start, item, res);
			item.remove(item.size() - 1);
		}

	}

	public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (candidates == null) {
			return result;
		}

		ArrayList<Integer> path = new ArrayList<Integer>();
		Arrays.sort(candidates);
		helper(candidates, target, path, 0, result);

		return result;
	}

	private void helper(int[] candidates, int target, ArrayList<Integer> path,
			int index, ArrayList<ArrayList<Integer>> result) {
		if (target == 0) {
			result.add(new ArrayList<Integer>(path));
			return;
		}

		int prev = -1;
		for (int i = index; i < candidates.length; i++) {
			if (candidates[i] > target) {
				break;
			}

			if (prev != -1 && prev == candidates[i]) {
				continue;
			}

			path.add(candidates[i]);
			helper(candidates, target - candidates[i], path, i, result);
			path.remove(path.size() - 1);

			prev = candidates[i];
		}
	}

}
