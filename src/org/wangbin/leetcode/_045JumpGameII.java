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
 * @version 2015-8-8 上午10:09:09
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
	 * 先尝试下递归。
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
		System.out.println("复制数组时间 :" + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		helper(nums2, index, curStep);
		System.out.println("结果运算时间:" + (System.currentTimeMillis() - time));
		System.out.println("result ~~" + nums2[nums.length]);
		return minStep;
	}

	/**
	 * 递归过程
	 * 
	 * @param nums
	 *            数组
	 * @param index
	 *            当前走到哪里了
	 * @curStep 当前的步骤
	 */
	private void helper(int[] nums, int index, int curStep) {
		// TODO Auto-generated method stub
		if (index >= nums.length - 1) {
			nums[nums.length - 1] = Math.min(nums[nums.length - 1], curStep);
			return;
		}
		for (int i = index; i < nums.length - 1; i++) {
			int temp = nums[i];
			// 可以跳几步，一开始如果每次都跳第一步，会超时，那么我们就让他每次都跳最大长度
			for (int j = 1; j <= temp; j++) {
				index += j;
				curStep++;
				helper(nums, index, curStep);
				curStep--;
			}
		}

	}

	/**
	 * 递归写的太丑了，看看人家写的 还有DP的解法。
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
	 * 上边的递归加贪心算法总是会超时 用下边这个很巧妙 
	 * 
	 * ret:目前为止的jump数
	 * 
	 * curRch:从A[0]进行ret次jump之后达到的最大范围
	 * 
	 * curMax:从0~i这i+1个A元素中能达到的最大范围
	 * 
	 * 当curRch < i，说明ret次jump已经不足以覆盖当前第i个元素，因此需要增加一次jump，使之达到
	 * 
	 * 记录的curMax。
	 */
	public int jumpStart(int[] nums) {
		return jumpC(nums, nums.length);
	}
	public int jumpC(int A[], int n) {
		int ret =0;//跳的次数
		int curMax = 0;//0~i这i+1个元素能够到达的最远距离
		int curRch =0;//从A[0]跳ret次，能够到达的最远距离
		for(int i =0;i <A.length;i++){
			if(curRch < i){
				//说明跳的最远你距离都到不了i，那么加一条
				ret++;
				curRch = curMax;
			}
			curMax = Math.max(curMax, A[i]+i);
		}
		
		return ret;
	}

}
