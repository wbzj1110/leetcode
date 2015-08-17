package org.wangbin.leetcode;

import java.util.HashMap;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 * Note: If there is no such window in S that covers all characters in T, return
 * the emtpy string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.
 * 
 * @author wb
 * @date 2015-8-15 ����11:25:27
 */
public class _076MinimumWindowSubstring {
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.println(new _076MinimumWindowSubstring().minWindow(s, t));
	}

	/**
	 * 2��ָ�롣
	 * http://www.lifeincode.net/programming/leetcode-minimum-window-substring
	 * -java/ http://www.cnblogs.com/springfor/p/3872559.html ʱ�临�Ӷ�O(n)
	 * ����Դ�ַ����ĳ���Ϊn���ֵ��е��ʵĳ���Ϊl����Ϊ����һ���ַ�������������Ҫ��Դ�ַ������г���Ϊl��
	 * �Ӵ������жϡ�������i��0��l-1���ַ���ʼ���õ���ʼindex�ֱ�Ϊi, i+l, i+2*l,
	 * ...�ĳ���Ϊl�ĵ��ʡ������Ϳ��Ա�֤�жϵ����е����������Ĵ�����Ϊÿ��ɨ���ʱ�临�Ӷ���O(2*n/l)(ÿ�����ʲ��ᱻ���ʶ������Σ�һ���Ǵ�
	 * ���Ҷˣ�һ���Ǵ������)���ܹ�ɨ��l�Σ�i=0, ...,
	 * l-1)�������ܸ��Ӷ���O(2*n/l*l)=O(n)����һ�������㷨���ռ临�Ӷ����ֵ�Ĵ�С����O(m*l)������m���ֵ�ĵ���������
	 * 
	 * ��
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindow(String S, String T) {
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
		for (int i = 0; i < T.length(); i++) {
			char c = T.charAt(i);
			if (!dict.containsKey(c)) {
				dict.put(c, 1);
			} else {
				dict.put(c, dict.get(c) + 1);
			}
		}
		HashMap<Character, Integer> found = new HashMap<Character, Integer>();
		int foundCounter = 0;
		int start = 0;
		int end = 0;
		int min = Integer.MAX_VALUE;
		String minWindow = "";
		while (end < S.length()) {
			char c = S.charAt(end);
			if (dict.containsKey(c)) {
				if (found.containsKey(c)) {
					if (found.get(c) < dict.get(c)) {
						foundCounter++;
					}
					found.put(c, found.get(c) + 1);
				} else {
					found.put(c, 1);
					foundCounter++;
				}
			}
			if (foundCounter == T.length()) {
				// When foundCounter equals to T.length(), in other words, found
				// all characters.
				char sc = S.charAt(start);
				// ɸ��ǰ�߶����Ԫ��(�����Ԫ�ص���˼��Ҫô����dict��ߣ�Ҫô������ظ�Ԫ��)
				while (!found.containsKey(sc) || found.get(sc) > dict.get(sc)) {
					if (found.containsKey(sc) && found.get(sc) > dict.get(sc)) {
						found.put(sc, found.get(sc) - 1);
					}

					start++;
					sc = S.charAt(start);
				}
				if (end - start + 1 < min) {
					minWindow = S.substring(start, end + 1);
					min = end - start + 1;
				}
			}
			end++;
		}
		return minWindow;
	}
}
