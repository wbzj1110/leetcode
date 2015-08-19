package org.wangbin.leetcode;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area. 好吧
 * 这题目有_084LargestRectangleinHistogram这个的铺垫就简单多了
 * 
 * @author wb
 * @date 2015-8-18 下午8:19:49
 */
public class _085MaximalRectangle {
	/**
	 * 我们可以一行一行扫描，记录下以每一个cell开始的最高的bar。
	 * 
	 * 举例：
	 * 
	 * 1001010
	 * 
	 * 0101000
	 * 
	 * 那么对第一行而言，cell的高度是1001010, 第二行是0102000 在每一行计算到尾部时，针对该行的计算Max Rectangle
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
			//栈存储的是索引
			//i阔以等于len这样就阔以走到下边的逻辑了。
			if (s.isEmpty() || (i < len && height[i] >= height[s.peek()])) {
				//只要是大的数据那么就一直放。
				s.push(i);
				i++;
			} else {
				//栈非空，且当前索引位置的元素大于栈顶元素
				h = height[s.pop()];
				width = s.isEmpty() ? i : i - s.peek() - 1;
				max = Math.max(max, h * width);
			}
		}
		return max;
	}
	
}
