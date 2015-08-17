package org.wangbin.leetcode;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * @author wb
 * @version 2015-8-7 ����8:36:38
 */
public class _042TrappingRainWater {
	public static void main(String[] args) {
		int []ints = new int []{0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(new _042TrappingRainWater().trap(ints));
	}

	/**
	 * �ο����͵�Сһ��http://blog.csdn.net/wzy_1988/article/details/17752809���Ľ���˼·
	 * 
	 * �����ȣ�������������Ŀ��Ҫ���ţ���������ÿ��A[i]��trapped water��������Ȼ�����е�A[i]��trapped
	 * water������Ӽ���
	 * 
	 * ��Σ�����ÿ��A[i]��trapped water��������ȡ����A[i]�������ߵĸ߶ȣ�����չ����Сֵ��A[i]�Ĳ�ֵ����volume[i] =
	 * [min(left[i], right[i]) - A[i]] * 1�������1�ǿ�ȣ����the width of each bar is
	 * 2,�Ǿ�Ҫ����2�ˡ�
	 * 
	 * 
	 * ��ô�����A[i]�����Ҹ߶��أ�
	 * Ҫ֪������ʢ����ˮ��Ҫ���̰塣��ô��ÿ��A[i]��˵��Ҫ��һ����ߵ���̰壬����һ����ߵ��Ҷ̰壬������ֱ����̵İ��Ӽ�ȥA
	 * [i]ԭ�е�ֵ�����ܳɶ���ˮ�ˡ�
	 * 
	 * 
	 * 
	 * ������Ҫ���������һ�������ң�����ߵ���̰壻һ�����ҵ�������ߵ��Ҷ̰塣����¼��ʢˮ������ֵ�������ս���ˡ�
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] A) {
		if (A == null || A.length == 0)
			return 0;

		int i, max, total = 0;
		int left[] = new int[A.length];
		int right[] = new int[A.length];

		// from left to right
		left[0] = A[0];
		max = A[0];
		for (i = 1; i < A.length; i++) {
			left[i] = Math.max(max, A[i]);
			max = Math.max(max, A[i]);
		}

		// from right to left
		right[A.length - 1] = A[A.length - 1];
		max = A[A.length - 1];
		for (i = A.length - 2; i >= 0; i--) {
			right[i] = Math.max(max, A[i]);
			max = Math.max(max, A[i]);
		}

		// trapped water (when i==0, it cannot trapped any water)
		for (i = 1; i < A.length - 1; i++) {
			System.out.println(left[i]+ "~~" +right[i] + "  A[i]:" + A[i]);
			int bit = Math.min(left[i], right[i]) - A[i];
			if (bit > 0)
				total += bit;
		}

		return total;
	}

}
