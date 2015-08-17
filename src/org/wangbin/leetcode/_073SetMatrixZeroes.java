package org.wangbin.leetcode;

public class _073SetMatrixZeroes {
	public static void main(String[] args) {
		int[][] ints = new int[][] { { 1, 2, 0 }, { 4, 5, 6 }, { 7, 8, 9 } };
		// int[][] ints = new int[][] { { 1 }, { 0 } };
		new _073SetMatrixZeroes().setZeroes(ints);
		Util.showDoubleArrya(ints);
	}

	/**
	 * 找出标记来，再行列变为0。记录数组为matrix.length. 空间复杂度为O(n) 时间复杂度O(nm)
	 * 
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		// 走一遍 看一下
		int[] hang = new int[matrix.length];
		for (int i = 0; i < hang.length; i++) {
			hang[i] = -1;
		}
		// String []lie = new String [matrix[0].length];
		int temp = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				temp = matrix[i][j];
				if (temp == 0) {
					hang[i] = j;
				}
			}
		}
		for (int i = 0; i < hang.length; i++) {
			if (hang[i] != -1) {
				// 0,0全部为0
				changToZero(matrix, i, hang[i]);
			}
		}

	}

	private void changToZero(int[][] matrix, int hang, int lie) {
		// TODO Auto-generated method stub
		for (int i = 0; i < matrix[0].length; i++) {
			matrix[hang][i] = 0;
		}
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][lie] = 0;
		}
	}
	/**
	 * 2个数组标记。空间复杂度O(n+m) 时间复杂度O(nm)
	 * @param matrix
	 */
	public void setZeroes2(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0){
			return;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[] flagr = new int[m];
		int[] flagc = new int[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					flagr[i] = 1;
					flagc[j] = 1;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (flagr[i] == 1 || flagc[j] == 1) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
