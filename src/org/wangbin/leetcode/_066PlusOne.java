package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, plus one to
 * the number.
 * 
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 * 
 * @author wb
 * @date 2015-8-11 ÉÏÎç9:24:41
 */
public class _066PlusOne {
	public static void main(String[] args) {
		int []ints = new int[]{1,2,3};
		ints = new int[]{9,9,9};
//		ints = new int[]{9,0,9};
		System.out.println(Arrays.toString(new _066PlusOne().plusOne(ints)));
	}
	
	public int[] plusOne(int[] digits) {
        int length;
        length = digits.length;
        for(int i = length-1; i>=0; i--){
            if(digits[i]<9){
                digits[i]++;
                break;
            }else{
                digits[i]=0;
            }
        }
        
        if(digits[0]==0){
        	int[] newdigits = new int[digits.length+1];
            System.arraycopy(newdigits, 1, digits, 0, digits.length-1);
            newdigits[0]=1;
            return newdigits;
        }else{
           return digits;
        }
    }
}
