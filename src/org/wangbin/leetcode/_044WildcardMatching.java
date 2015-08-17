package org.wangbin.leetcode;

import java.net.URLEncoder;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character. '*' Matches any sequence of characters
 * (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false isMatch("aa", "*") → true isMatch("aa", "a*") →
 * true isMatch("ab", "?*") → true isMatch("aab", "c*a*b") → false
 * 
 * @author wb
 * @version 2015-8-7 上午9:28:47
 */
public class _044WildcardMatching {
	public static void main(String[] args) {
		String s = "aabcccccd";
		String p = "aa*cc*";
		System.out.println(new _044WildcardMatching().isMatch(s, p));

	}
	
	/**
	 * 后来找到动态规划的做法，虽然AC不了，由于大数据的原因，但是思路不错。之后再弄，先走一遍
	 * http://www.cnblogs.com/yuzhangcmu/p/4116153.html
	 * 查看思路一.
	 * 下边的做法是利用了指针的做法。
	 */
	public boolean isMatch(String s, String p) {
		//Input:"", ""  结果为true
//		if (s == null || p == null || s.length() == 0 || p.length() == 0) {
//			return false;
//		}
		int i = 0;
		int j = 0;
		int starIndex = -1;
		int iIndex = -1;

		while (i < s.length()) {
			// *匹配任何字符，且阔以一直向后匹配
			if (j < p.length()
					&& (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				i++;
				j++;
			} else if (j < p.length() && p.charAt(j) == '*') {
				//记住i j走到哪里。
				starIndex = j;
				iIndex = i;
				j++;//试探下*后边的那个字母是否与i的匹配
			} else if (starIndex != -1) {
				//此时说明，有了*那么，一直向下匹配
				j = starIndex + 1;
				i = iIndex + 1;
				iIndex++;
			} else {
				return false;
			}
		}

		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}

		return j == p.length();
	}


}
