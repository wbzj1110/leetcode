package org.wangbin.leetcode;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.
 * 没啥难度已经排好序了。。
 * @author wb
 * @version 2015-8-1 下午8:21:55
 */
public class _026RemoveDuplicatesfromSortedArray {
	public static void main(String[] args) {
		int[] ints = new int[] { 1, 1, 2 };
		System.out.println(new _026RemoveDuplicatesfromSortedArray()
				.removeDuplicates(ints));
	}

	public int removeDuplicates(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return 1;
		}
		int length = A.length;
		int sum = 0;
		for (int i = 1; i < length; i++) {
			if (A[i] != A[i - 1]) {
				sum++;
				A[sum] = A[i];
			}

		}

		sum++;
		return sum;
	}

}
