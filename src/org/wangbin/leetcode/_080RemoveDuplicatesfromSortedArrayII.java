package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.
 * 
 * @author wb
 * @date 2015-8-15 下午1:45:42
 */
public class _080RemoveDuplicatesfromSortedArrayII {
	public static void main(String[] args) {
		int[] ints = new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 };
		ints = new int[] { 1, 1, 1, 2 };
		System.out.println(new _080RemoveDuplicatesfromSortedArrayII()
				.removeDuplicates(ints));
	}

	/**
	 * 不知道为啥过不了，神奇啊。。 Input: [1,1,1,2] Output: [1,1,1] Expected: [1,1,2]
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int result = 0;
		int tempCount = 1;
		int tempNumber = -1;
		int[] nums2 = new int[nums.length];
		int index = 0;
		for (int i = 0, len = nums.length; i < len; i++) {
			if (tempNumber != nums[i]) {
				nums2[index++] = nums[i];
				tempCount = 1;
				tempNumber = nums[i];
				result++;
			} else {
				if (tempCount >= 2) {
					continue;
				} else {
					nums2[index++] = nums[i];
					tempCount++;
					result++;
				}

			}
		}
		// System.out.println("nums2:" +Arrays.toString(nums2) + " Index :" +
		// index );
		nums = Arrays.copyOf(nums2, index);
		// System.out.println("nums:" +Arrays.toString(nums) );
		return result;
	}
	/**
	 * 必须要直接改变传入的参数的数组的值，事后不行。。完全不明白为啥不行
	 */
	public int removeDuplicates2(int[] A) {
        if (A.length <= 2)
            return A.length;
 
        int prev = 1; // point to previous
        int curr = 2; // point to current
 
        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }
 
        return prev + 1;
    }
}
