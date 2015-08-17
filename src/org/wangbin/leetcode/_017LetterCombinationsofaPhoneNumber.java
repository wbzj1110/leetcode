package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * 
 * 
 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd",
 * "ce", "cf"].
 * 递归~
 * @author wb
 * @version 2015-7-30 上午8:38:40
 */
public class _017LetterCombinationsofaPhoneNumber {
	public static void main(String[] args) {
		String str = "234";
		System.out.println(new _017LetterCombinationsofaPhoneNumber()
				.letterCombinations2(str));
	}

	/**
	 * 利用数组，需要用index传递数组位置
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return Collections.EMPTY_LIST;
		}
		// 依据图片0 1 2 3 4 5 6 7 8 9
		String[] keyboard = { "", "", "abc", "def", "ghi", "jkl", "mno",
				"pqrs", "tuv", "wxyz" };
		StringBuilder current = new StringBuilder();
		int index = 0;
		List<String> result = new ArrayList<String>();
		buildResult(digits, index, current, keyboard, result);
		for (String s : result) {
			System.out.println(s);
		}
		return result;
	}

	private void buildResult(String digits, int index, StringBuilder current,
			String[] keyboard, List<String> result) {
		// TODO Auto-generated method stub
		if (index == digits.length()) {
			result.add(current.toString());
			return;
		}
		int num = digits.charAt(index) - '0';
		for (int i = 0; i < keyboard[num].length(); i++) {
			current.append(keyboard[num].charAt(i));
			buildResult(digits, index + 1, current, keyboard, result);
			current.deleteCharAt(current.length() - 1);
		}
	}
	/**
	 * 利用map~~这样用中间结果StringBuilder的长度即可
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations2(String digits) {
		if (digits == null || digits.equals("")) {
			return Collections.EMPTY_LIST;
		}
		ArrayList<String> result = new ArrayList<String>();
		Map<Character, char[]> map = new HashMap<Character, char[]>();
		map.put('0', new char[] {});
		map.put('1', new char[] {});
		map.put('2', new char[] { 'a', 'b', 'c' });
		map.put('3', new char[] { 'd', 'e', 'f' });
		map.put('4', new char[] { 'g', 'h', 'i' });
		map.put('5', new char[] { 'j', 'k', 'l' });
		map.put('6', new char[] { 'm', 'n', 'o' });
		map.put('7', new char[] { 'p', 'q', 'r', 's' });
		map.put('8', new char[] { 't', 'u', 'v' });
		map.put('9', new char[] { 'w', 'x', 'y', 'z' });

		StringBuilder sb = new StringBuilder();
		helper(map, digits, sb, result);

		return result;
	}

	private void helper(Map<Character, char[]> map, String digits,
			StringBuilder sb, ArrayList<String> result) {
		if(sb.length()==digits.length()){
			result.add(sb.toString());
			return;
		}
		for(char c :map.get(digits.charAt(sb.length()))){
			sb.append(c);
			helper(map, digits, sb, result);
			sb.deleteCharAt(sb.length()-1);
		}
		
	}
}
