package org.wangbin.leetcode;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example, a = "11" b = "1" Return "100". 二进制数相加
 * 
 * @author wb
 * @date 2015-8-12 上午8:49:25
 */
public class _067AddBinary {
	public static void main(String[] args) {
		String a = "1010";
		String b = "1011";
		System.out.println(new _067AddBinary().addBinary(a, b));
	}

	public String addBinary(String a, String b) {
		int m = a.length();
		int n = b.length();
		int carry = 0;
		String res = "";
		int maxLen = Math.max(m, n);
		int tmp = 0;
		for (int i = 0; i < maxLen; i++) {
			// start from last char of a and b
			// notice that left side is int and right side is char
			// so we need to minus the decimal value of '0'
			int p = 0, q = 0;
			if (i < m) {
				p = a.charAt(m - 1 - i) - '0';
			}else {
				p = 0;
			}
			if (i < n) {
				q = b.charAt(n - 1 - i) - '0';
			} else {
				q = 0;
			}
			tmp = p + q + carry;
			carry = tmp / 2;
			res = tmp%2 + res;
		}
		return (carry == 0) ? res : "1" + res;
	}

	public String addBinary2(String a, String b) {
		if (a.length() < b.length()) {
			String tmp = a;
			a = b;
			b = tmp;
		}

		int pa = a.length() - 1;
		int pb = b.length() - 1;
		int carries = 0;
		String rst = "";

		while (pb >= 0) {
			int sum = (int) (a.charAt(pa) - '0') + (int) (b.charAt(pb) - '0')
					+ carries;
			rst = String.valueOf(sum % 2) + rst;
			carries = sum / 2;
			pa--;
			pb--;
		}

		while (pa >= 0) {
			int sum = (int) (a.charAt(pa) - '0') + carries;
			rst = String.valueOf(sum % 2) + rst;
			carries = sum / 2;
			pa--;
		}

		if (carries == 1) {
			rst = "1" + rst;
		}
		return rst;
	}
}
