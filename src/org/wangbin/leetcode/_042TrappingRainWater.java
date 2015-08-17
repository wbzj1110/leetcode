package org.wangbin.leetcode;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * @author wb
 * @version 2015-8-7 上午8:36:38
 */
public class _042TrappingRainWater {
	public static void main(String[] args) {
		int []ints = new int []{0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(new _042TrappingRainWater().trap(ints));
	}

	/**
	 * 参考：低调小一（http://blog.csdn.net/wzy_1988/article/details/17752809）的解题思路
	 * 
	 * “首先，碰到这样的题目不要慌张，挨个分析每个A[i]能trapped water的容量，然后将所有的A[i]的trapped
	 * water容量相加即可
	 * 
	 * 其次，对于每个A[i]能trapped water的容量，取决于A[i]左右两边的高度（可延展）较小值与A[i]的差值，即volume[i] =
	 * [min(left[i], right[i]) - A[i]] * 1，这里的1是宽度，如果the width of each bar is
	 * 2,那就要乘以2了”
	 * 
	 * 
	 * 那么如何求A[i]的左右高度呢？
	 * 要知道，能盛多少水主要看短板。那么对每个A[i]来说，要求一个最高的左短板，再求一个最高的右短板，这两个直接最短的板子减去A
	 * [i]原有的值就是能成多少水了。
	 * 
	 * 
	 * 
	 * 所以需要两遍遍历，一个从左到右，找最高的左短板；一个从右到左，找最高的右短板。最后记录下盛水量的总值就是最终结果了。
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] A) {
		if (A == null || A.length == 0)
			return 0;

		int i, max, total = 0;
		int left[] = new int[A.length];
		int right[] = new int[A.length];

		// from left to right
		left[0] = A[0];
		max = A[0];
		for (i = 1; i < A.length; i++) {
			left[i] = Math.max(max, A[i]);
			max = Math.max(max, A[i]);
		}

		// from right to left
		right[A.length - 1] = A[A.length - 1];
		max = A[A.length - 1];
		for (i = A.length - 2; i >= 0; i--) {
			right[i] = Math.max(max, A[i]);
			max = Math.max(max, A[i]);
		}

		// trapped water (when i==0, it cannot trapped any water)
		for (i = 1; i < A.length - 1; i++) {
			System.out.println(left[i]+ "~~" +right[i] + "  A[i]:" + A[i]);
			int bit = Math.min(left[i], right[i]) - A[i];
			if (bit > 0)
				total += bit;
		}

		return total;
	}

}
