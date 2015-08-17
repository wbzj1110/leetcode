package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,��,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the
 * following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation
 * sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * @author wb
 * @date 2015-8-8 ����9:46:00
 */
public class _060PermutationSequence {
	public static void main(String[] args) {
		int n = 4;
		int k = 3;
		// ����׳�
		long result = 1;
		for (int i = 1; i <= 9; i++) {
			result *= i;
		}
		System.out.println(result);

		System.out.println(new _060PermutationSequence().getPermutation(n, k));
	}

	/**
	 * ���Եݹ鲻�ɣ�Ȼ��Ϳ���ѧ������ ������ѧ���ɡ�
	 * 
	 * ���������������Ҫ��ɶ����������n����k�������� 1��2��3��... , n������ȫ�����У����ص�k�����С�
	 * 
	 * ��Ŀ�������ǣ�����n����������n!�����У���ôn-1��������(n-1)!�����С�
	 * 
	 * ��ô����nλ����˵�������ȥ���λ�����������n-1λ���� (n-1)!�����С�
	 * 
	 * ���ԣ����Ƕ���nλ����˵��ÿһ����ͬ�����λ�����������ƴ��(n-1)!�����С�
	 * 
	 * ������Ϳ��Կ����ǰ���ÿ��(n-1)!���������顣
	 * 
	 * ���� k/(n-1)! ����ȡ�����λ�������е�index��������k�����е����λ���ܴ������е�indexλȡ�ã���ʱ��Ҫ���������������ɾ����
	 * 
	 * Ȼ���µ�k�Ϳ�����k%(n-1)!��á�ѭ��n�μ��ɡ�
	 * 
	 * ͬʱ��Ϊ�˿��Ը�����������䣬��k��--��
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		k--;// to transfer it as begin from 0 rather than 1

		List<Integer> numList = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++)
			numList.add(i);

		int factorial = 1;
		for (int i = 2; i < n; i++)
			factorial *= i;

		StringBuilder res = new StringBuilder();
		int times = n - 1;
		while (times >= 0) {
			int indexInList = k / factorial;
			res.append(numList.get(indexInList));
			numList.remove(indexInList);

			k = k % factorial;// new k for next turn
			if (times != 0)
				factorial = factorial / times;// new (n-1)!

			times--;
		}

		return res.toString();
	}

	/**
	 * ʹ����������¼δʹ�õ����֣�ÿ�õ�һ�����������������Ƴ����ɡ�
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation1(int n, int k) {
		List<Character> digits = new LinkedList<Character>();

		for (char i = '1'; i <= '0' + n; i++) {
			digits.add(i);
		}

		k = k - 1;
		StringBuilder sb = new StringBuilder();

		int sum = 1;
		// n!
		for (int i = 1; i <= n; i++) {
			sum *= i;
		}

		int cur = n;
		while (!digits.isEmpty()) {
			sum /= cur;
			cur--;

			int digitIndex = k / sum;
			k = k % sum;
			sb.append(digits.get(digitIndex));
			// remove the used digit.
			digits.remove(digitIndex);
		}

		return sb.toString();
	}
}
