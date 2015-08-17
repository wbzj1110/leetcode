package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Note: All inputs will be in lower-case.
 * 
 * @author wb
 * @date 2015-8-8 ÏÂÎç12:41:57
 */
public class _049Anagrams {
	public static void main(String[] args) {
		String[] strs = new String[] { "abc", "aabbc", "bac" };
		List<String> ls = new _049Anagrams().anagrams2(strs);
		for (String s : ls) {
			System.out.println(s);
		}
		ls = new _049Anagrams().anagrams(strs);
		for (String s : ls) {
			System.out.println(s);
		}
	}

	public List<String> anagrams(String[] strs) {
		if (strs == null || strs.length == 0) {
			return Collections.EMPTY_LIST;
		}
		List<String> result = new ArrayList<String>();
		Map<String, List<String>> maps = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] cs = s.toCharArray();
			Arrays.sort(cs);
			String temp = new String(cs);
			if (maps.containsKey(temp)) {
				List<String> tempL = maps.get(temp);
				if (tempL.size() == 1) {
					result.add(tempL.get(0));
				}
				tempL.add(s);
				maps.put(temp, tempL);

				result.add(s);
			} else {
				List<String> tL = new ArrayList<String>();
				tL.add(s);
				maps.put(temp, tL);
			}
		}

		return result;
	}

	public ArrayList<String> anagrams2(String[] strs) {
		ArrayList<String> result = new ArrayList<String>();

		if (strs == null || strs.length == 0)
			return result;

		HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();

		for (String s : strs) {
			char[] temp = s.toCharArray();
			Arrays.sort(temp);
			String tempStr = new String(temp);

			if (hm.containsKey(tempStr)) {
				if (hm.get(tempStr).size() == 1)
					result.add(hm.get(tempStr).get(0));
				hm.get(tempStr).add(s);
				result.add(s);
			} else {
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(s);
				hm.put(tempStr, tempList);
			}
		}
		return result;
	}
}
