package org.wangbin.leetcode;
/**
 * Êı×ÖÄæ×ª
 * @author wb
 * @version 2015-7-25 ÏÂÎç2:47:08
 */
public class _009PalindromeNumber {
	public static void main(String[] args) {
		int x = 123321;
		System.out.println(new _009PalindromeNumber().isPalindrome(x));
		
		
	}
	public boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}
		int rest = 0;
		int y = x;
		while(x!=0){
			rest = rest*10+x%10;
			x = x/10;
		}
		return y==rest;
		
		
	}
	public boolean isPalindrome2(int x) {
		 if(x <0){
			 return false;
		 }
		 String s = x+"";
		 String s2 = new StringBuilder(s).reverse().toString();
		 if(s.equals(s2)){
			 return true;
		 }
		 
		return false; 
		
		
	}
}
