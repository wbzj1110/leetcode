package org.wangbin.leetcode;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
 * allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * @author wb
 * @date 2015-8-16 ����9:55:32
 */
public class _081SearchinRotatedSortedArrayII {
	public static void main(String[] args) {

	}

	/**
	 * �������֮ǰSearch in Rotated Sorted
	 * Array���ƣ�����ֻ���ڴ���dupilcate����ô��֮ǰ�ǵ���Ľⷨ������ǣ�����ͨ���Ƚ�A
	 * [mid]�ͱ�Եֵ��ȷ���ı�������ģ������A[mid
	 * ]���Եֵ��ȵ�״̬�����ԣ�����������Ƕ���A[mid]==A[low]��A[mid]==A[high]��������
	 * 
	 * ���м�ֵ���Եֵ���ʱ����ָ���Եֵ��ָ��ֱ���ǰ�ƶ������Ե������ͬ�㣬����֮ǰ�ķ����жϼ��ɡ�
	 * 
	 * ��һ�ı�������ʱ�临�Ӷȣ�����һ��������ͬһ�������{1��1��1��1��1}��target=2,
	 * ��ô����㷨�ͻὫ�������������ʱ�临�Ӷ���O(logn)����O(n)
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public boolean search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return false;
		}
		int low = 0;
		int high = A.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (target < A[mid]) {
				if (A[mid] < A[high]) {
					// �ұ�����targetһ�������
					high = mid - 1;
				} else if (A[mid] == A[high]) {
					// ��Ϊ�������ظ������ܹ������Ƿ�������high�Լ����¡�
					high--;
				} else {
					// �������
					if (target < A[low]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}

			} else if (target > A[mid]) {
				if (A[low] < A[mid]) {
					// �������targetһ�����ұ�
					low = mid + 1;
				} else if (A[low] == A[mid]) {
					// ��Ϊ�������ظ������ܹ������Ƿ�������low�Լӿ��¡�
					low++;
				} else {
					// �ұ�����
					if (target > A[high]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}

			} else
				return true;
		}
		return false;
	}

	/**
	 * �ϱ߿��Կ������ַ���������ʵʱ�临�Ӷ��Ѿ�����O(n)����ôֱ�ӽ������Բ��Ҿͺ���
	 */
	public boolean search2(int[] A, int target) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == target) {
				return true;
			}
		}
		return false;
	}
}
