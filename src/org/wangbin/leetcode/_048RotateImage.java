package org.wangbin.leetcode;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * @author wb
 * @date 2015-8-8 下午12:13:22
 */
public class _048RotateImage {
	public static void main(String[] args) {
		int [][]ints = {
				{1,2,3,4,5},
				{6,7,8,9,10},
				{11,12,13,14,15},
				{16,17,18,19,20},
				{21,22,23,24,25}
		};
		Util.showDoubleArrya(ints);
		new _048RotateImage().rotate(ints);
		Util.showDoubleArrya(ints);
	}
	/**
	 * 思路一，矩阵转置，然后每行都逆一下即可。
	 * http://www.cnblogs.com/TenosDoIt/p/3768734.html
	 * 非常不错。
	 * 还有个思路看做是一个环
	 * 可以见矩阵看成多个环组成，如下4*4的矩阵包括两个环，第一个环为1,2,3,4,8,12,16,15,14,13,9,5,1，第二个环为6,7,11,10。
	 * 每一组中：旋转后相当于把原来的数字移动到同组中下一个数字的位置
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		if(matrix==null){
			return;
		}
		int n = matrix[0].length;
		//转置矩阵
		for(int i = 0;i <n;i++){
			for(int j = i+1;j <n;j++){
				swap(matrix,i,j,j,i);
			}
		}
		//每一行翻转
		for(int i = 0; i < n; i++){
			for(int j = 0,len = n >>1; j <len;j++){
				swap(matrix, i, j, i, n-j-1);
			}
		}
		
	}
	/**
	 * num[i][j]变为num[j][i]
	 * @param num
	 * @param i
	 * @param j
	 * @param i2
	 * @param j2
	 */
	private void swap(int[][] num, int i, int j, int i2, int j2) {
		// TODO Auto-generated method stub
		int temp =num[i][j];
		num[i][j] = num[i2][j2];
		num[i2][j2] = temp;
	}
	
	
	/**
	 * 后续还有个思路就是，延反对角线交换即可。
	 * http://www.lifeincode.net/programming/leetcode-rotate-image-java/
	 * 其实只要把图画出来，答案就出来了
	 */
	public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int halfN;
        if (n % 2 == 0)
            halfN = n / 2;
        else
            halfN = n / 2 + 1;
        for (int i = 0; i < halfN; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }
}
