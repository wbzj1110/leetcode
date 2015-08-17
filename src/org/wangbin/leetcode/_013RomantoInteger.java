package org.wangbin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999. ��������ת��Ϊ����
 * 
 * @author wb
 * @version 2015-7-28 ����9:28:38
 */
public class _013RomantoInteger {
	public static void main(String[] args) {
		System.out.println(new _013RomantoInteger().romanToInt2("MCCCLXXXIX"));
	}

	/**
	 * �Ƚϱ��ķ��������������ֵ��߼��ж�
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == 'I') {
				if (res >= 5)// ���>=5, ˵��֮ǰ�϶�������V�ˣ��������I�϶�����ߣ���
					res += -1;
				else
					res += 1;
			} else if (c == 'V') {// ����V,L,D,M,ͳͳ����5��50��500��100
				res += 5;
			} else if (c == 'X') {
				if (res >= 50)// ˵���϶�֮ǰ�й�L�����X�϶�����ߣ���
					res += -10;
				else
					res += 10;
			} else if (c == 'L') {
				res += 50;
			} else if (c == 'C') {// ˵��֮ǰ��D�����C�϶�����ߣ������ܱ�����ֻ��I X C
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
	 * ����java��map�㶨
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
