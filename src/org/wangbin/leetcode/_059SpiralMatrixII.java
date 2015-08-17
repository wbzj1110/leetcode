package org.wangbin.leetcode;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n2 in spiral order.
 * 
 * For example, Given n = 3,
 * 
 * You should return the following matrix: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5
 * ] ]
 * 
 * @author wb
 * @date 2015-8-8 下午9:28:11
 */
public class _059SpiralMatrixII {
	public static void main(String[] args) {
		int n = 4;
		int[][] result = new _059SpiralMatrixII().generateMatrix(n);
		for (int[] r : result) {
			for (int i : r) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * top bottom left right 分别记录四个角的限制，然后依次填入就好了
	 * 
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		int k = 1;
		int top = 0, bottom = n - 1, left = 0, right = n - 1;
		while (left < right && top < bottom) {
			for (int j = left; j < right; j++) {
				res[top][j] = k++;
			}
			for (int i = top; i < bottom; i++) {
				res[i][right] = k++;
			}
			for (int j = right; j > left; j--) {
				res[bottom][j] = k++;
			}
			for (int i = bottom; i > top; i--) {
				res[i][left] = k++;
			}
			left++;
			right--;
			top++;
			bottom--;
		}
		if (n % 2 != 0)
			res[n / 2][n / 2] = k;
		return res;
	}

}
