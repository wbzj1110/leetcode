package org.wangbin.leetcode;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * @author wb
 * @version 2015-8-8 ����10:50:08
 */
public class _055JumpGame {
	public static void main(String[] args) {
		int ins[] = new int[] { 3, 2, 1, 0, 4 };
		 ins = new int[]{2,0};
		System.out.println(new _055JumpGame().canJump2(ins));
	}


	/**
	 * Ȼ������Զ������,�ο�_045JumpGameII
	 * 
	 * @param nums
	 * @return
	 */
	public boolean canJump2(int[] nums) {
		if(nums==null||nums.length==0){
			return false;
		}
		if(nums.length==1){
			return true;
		}
		int maxCover = 0;// ��0����ǰλ�ã��ܹ�������Զλ��,��Զλ��С���˵�ǰλ�ã�˵�����ܹ�������
		for (int i = 0; i <= maxCover && i < nums.length; i++) {
			maxCover = Math.max(maxCover, nums[i] + i);
			if (maxCover >= nums.length-1) {
				return true;
			}
		}
		return false;
	}


}
