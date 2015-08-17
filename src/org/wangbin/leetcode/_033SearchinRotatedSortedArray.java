package org.wangbin.leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author wb
 * @version 2015-8-2 上午11:05:54
 */
public class _033SearchinRotatedSortedArray {
	public static void main(String[] args) {
		int[] ints = new int[] { 4, 5, 6, 7, 8, 0, 1, 2, 3 };
		int result = new _033SearchinRotatedSortedArray().search(ints, 8);
		System.out.println(result);
	}

	public int search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}
		int left = 0;
		int right = A.length - 1;
		int mid = 0;
		while (left < right) {
			mid = (right + left) / 2;
			if (A[mid] == target) {
				return mid;
			}
			if (A[left] < A[mid]) {
				if (A[left] <= target && target < A[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {// A[left]>A[mid]
				if (A[mid] < target && target <= A[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}

		return -1;
	}

	/**
	 * 如果target比A[mid]值要小 如果A[mid]右边有序（A[mid]<A[high])
	 * 那么target肯定不在右边（target比右边的都得小），在左边找 如果A[mid]左边有序
	 * 那么比较target和A[low]，如果target比A[low]还要小，证明target不在这一区，去右边找；反之，左边找。
	 * 如果target比A[mid]值要大 如果A[mid]左边有序（A[mid]>A[low]）
	 * 那么target肯定不在左边（target比左边的都得大），在右边找 如果A[mid]右边有序
	 * 那么比较target和A[high]，如果target比A[high]还要大，证明target不在这一区，去左边找；反之，右边找。
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public int search2(int[] A, int target) {
		 if(A==null||A.length==0)
	         return -1;
	        
	       int low = 0;
	       int high = A.length-1;
	      
	       while(low <= high){
	           int mid = (low + high)/2;
	           if(target < A[mid]){
	               if(A[mid]<A[high])//right side is sorted
	                 high = mid - 1;//target must in left side
	               else
	                 if(target<A[low])//target<A[mid]&&target<A[low]==>means,target cannot be in [low,mid] since this side is sorted
	                    low = mid + 1;
	                 else 
	                    high = mid - 1;
	           }else if(target > A[mid]){
	               if(A[low]<A[mid])//left side is sorted
	                 low = mid + 1;//target must in right side
	               else
	                 if(target>A[high])//right side is sorted. If target>A[high] means target is not in this side
	                    high = mid - 1;
	                 else
	                    low = mid + 1;
	           }else
	             return mid;
	       }
	       
	       return -1;
	}

}
