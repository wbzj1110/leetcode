package org.wangbin.leetcode;

/**
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * @author wb
 * @date 2015-8-13 ÉÏÎç8:46:26
 */
public class _069Sqrt_x_ {
	public static void main(String[] args) {
		int i = 15;
		System.out.println(new _069Sqrt_x_().sqrt(i));
		System.out.println(new _069Sqrt_x_().mySqrt(i));
		System.out.println((int)Math.sqrt(i));
	}
	public int mySqrt2(int x) {
		 return (int)Math.sqrt(x);
	}
	public int mySqrt(int x) {
		int low = 0;
		int high = x;
		long mid = 0;
		long temp =0;
		while(low<=high){
			mid = (low + high)/2;
			temp = mid *mid;
			if(temp<x){
				low = (int) (mid+1);
			}else if(temp >x){
				high = (int) (mid-1);
			}else{
				return (int) mid;
			}
		}
		return high;
	}
	public int sqrt(int x) {
        int low = 0;
        int high = x;
        while(low<=high){
            long mid = (long)(low + high)/2;
            if(mid*mid < x)
                low = (int)mid + 1;
            else if(mid*mid > x)
                high = (int)mid - 1;
            else
                return (int)mid;
        }
        return high;
    }
}
