package org.wangbin.leetcode;

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 * 
 * @author wb
 * @version 2015-8-3 下午7:13:29
 */
public class _034SearchforaRange {
	public static void main(String[] args) {
		int []ints = new int[]{5, 7, 7, 8, 8, 10};
		int []ints2 = new _034SearchforaRange().searchRange(ints, 8);
		for(int i :ints2){
			System.out.println(i);
		}
		
		
	}

	public int[] searchRange(int[] A, int target) {
		int[] res = { -1, -1 };
		if (A == null || A.length == 0) {
			return res;
		}
		int low = 0;
		int high = A.length - 1;
		int pos = 0;
		int mid = 0;
		while (low <= high) {
			mid =  (high + low) / 2;
			pos = mid;
			if (A[mid] > target) {
				high = mid - 1;
			} else if (A[mid] < target) {
				low = mid + 1;
			} else {
				res[0] = pos;
				res[1] = pos;
				break;
			}
		}
		if (A[pos] != target) {
			return new int[] { -1, -1 };
		}
		//找出index最大的target的值
		low = pos;
		high = A.length - 1;
		while (low <= high) {
			int newmid = (low + high) / 2;
			if (A[newmid] == target)
				low = newmid + 1;
			else
				high = newmid - 1;
		}
		res[1] = high;
		//找出index最小的target的值
		low = 0;
		high = pos;
		while (low <= high) {
			int newmid = (low + high) / 2;
			if (A[newmid] == target)
				high = newmid - 1;
			else
				low = newmid + 1;
		}
		res[0] = low;

		return res;
	}
}
