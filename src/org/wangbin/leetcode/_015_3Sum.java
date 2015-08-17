package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤
 * b ≤ c) The solution set must not contain duplicate triplets. For example,
 * given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is: (-1, 0, 1) (-1, -1, 2)
 * 
 * @author wb
 * @version 2015-7-29 上午8:38:49
 */
public class _015_3Sum {
	public static void main(String[] args) {
		int[] ints = new int[] { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> result = new _015_3Sum().threeSum(ints);
		for (List<Integer> ls : result) {
			for (Integer i : ls) {
				System.out.print(i + "    ");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> threeSum(int[] num) {
		if (num == null || num.length < 3) {
			return Collections.EMPTY_LIST;
		}
		Arrays.sort(num);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0, len = num.length; i < len - 1; i++) {
			if (i != 0 && num[i] == num[i - 1]) {
				continue;// 排除重复的,例如0,0,0,0
			}
			int left = i + 1;
			int right = len - 1;
			while (left < right) {
				int sum = num[i] + num[left] + num[right];
				if (sum == 0) {
					List<Integer> ls = new ArrayList<Integer>(3);
					ls.add(num[i]);
					ls.add(num[left]);
					ls.add(num[right]);
					result.add(ls);
					left++;
					right--;
					while (left < right && num[left] == num[left - 1]) {
						left++;
					}
					while (left < right && num[right] == num[right + 1]) {
						right--;
					}
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}

		}

		return result;
	}
}
