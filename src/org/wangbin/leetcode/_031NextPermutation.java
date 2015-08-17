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
 * corresponding outputs are in the right-hand column. 1,2,3 �� 1,3,2 3,2,1 ��
 * 1,2,3 1,1,5 �� 1,5,1
 * 
 * @author wb
 * @version 2015-8-2 ����9:51:26
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
	 * ��������Ǹ���һ�������һ�����У�����һ�����С��㷨����ʵû��ʲô�ر�ĵط�����Ҫ�������Ǿ�������һ��������������������˼·��
	 * ����������һ��������˵��������������(2,3,6,5,4,1)������һ�����еĻ��������������� 1)
	 * �ȴӺ���ǰ�ҵ���һ����������������������¼��λ��p�����������е�3����Ӧ��λ����1; 2) ����������������� (1)
	 * �����������ֶ�������������
	 * ����ô˵���������һ�����У���һ�����ǵ�һ������ʵ���������ַ�ת��������(����(6,5,4,3,2,1)��һ����(1,2,3,4,5,6));
	 * (2)
	 * �������p���ڣ���p��ʼ�����ң������ң��ҵ���һ������С������ǰһ��λ�ã�Ȼ����������λ�ã����������е�4������λ�ú�õ�(2,4,6,5,3,1)��
	 * ����p֮����������ֵ��򣬱��������еõ�(2,4,1,3,5,6), �������Ҫ�����һ�����С�
	 * ���Ϸ����У�������Ҫɨ���������Σ�����ʱ�临�Ӷ���O(3*n)=O(n)���ռ临�Ӷ���O(1)��
	 * ������2, 4, 6, 5, 3, 1  ת��Ϊ2,5,1,3,4,6
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
		//�Ӻ���ǰ�� �ҵ���һ���ݼ���λ��
		int index = -1;
		int len = num.length;
		for(int i = len-2;i >=0;i--){
			if(num[i]<num[i+1]){
				index=i;
				break;
			}
		}
		if(index==-1){
			//����Ӻ���ǰ����һ�ε�����ֱ�ӵ������ͺ���
			reverse2(num, 0, len-1);
			return;
		}
		//��index����Ƚϣ��ҵ���һ����num[index]С��Ԫ�أ�������Ȼ���ٰ�[index+1,len-1]λ�õ�Ԫ������ �㶨
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
