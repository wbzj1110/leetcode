package org.wangbin.leetcode;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to
 * two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 * great / \ gr eat / \ / \ g r e at / \ a t To scramble the string, we may
 * choose any non-leaf node and swap its two children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it
 * produces a scrambled string "rgeat".
 * 
 * rgeat / \ rg eat / \ / \ r g e at / \ a t We say that "rgeat" is a scrambled
 * string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it
 * produces a scrambled string "rgtae".
 * 
 * rgtae / \ rg tae / \ / \ r g ta e / \ t a We say that "rgtae" is a scrambled
 * string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a
 * scrambled string of s1.
 * 
 * @author wb
 * @date 2015-8-19 ����8:57:39
 */
public class _087ScrambleString {
	public static void main(String[] args) {
		String str1 = "great";
		String str2 = "rgtae";
		System.out.println(new _087ScrambleString().isScramble(str1, str2));
	}

	/**
	 * ....���� �Ҿ��뵽�ˡ����ݹ飬��Ӽ����жϣ�������������������ά�Ķ�̬�滮������ֻ��˵NB�ǡ���
	 * �򵥵�˵������s1��s2��scramble�Ļ�����ô��Ȼ����һ����s1�ϵĳ���l1����s1�ֳ�s11��s12���Σ�ͬ����s21��s22��
	 * ��ôҪôs11��s21��scramble�Ĳ���s12��s22��scramble�ģ�
	 * Ҫôs11��s22��scramble�Ĳ���s12��s21��scramble��
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		if (s1.length() == 0 || s1.equals(s2)) {
			return true;
		}
		// ���ٲ���Ҫ���ж�
		if (!(isEqualByElement(s1).equals(isEqualByElement(s2)))) {
			return false;
		}
		for (int i = 1; i <= s1.length() - 1; i++) {
			String s11 = s1.substring(0, i);
			String s12 = s1.substring(i);
			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i);

			if (isScramble(s11, s21) && isScramble(s12, s22)) {
				return true;
			}
			s21 = s2.substring(0, s2.length() - i);
			s22 = s2.substring(s2.length() - i);
			if ((isScramble(s11, s22) && isScramble(s12, s21))) {
				return true;
			}
		}
		return false;
	}

	private String isEqualByElement(String s) {
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
}
