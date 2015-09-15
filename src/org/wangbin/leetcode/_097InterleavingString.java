package org.wangbin.leetcode;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 * 
 * @author wb
 * @date 2015-9-15 下午10:02:38
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
     * 没啥思路。。。参考的人家的： http://www.cnblogs.com/springfor/p/3896159.html “When you see string problem
     * that is about subsequence or matching, dynamic programming method should come to your mind
     * naturally. ” 大体思路是，s1取一部分s2取一部分，最后是否能匹配s3。
     * 
     * 动态规划数组是dp[i][j]，表示：s1取前i位，s2取前j位，是否能组成s3的前i+j位。
     * 
     * 初始化是，假设s1为空，那么s2每一位跟s3匹配放入dp[0][j]；假设s2为空，那么s1每一位跟s3匹配放入dp[i][0]。 那什么时候取True，什么时候取False呢？
     * 
     * False很直观，如果不等就是False了嘛。
     * 
     * 那True呢？首先第一个条件，新添加的字符，要等于s3里面对应的位( i + j 位)，第二个条件，之前那个格子也要等于True
     * 
     * 举个简单的例子s1 = ab, s2 = c, s3 = bbc
     * ，假设s1已经取了2位，c还没取，此时是False（ab!=bb），我们取s2的新的一位c，即便和s3中的c相等，但是之前是False，所以这一位也是False
     * 
     * 同理，如果s1 = ab, s2 = c, s3=abc
     * ，同样的假设，s1取了2位，c还没取，此时是True（ab==ab），我们取s2的新的一位c,和s3中的c相等，且之前这一位就是True，此时我们可以放心置True （abc==abc）
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

        for (int i = 1; i <= s1.length() && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {// s1匹配了s3的那些
            dp[i][0] = true;
        }

        for (int i = 1; i <= s2.length() && s2.charAt(i - 1) == s3.charAt(i - 1); i++) {// s2匹配了s3的那些
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
