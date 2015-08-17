package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
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
 * @date 2015-8-8 下午9:46:00
 */
public class _060PermutationSequence {
	public static void main(String[] args) {
		int n = 4;
		int k = 3;
		// 计算阶乘
		long result = 1;
		for (int i = 1; i <= 9; i++) {
			result *= i;
		}
		System.out.println(result);

		System.out.println(new _060PermutationSequence().getPermutation(n, k));
	}

	/**
	 * 明显递归不成，然后就看数学规律啦 发现数学规律。
	 * 
	 * 首先先捋捋这道题要干啥，给了我们n还有k，在数列 1，2，3，... , n构建的全排列中，返回第k个排列。
	 * 
	 * 题目告诉我们：对于n个数可以有n!种排列；那么n-1个数就有(n-1)!种排列。
	 * 
	 * 那么对于n位数来说，如果除去最高位不看，后面的n-1位就有 (n-1)!种排列。
	 * 
	 * 所以，还是对于n位数来说，每一个不同的最高位数，后面可以拼接(n-1)!种排列。
	 * 
	 * 所以你就可以看成是按照每组(n-1)!个这样分组。
	 * 
	 * 利用 k/(n-1)! 可以取得最高位在数列中的index。这样第k个排列的最高位就能从数列中的index位取得，此时还要把这个数从数列中删除。
	 * 
	 * 然后，新的k就可以有k%(n-1)!获得。循环n次即可。
	 * 
	 * 同时，为了可以跟数组坐标对其，令k先--。
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
	 * 使用链表来记录未使用的数字，每用掉一个，将它从链表中移除即可。
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
