package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * @author wb
 * @version 2015-7-31 ����9:45:12
 */
public class _022GenerateParentheses {
	public static void main(String[] args) {
		
		List<String> ls = new _022GenerateParentheses().generateParenthesis(3);
		for(String s:ls){
			System.out.println(s);
		}
		
	}
	/**
	 * �ݹ�
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		List<String> ls = new ArrayList<String>();
		rec(n,0,0,"",ls);
		return ls;
	}
	private static void rec(int n, int left, int right, String s, List<String> ls) {
		// TODO Auto-generated method stub
		//����С����
		if(left < right){
			return ;
		}
		//�������Ŷ�������
		if(left==n&&right==n){
			ls.add(s);
			return;
		}
		//�����ŷ���֮���������
		if(left==n){
			rec(n, left, right+1, s+")", ls);
			return;
		}
		//���������
		rec(n, left+1, right, s+"(", ls);
		//���������
		rec(n, left, right+1, s+")", ls);
	}
}
