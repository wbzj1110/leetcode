package org.wangbin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999. 罗马数字转换为数字
 * 
 * @author wb
 * @version 2015-7-28 上午9:28:38
 */
public class _013RomantoInteger {
	public static void main(String[] args) {
		System.out.println(new _013RomantoInteger().romanToInt2("MCCCLXXXIX"));
	}

	/**
	 * 比较笨的方法按照罗马数字的逻辑判断
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == 'I') {
				if (res >= 5)// 如果>=5, 说明之前肯定遍历过V了，所以这个I肯定在左边，减
					res += -1;
				else
					res += 1;
			} else if (c == 'V') {// 遇见V,L,D,M,统统都加5，50，500，100
				res += 5;
			} else if (c == 'X') {
				if (res >= 50)// 说明肯定之前有过L，这个X肯定在左边，减
					res += -10;
				else
					res += 10;
			} else if (c == 'L') {
				res += 50;
			} else if (c == 'C') {// 说明之前有D，这个C肯定在左边，减。能被减的只有I X C
				if (res >= 500)
					res += -100;
				else
					res += 100;
			} else if (c == 'D') {
				res += 500;
			} else if (c == 'M') {
				res += 1000;
			}
		}
		return res;
	}

	/**
	 * 利用java的map搞定
	 * 
	 * @param s
	 * @return
	 */
	private static Map<Character, Integer> m = new HashMap<Character, Integer>(7);
	static{
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);
	}
	public int romanToInt2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int length = s.length();
//		System.out.println(s.charAt(length - 1));
		int result = m.get(s.charAt(length - 1));
		for (int i = length - 2; i >= 0; i--) {
//			System.out.println(s.charAt(i + 1) + "~~~~" + s.charAt(i));
//			System.out.println(m.get(s.charAt(i + 1)) + "~~~~" +m.get(s.charAt(i)));
			if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
				result += m.get(s.charAt(i));
			} else {
				result -= m.get(s.charAt(i));
			}
		}
		return result;
	}
}
