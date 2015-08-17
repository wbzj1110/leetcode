package org.wangbin.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * @author wb
 * @version 2015-7-25 下午1:00:43
 */
public class _004MedianofTwoSortedArrays {
	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 4, 5, 6 };
		System.out.println(findMedianSortedArrays(a, b));

	}

	/**
	 * 堆排序log(n)
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static double findMedianSortedArrays(int A[], int B[]) {
		int allLen = A.length + B.length;
		Queue<Integer> que = new PriorityQueue<Integer>(allLen);
		for (int i = 0, len = A.length; i < len; i++) {
			que.add(A[i]);
		}
		for (int i = 0, len = B.length; i < len; i++) {
			que.add(B[i]);
		}
		int[] C = new int[allLen];
		for (int i = 0; i < allLen; i++) {
			C[i] = que.poll();
		}
		if (allLen % 2 != 0) {
			return C[allLen / 2];
		} else {
			return (C[allLen / 2] + C[allLen / 2 - 1]) * 1.00 / 2;
		}
	}

	/**
	 * 解决此题的方法可以依照：寻找一个unioned sorted array中的第k大（从1开始数）的数。因而等价于寻找并判断两个sorted
	 * array中第k/2（从1开始数）大的数。
	 * 
	 * 特殊化到求median，那么对于奇数来说，就是求第(m+n)/2+1（从1开始数）大的数。
	 * 
	 * 而对于偶数来说，就是求第(m+n)/2大（从1开始数）和第(m+n)/2+1大（从1开始数）的数的算术平均值。
	 * 
	 * 那么如何判断两个有序数组A,B中第k大的数呢？
	 * 
	 * 我们需要判断A[k/2-1]和B[k/2-1]的大小。
	 * 
	 * 如果A[k/2-1]==B[k/2-1]，那么这个数就是两个数组中第k大的数。
	 * 
	 * 如果A[k/2-1]<B[k/2-1],
	 * 那么说明A[0]到A[k/2-1]都不可能是第k大的数，所以需要舍弃这一半，继续从A[k/2]到A[A.length
	 * -1]继续找。当然，因为这里舍弃了A[0]到A[k/2-1]这k/2个数，那么第k大也就变成了，第k-k/2个大的数了。
	 * 
	 * 如果 A[k/2-1]>B[k/2-1]，就做之前对称的操作就好。
	 * 
	 * 这样整个问题就迎刃而解了。
	 * 
	 * 当然，边界条件页不能少，需要判断是否有一个数组长度为0，以及k==1时候的情况。
	 * 
	 * 因为除法是向下取整，并且页为了方便起见，对每个数组的分半操作采取：
	 * 
	 * int partA = Math.min(k/2,m); int partB = k - partA;
	 * 
	 * 为了能保证上面的分半操作正确，需要保证A数组的长度小于B数组的长度。
	 * 
	 * 同时，在返回结果时候，注意精度问题，返回double型的就好。
	 * 很明显思想是那么个事，写起来太麻烦
	 * @param A
	 * @param B
	 * @return
	 */

	public static double findMedianSortedArrays2(int A[], int B[]) {
		int len = A.length + B.length;
		if (len % 2 == 0) {
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0,
					len / 2 + 1)) / 2.0;
		} else {
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
	}

	// find kth number of two sorted array
	private static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
		if (A_start >= A.length)
			return B[B_start + k - 1];
		if (B_start >= B.length)
			return A[A_start + k - 1];

		if (k == 1)
			return Math.min(A[A_start], B[B_start]);

		int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1]
				: Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1]
				: Integer.MAX_VALUE;

		if (A_key < B_key) {
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
		}
	}
}
