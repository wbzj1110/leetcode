package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example, Given the following matrix:
 * 
 * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return
 * [1,2,3,6,9,8,7,4,5].
 * 
 * @author wb
 * @date 2015-8-8 下午3:31:38
 */
public class _054SpiralMatrix {
	public static void main(String[] args) {
		int[][] ints = { { 1, 2, 3 }, { 4, 5, 6 }, { 78, 9 } };
		// int [][]ints = {
		// {1,2,3,4,5},
		// {6,7,8,9,10},
		// {11,12,13,14,15},
		// {16,17,18,19,20},
		// {21,22,23,24,25}
		// };
		List<Integer> ls = new _054SpiralMatrix().spiralOrder(ints);
		for (Integer i : ls) {
			System.out.println(i + " ");
		}
	}

	/**
	 * 最笨的方法~~也是最容易想到的，按照节奏~~high起来 考虑2个初始条件，如果矩阵只有一行或者一列，那么无需转圈，依次输出即可。
	 * 
	 * 其他情况均需转圈：从左到右，从上到下，从右到左，从下到上。 从大圈依次循环到小圈即可。
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {

		if (matrix == null || matrix.length == 0) {
			return 	Collections.EMPTY_LIST;
		}

		List<Integer> result = new ArrayList<Integer>();
		int m = matrix.length;
		int n = matrix[0].length;

		int x = 0;
		int y = 0;

		while (m > 0 && n > 0) {

			// if one row/column left, no circle can be formed
			if (m == 1) {
				for (int i = 0; i < n; i++) {
					result.add(matrix[x][y++]);
				}
				break;
			} else if (n == 1) {
				for (int i = 0; i < m; i++) {
					result.add(matrix[x++][y]);
				}
				break;
			}

			// below, process a circle

			// top - move right
			for (int i = 0; i < n - 1; i++)
				result.add(matrix[x][y++]);

			// right - move down
			for (int i = 0; i < m - 1; i++)
				result.add(matrix[x++][y]);

			// bottom - move left
			for (int i = 0; i < n - 1; i++)
				result.add(matrix[x][y--]);

			// left - move up
			for (int i = 0; i < m - 1; i++)
				result.add(matrix[x--][y]);

			x++;
			y++;
			m = m - 2;
			n = n - 2;
		}

		return result;
	}
	/**
	 * 卧槽，好多种思路
	 * http://www.cnblogs.com/yuzhangcmu/p/4046543.html
	 * 
	 */
}
