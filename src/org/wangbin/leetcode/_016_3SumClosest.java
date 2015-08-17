package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers. You
 * may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * @author wb
 * @version 2015-7-29 ÉÏÎç9:39:21
 */
public class _016_3SumClosest {
	public static void main(String[] args) {
		int[] ints = new int[] { -3, -2, -5, 3, -4 };
		System.out.println(new _016_3SumClosest().threeSumClosest(ints, -1));
	}

	public int threeSumClosest(int[] num, int target) {
		if (num == null || num.length < 3) {
			return Integer.MAX_VALUE;
		}
		Arrays.sort(num);
		int closet = Integer.MAX_VALUE / 2;
		for (int i = 0, len = num.length; i < len; i++) {
			int left = i + 1;
			int right = len - 1;
			while (left < right) {
				int sum = num[i] + num[left] + num[right];
				if (sum == target) {
					return target;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
				closet = Math.abs(sum - target) < Math.abs(closet - target) ? sum
						: closet;
			}

		}

		return closet;
	}

}
