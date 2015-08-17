package org.wangbin.leetcode;

import org.apache.commons.lang3.StringUtils;

/**
 * Validate if a given string is numeric.
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
 * "2e10" => true Note: It is intended for the problem statement to be
 * ambiguous. You should gather all requirements up front before implementing
 * one.
 * 
 * Update (2015-02-10): The signature of the C++ function had been updated. If
 * you still see your function signature accepts a const char * argument, please
 * click the reload button to reset your code definition.
 * 
 * @author wb
 * @date 2015-8-11 ����9:23:35
 */
public class _065ValidNumber {
	public static void main(String[] args) {
		_065ValidNumber ob = new _065ValidNumber();
		// System.out.println("abc".matches("[a-zA-X&&[^e]]*"));

		// System.out.println(ob.isNumber("0"));
		// System.out.println(ob.isNumber(" 0.1"));
		// System.out.println(ob.isNumber("abc"));
		// System.out.println(ob.isNumber("1 a"));
		// System.out.println(ob.isNumber("2e10"));
		System.out.println(ob.isNumber(".1") + " " + ob.isNumber2(".1"));
		System.out.println(ob.isNumber(".") + " " + ob.isNumber2("."));
		System.out.println(ob.isNumber("..") + " " + ob.isNumber2(".."));
		System.out.println(ob.isNumber("2.") + " " + ob.isNumber2("2."));
		System.out.println(ob.isNumber("0e") + " " + ob.isNumber2("0e"));
		String s = ".e1";
		System.out.println(s + "  " + ob.isNumber(s) + " " + ob.isNumber2(s));
		s = "1.5e01";
		System.out.println(s + "  " + ob.isNumber(s) + " " + ob.isNumber2(s));
		s = "-1.";
		System.out.println(s + "  " + ob.isNumber(s) + " " + ob.isNumber2(s));
	}

	/**
	 * �Բ� ������Ϥ ��TM��������� ��������3��FLAG:
	 * 
	 * 1. num
	 * 
	 * 2. exp
	 * 
	 * 3. dot
	 * 
	 * �����������
	 * 
	 * ��1). ������e����ǰ��Ҫ��digit,������e. ���Һ���Ҫ��digit.
	 * 
	 * (2). ������. ��ô��һ��С������ôǰ�治������.��e
	 * 
	 * (3). ������+, - ��ô�������ǵ�һ��������ǰһ����e������" 005047e+6"
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		s = s.trim();
		if (s.length() == 0 || s.contains(" ")) {
			return false;
		}
		// e����ж��Ҳ���С�
		int isE = -1;// �Ƿ���e
		int isPoint = -1;// �Ƿ��е�
		// �߼��ǵ������e֮ǰ��2���������ⶼ��
		char[] cs = s.toCharArray();
		for (int i = 0, len = cs.length; i < len; i++) {
			Character c = cs[i];
			// ��������.e�����������������ĸ+-���������ô��false
			if (!(Character.isDigit(c))) {
				if (c != 'e' && c != '.') {
					if (i == 0 && (c == '+' || c == '-')) {

					} else {
						return false;
					}
				}
			}
			if (cs[i] == 'e') {
				if (isE != -1 || i == 0) {
					return false;// ���e,��e�����ٵ�һ��λ��
				} else {
					isE = i;
				}
			} else if (cs[i] == '.') {
				// .������Ϊ��һλ��".1"�ǶԵġ�
				if (isPoint != -1) {
					return false;// ���.,��.�����ٵ�һ��λ��
				} else {
					if (isE != -1) {
						// ����Ѿ�����e����ôpoint������e֮ǰ
						if (i < isE) {
							isPoint = i;
						} else {
							return false;
						}
					} else {
						isPoint = i;
					}
				}
			}
		}
		if (isPoint != -1 && cs.length == 1) {
			// ֻ��1���㣬�Ǿ��Ǵ��
			return false;
		}
		// ���ܳ���0eҲ����e�����һλ�����
		if (isE == cs.length - 1) {
			return false;
		}
		// .e1
		if (isE != -1 && isPoint != -1) {
			if (isPoint == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ������ʽ
	 */
	public boolean isNumber2(String s) {
		if (s.trim().isEmpty()) {
			return false;
		}
		String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
		if (s.trim().matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��������
	 */
	public boolean isNumber3(String s) {
		if (s == null) {
			return false;
		}

		// cut the leading spaces and tail spaces.
		String sCut = s.trim();

		/*
		 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" =>
		 * false "2e10" => true
		 */

		int len = sCut.length();

		boolean num = false;
		boolean exp = false;
		boolean dot = false;

		for (int i = 0; i < len; i++) {
			char c = sCut.charAt(i);
			if (c == 'e') {
				if (!num || exp) {
					return false;
				}
				exp = true;
				num = false; // Should be: 2e2 , so there should be number
								// follow "e"
			} else if (c <= '9' && c >= '0') {
				num = true;
			} else if (c == '.') {
				if (exp || dot) { // can't be: e0.2 can't be: ..
					return false;
				}
				dot = true;
			} else if (c == '+' || c == '-') {
				if (i != 0 && sCut.charAt(i - 1) != 'e') { // filter :
															// " 005047e+6",
															// this is true.
					return false;
				}
			} else {
				// invalid character.
				return false;
			}
		}

		return num;
	}
}
