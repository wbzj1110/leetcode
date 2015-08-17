package org.wangbin.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 * ����˼·��������map��taget��ȥi��i������λ�õ���������ʣ�µ�ֵmap��û�У���ѭ����
 * @author wb
 * @version 2015-7-25 ����9:33:19
 */
public class _001TwoSum {
	public static void main(String[] args) {
		int [] is = new int []{2,7,11,15};
		int target = 9;
		System.out.println(Arrays.toString(twoSum(is, 22)));
	}
	public static int[] twoSum(int[] numbers, int target){
		if(numbers==null){
			return null;
		}
		int len = numbers.length;
		if(len ==0 ||len <2){
			return null;
		}
		Map<Integer, Integer>maps = new HashMap<Integer, Integer>(len);
		for(int i = 0;i <len;i++){
			maps.put(numbers[i],i);
		}
		//��ʼ����
		for(int i = 0;i <len;i++){
			if(maps.containsKey(target - numbers[i])){
				int index2 = maps.get(target - numbers[i]);
				if(i==index2){
					continue;
				}else{
					return new int []{i+1,index2+1};
				}
			}
		}
		return null;
	}
}
