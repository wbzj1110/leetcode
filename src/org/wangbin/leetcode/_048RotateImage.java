package org.wangbin.leetcode;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * @author wb
 * @date 2015-8-8 ����12:13:22
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
	 * ˼·һ������ת�ã�Ȼ��ÿ�ж���һ�¼��ɡ�
	 * http://www.cnblogs.com/TenosDoIt/p/3768734.html
	 * �ǳ�����
	 * ���и�˼·������һ����
	 * ���Լ����󿴳ɶ������ɣ�����4*4�ľ����������������һ����Ϊ1,2,3,4,8,12,16,15,14,13,9,5,1���ڶ�����Ϊ6,7,11,10��
	 * ÿһ���У���ת���൱�ڰ�ԭ���������ƶ���ͬ������һ�����ֵ�λ��
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		if(matrix==null){
			return;
		}
		int n = matrix[0].length;
		//ת�þ���
		for(int i = 0;i <n;i++){
			for(int j = i+1;j <n;j++){
				swap(matrix,i,j,j,i);
			}
		}
		//ÿһ�з�ת
		for(int i = 0; i < n; i++){
			for(int j = 0,len = n >>1; j <len;j++){
				swap(matrix, i, j, i, n-j-1);
			}
		}
		
	}
	/**
	 * num[i][j]��Ϊnum[j][i]
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
	 * �������и�˼·���ǣ��ӷ��Խ��߽������ɡ�
	 * http://www.lifeincode.net/programming/leetcode-rotate-image-java/
	 * ��ʵֻҪ��ͼ���������𰸾ͳ�����
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
