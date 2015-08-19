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
 * @date 2015-8-18 ����7:33:34
 */
public class _084LargestRectangleinHistogram {
	public static void main(String[] args) {
		int[] ints = new int[] { 2, 1, 5, 6, 2, 3 };
		System.out.println(new _084LargestRectangleinHistogram()
				.largestRectangleArea2(ints));
	}

	/**
	 * ���������Ǳ���2�顣����o(n^2) ���ϵ� ��ʱ�ˡ���
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
						// ��ǰ�ߵ�minС ��ôд�����ȥ
						min[j] = height[j];
					} else {
						// ��ǰ��С��д��С��
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
	 * ����ʾ��ջ~~���� ������û��������������ο����д�ġ�����http://www.cnblogs.com/lichen782/p/
	 * leetcode_Largest_Rectangle_in_Histogram.html
	 * ֻ��˵̫NB��
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
