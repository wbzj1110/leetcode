package org.wangbin.leetcode;
/**
 * 考虑特殊情况
 * @author wb
 * @version 2015-7-25 下午2:39:29
 */
public class _008String_to_Integer_atoi {
	public static void main(String[] args) {
		String str = "-12345";
		System.out.println(new _008String_to_Integer_atoi().atoi(str));
	}
	 public int atoi(String str) {
	        if (str == null || str.length() < 1)
			return 0;
	 
		// trim white spaces
		str = str.trim();
	 
		char flag = '+';
	 
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result
		double result = 0;
	 
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
	 
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	 
		return (int) result;
	    }
}
