package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example: Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * @author wb
 * @version 2015-8-8 ����10:09:09
 */
public class _045JumpGameII {
	public static void main(String[] args) {
		int[] ints = new int[] { 2, 3, 1, 1, 4 };
//		ints = new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5,
//				9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8,
//				5 };
		System.out.println(new _045JumpGameII().jumpStart(ints));
	}

	/**
	 * �ȳ����µݹ顣
	 * 
	 * @param nums
	 * @return
	 */

	public int jump(int[] nums) {
		long time = System.currentTimeMillis();
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int minStep = Integer.MAX_VALUE;
		int index = 0;
		int curStep = 0;
		time = System.currentTimeMillis();
		int[] nums2 = Arrays.copyOf(nums, nums.length + 1);
		nums2[nums.length] = Integer.MAX_VALUE;
		System.out.println("��������ʱ�� :" + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		helper(nums2, index, curStep);
		System.out.println("�������ʱ��:" + (System.currentTimeMillis() - time));
		System.out.println("result ~~" + nums2[nums.length]);
		return minStep;
	}

	/**
	 * �ݹ����
	 * 
	 * @param nums
	 *            ����
	 * @param index
	 *            ��ǰ�ߵ�������
	 * @curStep ��ǰ�Ĳ���
	 */
	private void helper(int[] nums, int index, int curStep) {
		// TODO Auto-generated method stub
		if (index >= nums.length - 1) {
			nums[nums.length - 1] = Math.min(nums[nums.length - 1], curStep);
			return;
		}
		for (int i = index; i < nums.length - 1; i++) {
			int temp = nums[i];
			// ������������һ��ʼ���ÿ�ζ�����һ�����ᳬʱ����ô���Ǿ�����ÿ�ζ�����󳤶�
			for (int j = 1; j <= temp; j++) {
				index += j;
				curStep++;
				helper(nums, index, curStep);
				curStep--;
			}
		}

	}

	/**
	 * �ݹ�д��̫���ˣ������˼�д�� ����DP�Ľⷨ��
	 * http://www.zhuangjingyang.com/leetcode-jump-game-jump-game-ii/
	 * 
	 */
	public static int jumpPL(int[] A) {
		if (A.length == 0 || A == null)
			return 0;
		// every time try to move far
		return count(A, 0, 0, 0);
	}

	public static int count(int[] A, int curIndex, int curJump, int count) {
		if (curIndex >= A.length - 1)
			return count;

		curJump = A[curIndex];
		// curJump > 0 show it can jump yet~~greedy
		while (curJump > 0) {
			int ans = count(A, curIndex + curJump, 0, count + 1);
			curJump--;
			if (ans != 0) {
				return ans;
			}
		}
		return 0;
	}

	/**
	 * �ϱߵĵݹ��̰���㷨���ǻᳬʱ ���±���������� 
	 * 
	 * ret:ĿǰΪֹ��jump��
	 * 
	 * curRch:��A[0]����ret��jump֮��ﵽ�����Χ
	 * 
	 * curMax:��0~i��i+1��AԪ�����ܴﵽ�����Χ
	 * 
	 * ��curRch < i��˵��ret��jump�Ѿ������Ը��ǵ�ǰ��i��Ԫ�أ������Ҫ����һ��jump��ʹ֮�ﵽ
	 * 
	 * ��¼��curMax��
	 */
	public int jumpStart(int[] nums) {
		return jumpC(nums, nums.length);
	}
	public int jumpC(int A[], int n) {
		int ret =0;//���Ĵ���
		int curMax = 0;//0~i��i+1��Ԫ���ܹ��������Զ����
		int curRch =0;//��A[0]��ret�Σ��ܹ��������Զ����
		for(int i =0;i <A.length;i++){
			if(curRch < i){
				//˵��������Զ����붼������i����ô��һ��
				ret++;
				curRch = curMax;
			}
			curMax = Math.max(curMax, A[i]+i);
		}
		
		return ret;
	}

}
