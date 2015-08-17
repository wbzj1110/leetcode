package org.wangbin.leetcode;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211. Given an integer n, generate the nth
 * sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * @author wb
 * @version 2015-8-4 ����8:49:33
 */
public class _038CountandSay {
	public static void main(String[] args) {
		int i = 6;
		String str = new _038CountandSay().countAndSay(i);
		System.out.println(str);

	}
	public String countAndSay(int n) {
		if(n < 0){
			return "";
		}
		String curRes = "1";
		int start = 1;// ��1��ʼ��  ���㵽n��
		StringBuilder sb = null;
		while (start < n) {
			//��¼�м��ֵ ���� 11, 21, 1211, 111221��
			sb = new StringBuilder();
			int count = 1;
			for(int j = 1,len = curRes.length();j <len;j++){
				if(curRes.charAt(j)==curRes.charAt(j-1)){
					count++;
				}else{
					sb.append(count);
					sb.append(curRes.charAt(j-1));
					count=1;
				}
			}
			//������������ȥ ���һ��
			sb.append(count);
			sb.append(curRes.charAt(curRes.length()-1));
			curRes = sb.toString();
			start++;
		}
		return curRes;
	}

}
