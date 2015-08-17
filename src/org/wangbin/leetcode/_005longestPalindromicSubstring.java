package org.wangbin.leetcode;
/**
 * 1、看见比较会问字符串第一个就是想到了KMP算法，继续研究ing
 * 2、最笨的方法，挨个检查，维护全局最长，时间复杂度为O（n3）
 * 3、枚举每个i，从i开始向两头匹配找以i为中心的最大回文串
 * 4、这边还有一个Manacher算法，时间复杂度是o(n)
 * http://www.cnblogs.com/bitzhuwei/p/Longest-Palindromic-Substring-Part-II.html
 * @author wb
 * @version 2015-7-25 下午1:34:55
 */
public class _005longestPalindromicSubstring {
	public static void main(String[] args) {
		String str = "abaaba";
		System.out.println(new _005longestPalindromicSubstring().longestPalindrome2(str));
	}
	/**
	 * 最笨的方法
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		 
	    int maxPalinLength = 0;
	    String longestPalindrome = null;
	    int length = s.length();
	 
	    // check all possible sub strings
	    for (int i = 0; i < length; i++) {
	        for (int j = i + 1; j < length; j++) {
	            int len = j - i;
	            String curr = s.substring(i, j + 1);
	            if (isPalindrome(curr)) {
	                if (len > maxPalinLength) {
	                    longestPalindrome = curr;
	                    maxPalinLength = len;
	                }
	            }
	        }
	    }
	 
	    return longestPalindrome;
	}
	 
	private boolean isPalindrome(String s) {
	 
	    for (int i = 0; i < s.length() - 1; i++) {
	        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
	            return false;
	        }
	    }
	 
	    return true;
	}
	/**
	 * 中心扩散法
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if(s==null||s.length()==0||s.length()==1){
			return s;
		}
		String longest =s.substring(0,1);
		for(int i = 0,len = s.length();i <len;i++){
			//获取i位置
			String temp = getLongest(s,i,i);
			if(temp.length()>longest.length()){
				longest = temp;
			}
			//获取i，i+1位置向外扩散
			 temp = getLongest(s,i,i+1);
			if(temp.length()>longest.length()){
				longest = temp;
			}
			
		}
		
		
		return longest;
	}
	private String getLongest(String s, int begin, int end) {
		while(begin>=0&&end<=s.length()-1&&s.charAt(begin)==s.charAt(end)){
			begin--;
			end++;
		}
		return s.substring(begin+1,end);
	}
	
}
