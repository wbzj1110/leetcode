package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * click to show follow up.
 * 
 * Follow up: A rather straight forward solution is a two-pass algorithm using
 * counting sort. First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * @author wb
 * @date 2015-8-15 上午10:54:50
 */
public class _075SortColors {
	public static void main(String[] args) {
		int[] nums = new int[] { 0, 1, 2, 0, 2, 1 };
		nums = new int[] { 1,2,0 };
		new _075SortColors().sortColors2(nums);
		for (int i : nums) {
			System.out.print(i + " ");
		}
	}

	public void sortColors(int[] nums) {
		Arrays.sort(nums);
	}

	/**
	 * 2个指针 第一个指着0的上限，第二个指针指向从数组最大值往前推的2的位置
	 * 
	 * @param nums
	 */
	public void sortColors2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int pointTo0 = 0;
		int pointTo2 = nums.length - 1;
		for(int i =0,len = nums.length;i < len;){
			if(nums[i] == 0 && pointTo0 <i){//如果是0，且当前位置不能比i的位置还要大
				swap(nums,i,pointTo0);
				pointTo0++;
			}else if (nums[i]==2&&i<pointTo2){
				swap(nums,i,pointTo2);
				pointTo2--;
			}else{
				i++;
			}
		}
		
	}

	private void swap(int[] nums, int i, int j) {
		// TODO Auto-generated method stub
		int temp = nums[i];
		nums[i]= nums[j];
		nums[j]=temp;
	}
}
