package org.wangbin.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * @author wb
 * @version 2015-7-25 ����1:00:43
 */
public class _004MedianofTwoSortedArrays {
	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 4, 5, 6 };
		System.out.println(findMedianSortedArrays(a, b));

	}

	/**
	 * ������log(n)
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static double findMedianSortedArrays(int A[], int B[]) {
		int allLen = A.length + B.length;
		Queue<Integer> que = new PriorityQueue<Integer>(allLen);
		for (int i = 0, len = A.length; i < len; i++) {
			que.add(A[i]);
		}
		for (int i = 0, len = B.length; i < len; i++) {
			que.add(B[i]);
		}
		int[] C = new int[allLen];
		for (int i = 0; i < allLen; i++) {
			C[i] = que.poll();
		}
		if (allLen % 2 != 0) {
			return C[allLen / 2];
		} else {
			return (C[allLen / 2] + C[allLen / 2 - 1]) * 1.00 / 2;
		}
	}

	/**
	 * �������ķ����������գ�Ѱ��һ��unioned sorted array�еĵ�k�󣨴�1��ʼ��������������ȼ���Ѱ�Ҳ��ж�����sorted
	 * array�е�k/2����1��ʼ�����������
	 * 
	 * ���⻯����median����ô����������˵���������(m+n)/2+1����1��ʼ�����������
	 * 
	 * ������ż����˵���������(m+n)/2�󣨴�1��ʼ�����͵�(m+n)/2+1�󣨴�1��ʼ��������������ƽ��ֵ��
	 * 
	 * ��ô����ж�������������A,B�е�k������أ�
	 * 
	 * ������Ҫ�ж�A[k/2-1]��B[k/2-1]�Ĵ�С��
	 * 
	 * ���A[k/2-1]==B[k/2-1]����ô������������������е�k�������
	 * 
	 * ���A[k/2-1]<B[k/2-1],
	 * ��ô˵��A[0]��A[k/2-1]���������ǵ�k�������������Ҫ������һ�룬������A[k/2]��A[A.length
	 * -1]�����ҡ���Ȼ����Ϊ����������A[0]��A[k/2-1]��k/2��������ô��k��Ҳ�ͱ���ˣ���k-k/2��������ˡ�
	 * 
	 * ��� A[k/2-1]>B[k/2-1]������֮ǰ�ԳƵĲ����ͺá�
	 * 
	 * �������������ӭ�ж����ˡ�
	 * 
	 * ��Ȼ���߽�����ҳ�����٣���Ҫ�ж��Ƿ���һ�����鳤��Ϊ0���Լ�k==1ʱ��������
	 * 
	 * ��Ϊ����������ȡ��������ҳΪ�˷����������ÿ������ķְ������ȡ��
	 * 
	 * int partA = Math.min(k/2,m); int partB = k - partA;
	 * 
	 * Ϊ���ܱ�֤����ķְ������ȷ����Ҫ��֤A����ĳ���С��B����ĳ��ȡ�
	 * 
	 * ͬʱ���ڷ��ؽ��ʱ��ע�⾫�����⣬����double�͵ľͺá�
	 * ������˼������ô���£�д����̫�鷳
	 * @param A
	 * @param B
	 * @return
	 */

	public static double findMedianSortedArrays2(int A[], int B[]) {
		int len = A.length + B.length;
		if (len % 2 == 0) {
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0,
					len / 2 + 1)) / 2.0;
		} else {
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
	}

	// find kth number of two sorted array
	private static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
		if (A_start >= A.length)
			return B[B_start + k - 1];
		if (B_start >= B.length)
			return A[A_start + k - 1];

		if (k == 1)
			return Math.min(A[A_start], B[B_start]);

		int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1]
				: Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1]
				: Integer.MAX_VALUE;

		if (A_key < B_key) {
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
		}
	}
}
