package org.wangbin.leetcode;
/**
 * Implement pow(x, n).
 * @author wb
 * @date 2015-8-8 下午1:03:12
 */
public class _050Implementpow_x_n {
	public static void main(String[] args) {
		int x = 2;
		int n = 4;
		System.out.println(new _050Implementpow_x_n().myPow(x, n));
	}
	/**
	 * 二分法~~结束
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if(x==1){
			return 1;
		}
		if(x==0){
			return 0;
		}
		if(n >0){
			return helper(x,n);
		}else{
			return 1/helper(x, -n);
		}
		
	}

	private double helper(double x, int n) {
		// TODO Auto-generated method stub
		if(n==0){
			return 1;
		}
		
		double temp = helper(x, n/2);
		if(n%2==0){
			return temp *temp;
		}else{
			return temp*temp*x;
		}
		
	}
}
