package org.wangbin.leetcode;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 * 
 * @author wb
 * @date 2015-9-15 ����10:02:38
 */
public class _097InterleavingString {
    public static void main(String[] args) {
        String str1 = "aabcc";
        String str2 = "dbbca";
        String str3 = "aadbbcbcac";
        System.out.println(new _097InterleavingString().isInterleave(str1, str2, str3));
        str3 = "aadbbcbcac";
        System.out.println(new _097InterleavingString().isInterleave(str1, str2, str3));
    }

    /**
     * ûɶ˼·�������ο����˼ҵģ� http://www.cnblogs.com/springfor/p/3896159.html ��When you see string problem
     * that is about subsequence or matching, dynamic programming method should come to your mind
     * naturally. �� ����˼·�ǣ�s1ȡһ����s2ȡһ���֣�����Ƿ���ƥ��s3��
     * 
     * ��̬�滮������dp[i][j]����ʾ��s1ȡǰiλ��s2ȡǰjλ���Ƿ������s3��ǰi+jλ��
     * 
     * ��ʼ���ǣ�����s1Ϊ�գ���ôs2ÿһλ��s3ƥ�����dp[0][j]������s2Ϊ�գ���ôs1ÿһλ��s3ƥ�����dp[i][0]�� ��ʲôʱ��ȡTrue��ʲôʱ��ȡFalse�أ�
     * 
     * False��ֱ�ۣ�������Ⱦ���False���
     * 
     * ��True�أ����ȵ�һ������������ӵ��ַ���Ҫ����s3�����Ӧ��λ( i + j λ)���ڶ���������֮ǰ�Ǹ�����ҲҪ����True
     * 
     * �ٸ��򵥵�����s1 = ab, s2 = c, s3 = bbc
     * ������s1�Ѿ�ȡ��2λ��c��ûȡ����ʱ��False��ab!=bb��������ȡs2���µ�һλc�������s3�е�c��ȣ�����֮ǰ��False��������һλҲ��False
     * 
     * ͬ�����s1 = ab, s2 = c, s3=abc
     * ��ͬ���ļ��裬s1ȡ��2λ��c��ûȡ����ʱ��True��ab==ab��������ȡs2���µ�һλc,��s3�е�c��ȣ���֮ǰ��һλ����True����ʱ���ǿ��Է�����True ��abc==abc��
     * 
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1.length() && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {// s1ƥ����s3����Щ
            dp[i][0] = true;
        }

        for (int i = 1; i <= s2.length() && s2.charAt(i - 1) == s3.charAt(i - 1); i++) {// s2ƥ����s3����Щ
            dp[0][i] = true;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                char c = s3.charAt(i + j - 1);
                if (c == s1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (c == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
