package org.wangbin.leetcode;

/**
 * Divide Two Integers Divide two integers without using multiplication,
 * division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 * 
 * 利用位运算，意思是任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0
 * *2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2
 * ，我们先让除数左移直到大于被除数之前得到一个最大的基n的值
 * ，说明被除数中至少包含2^n个除数，然后减去这个基数，再依次找到n-1，...，1的值。将所有的基数相加即可得到结果
 * 
 * @author wb
 * @version 2015-8-1 下午9:19:35
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
