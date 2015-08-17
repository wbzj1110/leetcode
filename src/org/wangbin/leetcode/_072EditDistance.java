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
 * @date 2015-8-15 ����8:48:58
 */
public class _072EditDistance {
	public static void main(String[] args) {
		String str1 = "distance";
		String str2 = "springbok";
		System.out.println(new _072EditDistance().minDistance(str1, str2));
	}

	/**
	 * ���������Ҳ���ö�̬�滮��
	 * 
	 * ��̬����dp[word1.length+1][word2.length+1]
	 * 
	 * dp[i][j]��ʾ��word1ǰi���ַ�ת����word2ǰj���ַ����ٵĲ�������
	 * 
	 * ����word1���ڱ������ַ�x��word2�������ַ�y��word1��ǰ�������ĳ���Ϊi��word2Ϊj����
	 * 
	 * �������ֿ����ԣ�
	 * 
	 * 1. x==y����ô�������κα༭����������dp[i][j] = dp[i-1][j-1]
	 * 
	 * 2. x != y
	 * 
	 * (1) ��word1����y�� ��ôdp[i][j] = dp[i][j-1] + 1
	 * 
	 * (2) ��word1ɾ��x�� ��ôdp[i][j] = dp[i-1][j] + 1
	 * 
	 * (3) ��word1�е�x��y���滻����ôdp[i][j] = dp[i-1][j-1] + 1
	 * 
	 * ���ٵĲ������ȡ�������е���Сֵ��
	 * 
	 * ��󷵻� dp[word1.length+1][word2.length+1] ���ɡ�
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
					// ��word1�е�x��y���滻����ôdp[i][j] = dp[i-1][j-1] + 1
					replace = dp[i - 1][j - 1] + 1;
					// ��word1����y�� ��ôdp[i][j] = dp[i][j-1] + 1
					insert = dp[i - 1][j] + 1;
					// ��word1ɾ��x�� ��ôdp[i][j] = dp[i-1][j] + 1
					delete = dp[i][j - 1] + 1;
					dp[i][j] = Math.min(replace, Math.min(insert, delete));
				}
			}
		}
		return dp[len1][len2];
	}
}
