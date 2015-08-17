package org.wangbin.leetcode;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * @author wb
 * @date 2015-8-13 ����8:54:18
 */
public class _070ClimbingStairs {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(new _070ClimbingStairs().climbStairs2(n));

	}

	/**
	 * �ܼ򵥵Ķ�̬�滮���⣬ ����n��n>2������Ӧ����dp[n-1]��һ�� or dp[n-2]��2�� ���Ծ���dp[n-1] + dp[n-2]
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i < n + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
	/**
	 * ��ʵ�õ�Ҳ�Ƕ�̬�滮��ԭ��������û���������
	 * @param n
	 * @return
	 */
	public int climbStairs2(int n) {
		if (n <= 1) {
			return n;
		}
		int last = 1, lastlast = 1;
		int now = 0;
		for (int i = 2; i <= n; i++) {
			now = last + lastlast;
			lastlast = last;
			last = now;
		}
		return now;
	}
}
