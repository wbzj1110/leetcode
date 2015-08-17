package org.wangbin.leetcode;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples. 
 * [1,3,5,6], 5 ¡ú 2 
 * [1,3,5,6], 2 ¡ú 1 
 * [1,3,5,6], 7 ¡ú 4
 * [1,3,5,6], 0 ¡ú 0
 * 
 * @author wb
 * @version 2015-8-5 ÉÏÎç8:27:29
 */
public class _035SearchInsertPosition {
	public static void main(String[] args) {
		int ints[] = new int[]{1,3,5,6};
		int target = 0;
		System.out.println(new _035SearchInsertPosition().searchInsert(ints, target));
		
		
	}
	 public int searchInsert(int[] A, int target) {
		 if(A==null||A.length==0){
			 return 0;
		 }
		 int low = 0;
		 for(int i = 0,len = A.length;i <len;i++){
//			 System.out.println(A[i] +"~~"+target);
			 if(A[i] == target){
				 return i;
			 }
			 if(A[low] < target){
				 low++;
			 }
		 }
		 return low;
	 }
	
	
}
