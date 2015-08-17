package org.wangbin.leetcode;

/**
 * Divide Two Integers Divide two integers without using multiplication,
 * division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 * 
 * ����λ���㣬��˼���κ�һ���������Ա�ʾ����2����Ϊ�׵�һ�����������ϣ���num=a_0
 * *2^0+a_1*2^1+a_2*2^2+...+a_n*2^n���������������ʽ�Լ�����һλ�൱�ڳ���2
 * ���������ó�������ֱ�����ڱ�����֮ǰ�õ�һ�����Ļ�n��ֵ
 * ��˵�������������ٰ���2^n��������Ȼ���ȥ����������������ҵ�n-1��...��1��ֵ�������еĻ�����Ӽ��ɵõ����
 * 
 * @author wb
 * @version 2015-8-1 ����9:19:35
 */
public class _029DivideTwoIntegers {
	public static void main(String[] args) {
		int dividend = -2147483648;
		int divisor = -1;
		int i = new _029DivideTwoIntegers().divide(1, 1);
		System.out.println(i);
	}

	private static int divide(int dividend, int divisor) {
		// TODO Auto-generated method stub
		if (divisor == 0)
			return Integer.MAX_VALUE;
		if (dividend == 0) {
			return 0;
		}
		int res = 0;
		if (dividend == Integer.MIN_VALUE) {
			res = 1;
			dividend += Math.abs(divisor);
		}
		if (divisor == Integer.MIN_VALUE)
			return res;
		boolean a = dividend >= 0 ? true : false;
		boolean b = divisor >= 0 ? true : false;
		boolean isNeg = ((dividend ^ divisor) >>> 31 == 1) ? true : false;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		int digit = 0;
		while (divisor <= (dividend >> 1)) {
			divisor <<= 1;
			digit++;
		}
		while (digit >= 0) {
			if (dividend >= divisor) {
				dividend -= divisor;
				res += 1 << digit;
			}
			divisor >>= 1;
			digit--;
		}
		if (res <= Integer.MIN_VALUE) {
			if ((a && b) || (!a && !b)) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}

		} else if (res > Integer.MAX_VALUE) {
			if ((a && b) || (!a && !b)) {
				return Integer.MIN_VALUE;
			} else {
				return Integer.MAX_VALUE;
			}
		} else {
			if ((a && b) || (!a && !b)) {
				return res;
			} else {
				return -res;
			}
		}
	}

}
