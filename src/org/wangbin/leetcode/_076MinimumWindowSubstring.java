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
 * @date 2015-8-15 上午11:25:27
 */
public class _076MinimumWindowSubstring {
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.println(new _076MinimumWindowSubstring().minWindow(s, t));
	}

	/**
	 * 2个指针。
	 * http://www.lifeincode.net/programming/leetcode-minimum-window-substring
	 * -java/ http://www.cnblogs.com/springfor/p/3872559.html 时间复杂度O(n)
	 * 假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的
	 * 子串进行判断。做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l,
	 * ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串。因为每次扫描的时间复杂度是O(2*n/l)(每个单词不会被访问多于两次，一次是窗
	 * 口右端，一次是窗口左端)，总共扫描l次（i=0, ...,
	 * l-1)，所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量。
	 * 
	 * ”
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
				// 筛出前边多余的元素(多余的元素的意思是要么不在dict里边，要么多余的重复元素)
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
