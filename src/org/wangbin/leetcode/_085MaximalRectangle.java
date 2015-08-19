package org.wangbin.leetcode;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area. �ð�
 * ����Ŀ��_084LargestRectangleinHistogram������̵�ͼ򵥶���
 * 
 * @author wb
 * @date 2015-8-18 ����8:19:49
 */
public class _085MaximalRectangle {
	/**
	 * ���ǿ���һ��һ��ɨ�裬��¼����ÿһ��cell��ʼ����ߵ�bar��
	 * 
	 * ������
	 * 
	 * 1001010
	 * 
	 * 0101000
	 * 
	 * ��ô�Ե�һ�ж��ԣ�cell�ĸ߶���1001010, �ڶ�����0102000 ��ÿһ�м��㵽β��ʱ����Ը��еļ���Max Rectangle
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		 if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	            return 0;
	        }
	        
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        int[][] h = new int[matrix.length][matrix[0].length];
	        
	        int maxArea = 0;
	        
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                if (matrix[i][j] == '0') {
	                    h[i][j] = 0;
	                } else {
	                    if (i == 0) {
	                        h[i][j] = 1;    
	                    } else {
	                        h[i][j] = 1 + h[i - 1][j];
	                    }
	                }
	                
	                if (j == cols - 1) {
	                    // the last element of every row.
	                    int maxRec = maxAreaInRow(h[i]);
	                    maxArea = Math.max(maxArea, maxRec);
	                }
	            }
	        }
	        
	        return maxArea;
	    }

	private int maxAreaInRow(int[] height) {
		// TODO Auto-generated method stub
		if (height == null || height.length == 0) {
			return 0;
		}
		Stack<Integer> s = new Stack<Integer>();
		int max = 0;
		int len = height.length;
		int i = 0;
		int h  = 0;
		int width = 0;
		while (i <= len) {
			//ջ�洢��������
			//i���Ե���len�����������ߵ��±ߵ��߼��ˡ�
			if (s.isEmpty() || (i < len && height[i] >= height[s.peek()])) {
				//ֻҪ�Ǵ��������ô��һֱ�š�
				s.push(i);
				i++;
			} else {
				//ջ�ǿգ��ҵ�ǰ����λ�õ�Ԫ�ش���ջ��Ԫ��
				h = height[s.pop()];
				width = s.isEmpty() ? i : i - s.peek() - 1;
				max = Math.max(max, h * width);
			}
		}
		return max;
	}
	
}
