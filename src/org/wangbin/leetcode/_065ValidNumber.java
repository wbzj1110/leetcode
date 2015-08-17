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
 * @date 2015-8-11 上午9:23:35
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
	 * 卧槽 规则不熟悉 老TM各种情况那 我们设置3个FLAG:
	 * 
	 * 1. num
	 * 
	 * 2. exp
	 * 
	 * 3. dot
	 * 
	 * 有以下情况：
	 * 
	 * （1). 出现了e，则前面要有digit,不能有e. 并且后面要有digit.
	 * 
	 * (2). 出现了. 那么是一个小数，那么前面不可以有.和e
	 * 
	 * (3). 出现了+, - 那么它必须是第一个，或者前一个是e，比如" 005047e+6"
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
		// e如果有多个也不行。
		int isE = -1;// 是否有e
		int isPoint = -1;// 是否有点
		// 逻辑是点必须在e之前。2个可以任意都有
		char[] cs = s.toCharArray();
		for (int i = 0, len = cs.length; i < len; i++) {
			Character c = cs[i];
			// 除了数字.e以外的其他，例如字母+-等如果有那么就false
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
					return false;// 多个e,且e不能再第一个位置
				} else {
					isE = i;
				}
			} else if (cs[i] == '.') {
				// .可以作为第一位，".1"是对的。
				if (isPoint != -1) {
					return false;// 多个.,且.不能再第一个位置
				} else {
					if (isE != -1) {
						// 如果已经有了e，那么point必须在e之前
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
			// 只有1个点，那就是错的
			return false;
		}
		// 不能出现0e也就是e是最后一位的情况
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
	 * 正则表达式
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
	 * 还有这种
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
