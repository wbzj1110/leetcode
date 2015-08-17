package org.wangbin.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1. 
 * 2个指针就好了
 * 
 * @author wb
 * @version 2015-7-25 上午10:46:40
 */
public class _003LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		String str = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(str));
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int len = s.length();
		Set<Character> sets = new HashSet<Character>(len);
		int max, runner, walker;
		max = runner = walker = 0;
		while (runner < len) {
			if (sets.contains(s.charAt(runner))) {
				max = Math.max(max, runner - walker);
				while (s.charAt(runner) != s.charAt(walker)) {
					sets.remove(s.charAt(walker));
					walker++;
				}
				walker++;
			} else {
				sets.add(s.charAt(runner));
			}
			runner++;
		}
		max = Math.max(max, runner - walker);
		return max;
	}
}
