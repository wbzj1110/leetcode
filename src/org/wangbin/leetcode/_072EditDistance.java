package org.wangbin.leetcode;

/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character b) Delete a character c) Replace a character
 * 
 * @author wb
 * @date 2015-8-15 上午8:48:58
 */
public class _072EditDistance {
	public static void main(String[] args) {
		String str1 = "distance";
		String str2 = "springbok";
		System.out.println(new _072EditDistance().minDistance(str1, str2));
	}

	/**
	 * 处理这道题也是用动态规划。
	 * 
	 * 动态数组dp[word1.length+1][word2.length+1]
	 * 
	 * dp[i][j]表示从word1前i个字符转换到word2前j个字符最少的步骤数。
	 * 
	 * 假设word1现在遍历到字符x，word2遍历到字符y（word1当前遍历到的长度为i，word2为j）。
	 * 
	 * 以下两种可能性：
	 * 
	 * 1. x==y，那么不用做任何编辑操作，所以dp[i][j] = dp[i-1][j-1]
	 * 
	 * 2. x != y
	 * 
	 * (1) 在word1插入y， 那么dp[i][j] = dp[i][j-1] + 1
	 * 
	 * (2) 在word1删除x， 那么dp[i][j] = dp[i-1][j] + 1
	 * 
	 * (3) 把word1中的x用y来替换，那么dp[i][j] = dp[i-1][j-1] + 1
	 * 
	 * 最少的步骤就是取这三个中的最小值。
	 * 
	 * 最后返回 dp[word1.length+1][word2.length+1] 即可。
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public int minDistance(String word1, String word2) {
		if (word1 == null || word1.length() == 0) {
			return word2.length();
		}
		if (word2 == null || word2.length() == 0) {
			return word1.length();
		}
		int len1 = word1.length();
		int len2 = word2.length();
		char[] cs1 = word1.toCharArray();
		char[] cs2 = word2.toCharArray();
		int[][] dp = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
		char c1 = '.';
		char c2 = '.';
		int replace = 0;
		int insert = 0;
		int delete = 0;
		for (int i = 1; i <= len1; i++) {
			c1 = cs1[i-1];
			for (int j = 1; j <= len2; j++) {
				c2 = cs2[j-1];
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// 把word1中的x用y来替换，那么dp[i][j] = dp[i-1][j-1] + 1
					replace = dp[i - 1][j - 1] + 1;
					// 在word1插入y， 那么dp[i][j] = dp[i][j-1] + 1
					insert = dp[i - 1][j] + 1;
					// 在word1删除x， 那么dp[i][j] = dp[i-1][j] + 1
					delete = dp[i][j - 1] + 1;
					dp[i][j] = Math.min(replace, Math.min(insert, delete));
				}
			}
		}
		return dp[len1][len2];
	}
}
