package org.wangbin.leetcode;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * For example, Given s = "Hello World", return 5.
 * 这题目真TM水啊。。。
 * @author wb
 * @date 2015-8-8 下午9:34:58
 */
public class _058LengthofLastWord {
	public static void main(String[] args) {
		String  s = "Hello World";
		System.out.println(new _058LengthofLastWord().lengthOfLastWord(s));
		
	}
	public int lengthOfLastWord(String s) {
		if(s==null ||s.length()==0){
			return 0;
		}
		s =s.trim();
		if(s.length()==0){
			return 0;
		}
		int firstSpac = s.lastIndexOf(" ");
		if(firstSpac==-1){
			//没有空格
			return s.length();
		}else{
			return s.substring(firstSpac+1, s.length()).length();
		}
		
	}
	
}
