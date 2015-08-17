package org.wangbin.leetcode;
/**
 * java实现逆转方便，主要是考虑下边界情况
 * @author wb
 * @version 2015-7-25 下午2:02:30
 */
public class _007ReverseInteger {
	public static void main(String[] args) {
		int x = -123456;
		System.out.println(new _007ReverseInteger().reverse(x));
		
	}
	public int reverse(int x) {
		int max =Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		if(x>max||x<min){
			return 0;
		}
		String sign = "";
		String temp = String.valueOf(x);
		StringBuilder sb  = null;
		if(temp.startsWith("-")){
			sign = "-";
			sb = new StringBuilder(temp.substring(1, temp.length()));
		}else{
			sb = new StringBuilder(temp);
		}
		temp = sb.reverse().toString();
		if(sign.equals("-")){
			temp = "-"+temp;
		}
		if(Long.valueOf(temp)>max||Long.valueOf(temp)<min){
			return 0;
		}
		return Integer.valueOf(temp);
	}
	
	
	
}
