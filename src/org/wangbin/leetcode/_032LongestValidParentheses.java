package org.wangbin.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is
 * "()()", which has length = 4.
 * 
 * @author wb
 * @version 2015-8-2 ����10:42:51
 */
public class _032LongestValidParentheses {
	public static void main(String[] args) {
		String str = "()(()()";
		System.out.println(new _032LongestValidParentheses()
				.longestValidParentheses(str));
	}

	/**
	 * �������������������У��Ƚ������뵽��ջ������ݽṹ������˼·����ά��һ��ջ�����������žͽ�ջ���������������ջ�������жϵ�ǰ�Ϸ������Ƿ�Ϊ��
	 * �����С���������⿴��˼·�򵥣����������Ƚϵ���Ĳ��Լ���������˵����Ҫ�����������������ʱ����жϵ�ǰ�ĺϷ����еĳ��ȡ��ȽϽ�׳�ķ�ʽ���£�
	 * (1) �����ǰջΪ�գ���˵�����ϵ�ǰ������û�кϷ����У���Ҳ��֮ǰ�жϹ��ģ��� (2)
	 * ���򵯳�ջ��Ԫ�أ����������ջΪ�գ���˵����ǰ����ƥ�䣬���ǻ�ά��һ���Ϸ���ʼ�����start���Ϸ����еĳ��ȼ�Ϊ��ǰԪ�ص�λ��
	 * -start+1���������ջ������Ԫ�أ���ǰ�Ϸ����еĳ���Ϊ��ǰջ��Ԫ�ص�λ����һλ����ǰԪ�صľ��룬��Ϊջ��Ԫ�غ�������ŶԿ϶��ǺϷ��ģ���
	 * �������ų���ջ�ˡ� ��Ϊֻ��Ҫһ��ɨ�裬�㷨��ʱ�临�Ӷ���O(n)���ռ临�Ӷ���ջ�Ŀռ䣬�����Ƕ��������ţ�������O(n)��
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		if (s == null) {
			return 0;
		}
		int len = s.length();
		Stack<Integer> stk = new Stack<Integer>();

		int sum = 0;
		int max = 0;
		int temp =0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stk.push(i);
			} else {
				if(stk.isEmpty()){// ջΪ��ʱ������һ��')'�����Խ�sum��0
					sum=0;
				}else{
					temp = i-stk.pop()+1;
					if(stk.isEmpty()){
						//��ǰ�ߵ�һ���Ϸ�( �����ߵ�)�ľ���
						sum+=temp;
						max = Math.max(max, sum);
					}else{
						//��ǰ�ߵ�һ���Ϸ�( �����ߵ�)�ľ���
						max = Math.max(max, i - stk.peek());
					}
				}
			}
		}

		return max;
	}
}
