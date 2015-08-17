package org.wangbin.leetcode;

/**
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack. 
 * 这也太简单了吧
 * 
 * @author wb
 * @version 2015-8-1 下午9:04:37
 */
public class _028ImplementstrStr {
	public static void main(String[] args) {

	}

	public int strStr(String haystack, String needle) {
		if (haystack.length() <= 0 && needle.length() <= 0) {
			return 0;
		}
		if (haystack.length() <= 0) {
			return -1;
		}
		int i = haystack.indexOf(needle);
		if (i == -1) {
			return -1;
		} else {
			return i;
		}
	}

}
