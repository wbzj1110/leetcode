package org.wangbin.leetcode;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example, Given height = [2,1,5,6,2,3], return 10.
 * 
 * @author wb
 * @date 2015-8-18 下午7:33:34
 */
public class _084LargestRectangleinHistogram {
	public static void main(String[] args) {
		int[] ints = new int[] { 2, 1, 5, 6, 2, 3 };
		System.out.println(new _084LargestRectangleinHistogram()
				.largestRectangleArea2(ints));
	}

	/**
	 * 笨方法就是遍历2遍。。。o(n^2) 果断的 超时了。。
	 * 
	 * @param height
	 * @return
	 */
	public int largestRectangleArea(int[] height) {
		int[] min = new int[height.length];
		int maxArea = 0;
		int temp = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i; j < height.length; j++) {
				if (i == j) {
					min[j] = height[j];
				} else {
					if (height[j] < min[j - 1]) {
						// 比前者的min小 那么写进大的去
						min[j] = height[j];
					} else {
						// 比前者小，写入小的
						min[j] = min[j - 1];
					}
				}
				temp = min[j] * (j - i + 1);
				if (temp > maxArea) {
					maxArea = temp;
				}
			}
		}
		return maxArea;
	}

	/**
	 * 看提示用栈~~—— ——！没想出来。。。。参考这个写的。。。http://www.cnblogs.com/lichen782/p/
	 * leetcode_Largest_Rectangle_in_Histogram.html
	 * 只能说太NB了
	 * 
	 * @param height
	 * @return
	 */
	public int largestRectangleArea2(int[] height) {
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
