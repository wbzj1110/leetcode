package org.wangbin.leetcode;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 
 * @author wb
 * @version 2015-8-6 ����8:09:10
 */
public class _041FirstMissingPositive {
	public static void main(String[] args) {
		int[] ints = new int[] { 3, 5,7 ,-1, 1 };
		// ints = new int []{1,2,3};
		System.out.println(new _041FirstMissingPositive()
				.firstMissingPositive2(ints));
		
		double b1 = 1.2;
		double GB = 1024*1024*1024;
		double _1GB = GB/b1;//1GB�洢����������
		System.out.println("_1GB: " + (_1GB + ""));
		System.out.println(System.currentTimeMillis());
		
		System.out.println("һ������� ��" + (60*60*24));
		System.out.println(URLDecoder.decode("url=http%3A%2F%2Fgongyi.weibo.com%2Fr%2F213509%3Ffrom_uid%3D2736717695&target=_blank"));
		System.out.println(URLDecoder.decode("key=tblog_card&value=click_card:3872886760416597:1042017-event&title=%5B%E5%93%81%E7%89%8C%E6%8D%90%5D%E6%8D%90%E4%B8%80%E5%85%83%20%E9%80%81%E8%90%A5%E5%85%BB&full_url=http://t.cn/RLTOYm1&short_url=http://t.cn/RLTOYm1&object_id=1042017:566a89b646fd3d2a89e80a8b17bad111&src=http%3A%2F%2Fgongyi.weibo.com%2Fr%2F213509%3Ffrom_uid%3D2736717695"));
		System.out.println(URLEncoder.encode("A��΢��s1������"));
	}

	/**
	 * ˼·�ο��� http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
	 * http://blog.csdn.net/kenden23/article/details/17099987
	 * ��������Ԫ�أ�ʹ�������е�iλ�����ֵ
	 * (i+1)�����������飬Ѱ�ҵ�һ�������ϴ�Ҫ���Ԫ�أ��������±ꡣ����������Ҫ�����������飬���Ӷ�ΪO(n)��
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
						+ A[i]);//Ϊʲô��A[A[i]-1]������Ϊ���������Ǵ�0��ʼ��
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
	 * ��һ���뷨��2��ָ���¼���ֵ����Сֵ��������ֵ������Сֵ˵�� ��������������������high++��
	 * ���low>0��С��high����=high-1������ô����low++ ��������˼·���� ���в���ȥ��
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0) {
			// ������С������
			return 1;
		}
		int low = -1;
		int high = -1;
		int temp = 0;
		for (int i = 0, len = nums.length; i < len; i++) {
			temp = nums[i];
			if (temp > 0) {
				if (low < 0 && high < 0) {
					// ��һ��
					low = temp;
					high = temp;
					continue;
				}
				if (low >= 0 && low > temp) {// ��֤low��С
					low = temp;
				}
				if (high >= 0 && high < temp) {// ��֤high���
					high = temp;
				}

			}
		}

		if (low < 0 && high < 0 || low > 1) {// ���Ǹ���,��С������1����ô�϶�ȱ1
			return 1;
		}
		// ������������� �� 3 2 1 ~~1 2 3
		if (low == high) {
			return ++high;
		}
		// �±�˵��һ�����������������
		// ����ط���Ҫ�ж�low+1��ints�Ƿ������ �����˵Ļ� ����ѭ��
		for (int i = 0, len = nums.length; i < len; i++) {
			// if()

		}

		if (low < high) {

			return ++high;
		}

		return ++high;
	}

}
