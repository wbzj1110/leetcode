package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0 01 - 1 11 - 3 10 - 2 Note: For a given n, a gray code sequence is not
 * uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 * 
 * @author wb
 * @date 2015-8-20 ����9:01:41
 */
public class _089GrayCode {
	public static void main(String[] args) {
		int n =2;
		Util.showList(new _089GrayCode().grayCode(n));
		System.out.println(System.currentTimeMillis()/1000);
		List<Long> ls = new ArrayList<Long>();
		ls.add(123l);
//		ls.add(456l);
		ls.add(123l);
		ls.add(123l);
		Collection<Long>  result= removeElement(ls);
		if(result.size()==0){
			System.out.println("true");
		}
		for(Long lon :result){
			System.out.println(lon);
		}
		String value = "123456";
		System.out.println(value.substring(0, value.length()));
		System.out.println(value.substring(0, value.length() - 1));
		System.out.println(StringUtils.isEmpty(""));
	}

	private static Collection<Long> removeElement(Collection<Long> ls) {
		// TODO Auto-generated method stub
//		Iterator<Long> it = ls.iterator();
//		while(it.hasNext()){
//			Long lon = it.next();
//			if(lon==123l){
//				it.remove();
//			}
//		}
		ls.remove(123l);
		return ls;
	}

	/**
	 * ��Ŀ���˰��춼��֪����˵ɶ��˼���������� �������ο��˱��˵ġ�������β�֪���������� nλ�ĸ������������ֹ��ɣ�һ������n-1λ�����룬�ټ���
	 * 1<<(n-1)��n-1λ�������������������������0132���2310���֣��ĺ͡�
	 * 
	 * 
	 * 
	 * 1λ���������������� (n+1)λ�������е�ǰ2^n�����ֵ���nλ����������֣���˳����д����ǰ׺0
	 * (n+1)λ�������еĺ�2^n�����ֵ���nλ����������֣���������д����ǰ׺1��
	 * 
	 * �����Ƕ����ƣ������λ��0��ԭ����������û�иı䣬����ȡ����һλ����ĸ����������ټ���������1�ķ������ǵ�ǰ��λ������Ľ���ˡ�
	 * 
	 * n = 0ʱ��[0]
	 * 
	 * n = 1ʱ��[0,1]
	 * 
	 * n = 2ʱ��[00,01,11,10]
	 * 
	 * n = 3ʱ��[000,001,011,010,110,111,101,100]
	 * 
	 * 
	 * 
	 * ��n=1ʱ��0��1
	 * 
	 * ��n=2ʱ��ԭ����list 0��1���䣬ֻ��ǰ����ʽ�ϼ��˸�0���00��01��Ȼ�������1<<1Ϊ10�����Σ�10+1=11
	 * 10+0=10�����Ϊ��00 01 11 10
	 * 
	 * ��n=3ʱ��ԭ����list 00,01,11, 10������Ϊ��10��11��01��00��������1<<2Ϊ100���������Ϊ��100+10=110,
	 * 100+11=111,100+01=101, 100+00= 100��
	 * 
	 * ���ս��Ϊ000 001 011 010 110 111 101 100 ���ǿ���ʹ�õݹ������������ǣ�
	 * 
	 * һ������n-1λ�����룬�ټ��� 1<<(n-1)��n-1λ�����������ĺ͡�
	 * ��ַ���ӣ�http://zh.wikipedia.org/wiki/������
	 * ��java��������ȼ�
	 * http://blog.csdn.net/xiaoli_feng/article/details/4567184
	 * @param n
	 * @return
	 */
	public List<Integer> grayCode(int n) {
		if (n == 0) {
			List<Integer> result = new ArrayList<Integer>();
			result.add(0);
			return result;
		}

		List<Integer> result = grayCode(n - 1);
		int addNumber = 1 << (n - 1);
		int originalsize = result.size();

		for (int i = originalsize - 1; i >= 0; i--) {
			result.add(addNumber + result.get(i));
		}
		return result;
	}
}
