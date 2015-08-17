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
 * Some examples: isMatch("aa","a") �� false isMatch("aa","aa") �� true
 * isMatch("aaa","aa") �� false isMatch("aa", "*") �� true isMatch("aa", "a*") ��
 * true isMatch("ab", "?*") �� true isMatch("aab", "c*a*b") �� false
 * 
 * @author wb
 * @version 2015-8-7 ����9:28:47
 */
public class _044WildcardMatching {
	public static void main(String[] args) {
		String s = "aabcccccd";
		String p = "aa*cc*";
		System.out.println(new _044WildcardMatching().isMatch(s, p));

	}
	
	/**
	 * �����ҵ���̬�滮����������ȻAC���ˣ����ڴ����ݵ�ԭ�򣬵���˼·����֮����Ū������һ��
	 * http://www.cnblogs.com/yuzhangcmu/p/4116153.html
	 * �鿴˼·һ.
	 * �±ߵ�������������ָ���������
	 */
	public boolean isMatch(String s, String p) {
		//Input:"", ""  ���Ϊtrue
//		if (s == null || p == null || s.length() == 0 || p.length() == 0) {
//			return false;
//		}
		int i = 0;
		int j = 0;
		int starIndex = -1;
		int iIndex = -1;

		while (i < s.length()) {
			// *ƥ���κ��ַ���������һֱ���ƥ��
			if (j < p.length()
					&& (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				i++;
				j++;
			} else if (j < p.length() && p.charAt(j) == '*') {
				//��סi j�ߵ����
				starIndex = j;
				iIndex = i;
				j++;//��̽��*��ߵ��Ǹ���ĸ�Ƿ���i��ƥ��
			} else if (starIndex != -1) {
				//��ʱ˵��������*��ô��һֱ����ƥ��
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
