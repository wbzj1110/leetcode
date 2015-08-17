package org.wangbin.leetcode;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 
 * @author wb
 * @version 2015-8-6 上午8:09:10
 */
public class _041FirstMissingPositive {
	public static void main(String[] args) {
		int[] ints = new int[] { 3, 5,7 ,-1, 1 };
		// ints = new int []{1,2,3};
		System.out.println(new _041FirstMissingPositive()
				.firstMissingPositive2(ints));
		
		double b1 = 1.2;
		double GB = 1024*1024*1024;
		double _1GB = GB/b1;//1GB存储多少条数据
		System.out.println("_1GB: " + (_1GB + ""));
		System.out.println(System.currentTimeMillis());
		
		System.out.println("一天的秒数 ：" + (60*60*24));
		System.out.println(URLDecoder.decode("url=http%3A%2F%2Fgongyi.weibo.com%2Fr%2F213509%3Ffrom_uid%3D2736717695&target=_blank"));
		System.out.println(URLDecoder.decode("key=tblog_card&value=click_card:3872886760416597:1042017-event&title=%5B%E5%93%81%E7%89%8C%E6%8D%90%5D%E6%8D%90%E4%B8%80%E5%85%83%20%E9%80%81%E8%90%A5%E5%85%BB&full_url=http://t.cn/RLTOYm1&short_url=http://t.cn/RLTOYm1&object_id=1042017:566a89b646fd3d2a89e80a8b17bad111&src=http%3A%2F%2Fgongyi.weibo.com%2Fr%2F213509%3Ffrom_uid%3D2736717695"));
		System.out.println(URLEncoder.encode("A对微博s1的评论"));
	}

	/**
	 * 思路参考： http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
	 * http://blog.csdn.net/kenden23/article/details/17099987
	 * 交换数组元素，使得数组中第i位存放数值
	 * (i+1)。最后遍历数组，寻找第一个不符合此要求的元素，返回其下标。整个过程需要遍历两次数组，复杂度为O(n)。
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive2(int[] A) {
		if (A == null || A.length == 0) {
			return 1;
		}
		int len = A.length;
		for (int i = 0; i < len; i++) {
			if (A[i] <= len && A[i] > 0 && A[A[i] - 1] != A[i]) {
				System.out.println((A[i] - 1) + "~~~" + A[A[i] - 1] + "~~~"
						+ A[i]);//为什么是A[A[i]-1]来，因为数组坐标是从0开始的
				int temp = A[A[i] - 1];
				A[A[i] - 1] = A[i];
				A[i] = temp;
				i--;
			}
		}
		System.out.println(Arrays.toString(A));
		for (int i = 0; i < len; i++) {
			if (A[i] != i + 1){
				return i + 1;
			}
		}

		return len + 1;
	}

	/**
	 * 第一个想法，2个指针记录最大值与最小值，如果最大值等于最小值说明 都是连续的正数，返回high++；
	 * 如果low>0且小于high（！=high-1），那么返回low++ ！！明显思路不对 进行不下去了
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0) {
			// 返回最小的正数
			return 1;
		}
		int low = -1;
		int high = -1;
		int temp = 0;
		for (int i = 0, len = nums.length; i < len; i++) {
			temp = nums[i];
			if (temp > 0) {
				if (low < 0 && high < 0) {
					// 第一次
					low = temp;
					high = temp;
					continue;
				}
				if (low >= 0 && low > temp) {// 保证low最小
					low = temp;
				}
				if (high >= 0 && high < temp) {// 保证high最大
					high = temp;
				}

			}
		}

		if (low < 0 && high < 0 || low > 1) {// 都是负数,最小数不是1，那么肯定缺1
			return 1;
		}
		// 这个是连续的数 如 3 2 1 ~~1 2 3
		if (low == high) {
			return ++high;
		}
		// 下边说明一定不会粗线连续的书
		// 这个地方需要判断low+1在ints是否出现了 出现了的话 继续循环
		for (int i = 0, len = nums.length; i < len; i++) {
			// if()

		}

		if (low < high) {

			return ++high;
		}

		return ++high;
	}

}
