package org.wangbin.leetcode;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column. 1,2,3 → 1,3,2 3,2,1 →
 * 1,2,3 1,1,5 → 1,5,1
 * 
 * @author wb
 * @version 2015-8-2 上午9:51:26
 */
public class _031NextPermutation {
	public static void main(String[] args) {
		int[] ints = new int[] { 2, 3, 6, 5, 4, 1 };
//		ints = new int[] { 2, 4, 6, 5, 3, 1 };
		ints = new int[] { 1,2 };
		new _031NextPermutation().nextPermutation2(ints);
		for (int i : ints) {
			System.out.print(i + "  ");
		}
		System.out.println();

	}

	/**
	 * “这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，主要的问题是经常不是一见到这个题就能马上理清思路。
	 * 下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样： 1)
	 * 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1; 2) 接下来分两种情况： (1)
	 * 如果上面的数字都是依次增长的
	 * ，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
	 * (2)
	 * 否则，如果p存在，从p开始往后找，找找找，找到第一个比他小的数的前一个位置，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。
	 * 最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
	 * 以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。
	 * 再例如2, 4, 6, 5, 3, 1  转换为2,5,1,3,4,6
	 * @param num
	 */
	public void nextPermutation(int[] num) {
		if (num == null || num.length == 0)
			return;
		int i = num.length - 2;
		while (i >= 0 && num[i] >= num[i + 1])
			i--;

		if (i >= 0) {
			int j = i + 1;
			while (j < num.length && num[j] > num[i])
				j++;
			j--;
			swap(num, i, j);
		}
		reverse(num, i + 1, num.length - 1);
	}

	private void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}

	private void reverse(int[] num, int i, int j) {
		while (i < j)
			swap(num, i++, j--);
	}

	
	public void nextPermutation2(int[] num) {
		if(num==null||num.length<=1){
			return ;
		}
		//从后往前找 找到第一个递减的位置
		int index = -1;
		int len = num.length;
		for(int i = len-2;i >=0;i--){
			if(num[i]<num[i+1]){
				index=i;
				break;
			}
		}
		if(index==-1){
			//如果从后往前都是一次递增，直接倒过来就好了
			reverse2(num, 0, len-1);
			return;
		}
		//从index往后比较，找到第一个比num[index]小的元素，交换，然后再把[index+1,len-1]位置的元素逆序 搞定
		for(int i =index +1;i <len;i++){
			if(index!=i && num[index] >num[i]){
				swap(num, index, i-1);
				reverse2(num, index+1, len-1);
				break;
			}
		}
	}
	
	private void reverse2(int[] num, int start, int end) {
		// TODO Auto-generated method stub
		for(int i =start,j=end;i<j;i++,j--){
			swap(num, i, j);
		}
	}
}
