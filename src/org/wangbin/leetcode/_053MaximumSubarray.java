package org.wangbin.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray
 * [4,−1,2,1] has the largest sum = 6.
 * 
 * More practice: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 * 
 * @author wb
 * @date 2015-8-8 下午2:50:05
 */
public class _053MaximumSubarray {
	public static void main(String[] args) {
		int[] ints = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(new _053MaximumSubarray().maxSubArray(ints));
	}

	public int maxSubArray(int[] A) {
		int change = A[0];
		int max = A[0];
		for (int i = 1; i < A.length; i++) {
			System.out.println("A[i] :" + A[i] + "   change :" + change
					+ "   A[i]+change:" + (A[i] + change));
			// A[i]+change的意思就是i之前的最大值与A【i】的值
			change = Math.max(A[i], A[i] + change);
			max = Math.max(max, change);
		}
		return max;
	}

	/**
	 * Kadane算法，算法复杂度O(n)
	 */
	public int maxSubArray2(int[] A) {
		int max_ending_here = 0;
		int max_so_far = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (max_ending_here < 0)
				max_ending_here = 0;
			max_ending_here += A[i];
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}

	/**
	 * 分治法
	 */
	public int maxSubArray3(int[] A) {
		return divide(A, 0, A.length - 1);
	}

	public int divide(int A[], int low, int high) {
		if (low == high)
			return A[low];
		if (low == high - 1)
			return Math.max(A[low] + A[high], Math.max(A[low], A[high]));

		int mid = (low + high) / 2;
		int lmax = divide(A, low, mid - 1);
		int rmax = divide(A, mid + 1, high);

		int mmax = A[mid];
		int tmp = mmax;
		for (int i = mid - 1; i >= low; i--) {
			tmp += A[i];
			if (tmp > mmax)
				mmax = tmp;
		}
		tmp = mmax;
		for (int i = mid + 1; i <= high; i++) {
			tmp += A[i];
			if (tmp > mmax)
				mmax = tmp;
		}
		return Math.max(mmax, Math.max(lmax, rmax));

	}
}
