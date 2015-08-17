package org.wangbin.leetcode;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
 * allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * @author wb
 * @date 2015-8-16 上午9:55:32
 */
public class _081SearchinRotatedSortedArrayII {
	public static void main(String[] args) {

	}

	/**
	 * 这道题与之前Search in Rotated Sorted
	 * Array类似，问题只在于存在dupilcate。那么和之前那道题的解法区别就是，不能通过比较A
	 * [mid]和边缘值来确定哪边是有序的，会出现A[mid
	 * ]与边缘值相等的状态。所以，解决方法就是对于A[mid]==A[low]和A[mid]==A[high]单独处理。
	 * 
	 * 当中间值与边缘值相等时，让指向边缘值的指针分别往前移动，忽略掉这个相同点，再用之前的方法判断即可。
	 * 
	 * 这一改变增加了时间复杂度，试想一个数组有同一数字组成{1，1，1，1，1}，target=2,
	 * 那么这个算法就会将整个数组遍历，时间复杂度由O(logn)升到O(n)
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public boolean search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return false;
		}
		int low = 0;
		int high = A.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (target < A[mid]) {
				if (A[mid] < A[high]) {
					// 右边有序，target一定在左边
					high = mid - 1;
				} else if (A[mid] == A[high]) {
					// 因为可能有重复，不能够区分是否是有序，high自减看下。
					high--;
				} else {
					// 左边有序
					if (target < A[low]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}

			} else if (target > A[mid]) {
				if (A[low] < A[mid]) {
					// 左边有序，target一定在右边
					low = mid + 1;
				} else if (A[low] == A[mid]) {
					// 因为可能有重复，不能够区分是否是有序，low自加看下。
					low++;
				} else {
					// 右边有序
					if (target > A[high]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}

			} else
				return true;
		}
		return false;
	}

	/**
	 * 上边可以看到二分法最差情况其实时间复杂度已经到了O(n)，那么直接进行线性查找就好了
	 */
	public boolean search2(int[] A, int target) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == target) {
				return true;
			}
		}
		return false;
	}
}
