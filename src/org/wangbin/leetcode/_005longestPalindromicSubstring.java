package org.wangbin.leetcode;
/**
 * 1�������Ƚϻ����ַ�����һ�������뵽��KMP�㷨�������о�ing
 * 2����ķ�����������飬ά��ȫ�����ʱ�临�Ӷ�ΪO��n3��
 * 3��ö��ÿ��i����i��ʼ����ͷƥ������iΪ���ĵ������Ĵ�
 * 4����߻���һ��Manacher�㷨��ʱ�临�Ӷ���o(n)
 * http://www.cnblogs.com/bitzhuwei/p/Longest-Palindromic-Substring-Part-II.html
 * @author wb
 * @version 2015-7-25 ����1:34:55
 */
public class _005longestPalindromicSubstring {
	public static void main(String[] args) {
		String str = "abaaba";
		System.out.println(new _005longestPalindromicSubstring().longestPalindrome2(str));
	}
	/**
	 * ��ķ���
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
	 * ������ɢ��
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if(s==null||s.length()==0||s.length()==1){
			return s;
		}
		String longest =s.substring(0,1);
		for(int i = 0,len = s.length();i <len;i++){
			//��ȡiλ��
			String temp = getLongest(s,i,i);
			if(temp.length()>longest.length()){
				longest = temp;
			}
			//��ȡi��i+1λ��������ɢ
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
