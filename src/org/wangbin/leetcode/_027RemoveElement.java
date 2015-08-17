package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond
 * the new length.
 * 没啥难度
 * @author wb
 * @version 2015-8-1 下午8:26:19
 */
public class _027RemoveElement {
	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 2, 2, 2, 3 };
		A = new int[] { 4, 5 };
		int target = 4;
		System.out.println(removeElement(A, target));
		;
		System.out.println(Arrays.toString(A));
		System.out.println("~~~");
		A = new int[] { 4, 5 };
	
		System.out.println(removeElement2(A, target));
		System.out.println(Arrays.toString(A));
	}

	public static int removeElement(int[] A, int elem) {
		if (A == null || A.length == 0)
			return 0;

		int len = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != elem) {
				System.out.println(A[i] + "~~~" + A[len]);
				if (A[len] != A[i]) {
					A[len] = A[i];
				}
				len++;
			}
		}
		return len;
	}
	 public static int removeElement2(int[] A, int elem) {
	        int i = 0;
	        int pointer = A.length - 1;
	        while(i <= pointer){
	            if(A[i] == elem){
	                A[i] = A[pointer];
	                pointer--;
	            } else {
	                i++;
	            }
	        }
	        return pointer + 1;
	    }

}
