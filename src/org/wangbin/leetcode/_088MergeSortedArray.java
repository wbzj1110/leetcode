package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 * 
 * Note: You may assume that nums1 has enough space (size that is greater or
 * equal to m + n) to hold additional elements from nums2. The number of
 * elements initialized in nums1 and nums2 are m and n respectively.
 * 
 * @author wb
 * @date 2015-8-19 ����8:58:45
 */
public class _088MergeSortedArray {
	public static void main(String[] args) {
		int[] ints1 = new int[] { 1, 2, 3, 6, 0, 0, 0, 0 };
		int[] ints2 = new int[] { 4, 5, 7, 8 };
		new _088MergeSortedArray().merge(ints1, 4, ints2, 4);
		Arrays.toString(ints1);
	}

	/**
	 * ��˼·���Ƚ�nums1��nums2��Ԫ�أ����ǴӺ�߱Ƚϸ��ã���Ϊ�������µ����鲻�ò���λ�á� 
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums1 == null || nums2 == null) {
			return;
		}
		int i = m - 1;
		int j = n - 1;
		int index = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] > nums2[j]) {
				nums1[index--] = nums1[i--];
			} else {
				nums1[index--] = nums2[j--];
			}
		}

		while (i >= 0) {
			nums1[index--] = nums1[i--];
		}
		while (j >= 0) {
			nums1[index--] = nums2[j--];
		}

	}
}