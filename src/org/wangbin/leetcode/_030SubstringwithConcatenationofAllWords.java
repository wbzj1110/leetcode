package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Substring with Concatenation of All Words
 * 
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 * 
 * @author wb
 * @version 2015-8-1 ����9:31:12
 */
public class _030SubstringwithConcatenationofAllWords {
	public static void main(String[] args) {
		String str = "barfoothefoobarman";
		String[] l = new String[] { "foo", "bar" };
		List<Integer> ls = new _030SubstringwithConcatenationofAllWords()
				.findSubstring2(str, l);
		for (Integer i : ls) {
			System.out.println(i);
		}

		str = "aaabbbc";
		l = new String[] { "a", "a", "b", "b", "c" };
		ls = new _030SubstringwithConcatenationofAllWords().findSubstring2(str,
				l);
		for (Integer i : ls) {
			System.out.println(i);
		}
		
		str = "aaa";
		l = new String[] { "a", "a" };
		ls = new _030SubstringwithConcatenationofAllWords().findSubstring2(str,
				l);
		for (Integer i : ls) {
			System.out.println(i);
		}
	}

	/**
	 * �������Ȱ��������ֵ�����HashMap��һ�£�key��word��value�����word���ֵĸ�����
	 * 
	 * ��Ϊÿ�����ʳ���һ�������ѭ��ֻ��ѭ��wordLen�Σ�ÿ��ָ��Ųһ�Σ�ÿһ��ѭ�����������ַ�����
	 * 
	 * �ڲ�ѭ��ÿ�α���һ�����ʣ�������S�ַ���������顣
	 * 
	 * ��Ҫ��ÿ�δ�ѭ��ά��һ��count�����ǲ��Ǵﵽ�˸����ֵ��ַ���������ͬʱά��һ��index����ÿ�������������ַ�������ʼindex��
	 * ��Ҫ�浽���ؽ���С�
	 * 
	 * Ϊ���ܹ�����ǲ��Ǻϸ��ַ�����������ά��һ��curDict��HashMap��
	 * 
	 * ���ȼ��һ�������ǲ�����ԭʼ�ֵ��г��֣�û���ֵĻ�˵��������ʿ϶������ϱ�׼��indexָ��ָ����һ�����ʵ���ʼ�㣬��������curDict��Ҫ����
	 * ��
	 * 
	 * ������������ԭʼ�ֵ�����ֹ����ø���ԭʼ�ֵ�ķ�������curDict�����������ʳ��ֵĴ���û�г���ԭʼ�ֵ����¼�Ĵ�������ôcount++��
	 * ��������ˣ�����ҪŲ��ָ�룬���ѳ����Ĵ�curDictɾ����
	 * 
	 * ������count�ﵽ��L��length��˵���ҵ���һ���ϸ���ַ�������ô��index���뷵�ؽ��res�У��ٰ�indexŲ����һ�����ʴ���
	 * ����curDict���ɡ�
	 * 
	 * @param S
	 * @param L
	 * @return
	 */
	public static ArrayList<Integer> findSubstring(String S, String[] L) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (S == null || L == null || S.length() == 0 || L.length == 0)
			return res;
		int wordLen = L[0].length();// same length for each word in dictionary

		// put given dictionary into hashmap with each word's count
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		for (String word : L) {
			if (!dict.containsKey(word))
				dict.put(word, 1);
			else
				dict.put(word, dict.get(word) + 1);
		}

		for (int i = 0; i < wordLen; i++) {
			int count = 0;
			int index = i;// index of each startpoint
			HashMap<String, Integer> curdict = new HashMap<String, Integer>();
			// till the first letter of last word
			for (int j = i; j <= S.length() - wordLen; j += wordLen) {
				String curWord = S.substring(j, j + wordLen);
				// check each word to tell if it existes in give dictionary
				if (!dict.containsKey(curWord)) {
					curdict.clear();
					count = 0;
					index = j + wordLen;
				} else {
					// form current dictionary
					if (!curdict.containsKey(curWord))
						curdict.put(curWord, 1);
					else
						curdict.put(curWord, curdict.get(curWord) + 1);

					// count for current found word and check if it exceed given
					// word count
					if (curdict.get(curWord) <= dict.get(curWord)) {
						count++;
					} else {
						while (curdict.get(curWord) > dict.get(curWord)) {
							String temp = S.substring(index, index + wordLen);
							curdict.put(temp, curdict.get(temp) - 1);
							if (curdict.get(temp) < dict.get(temp)) {
								count--;
							}
							index = index + wordLen;
						}
					}

					// put into res and move index point to nextword
					// and update current dictionary as well as count num
					if (count == L.length) {
						res.add(index);
						String temp = S.substring(index, index + wordLen);
						curdict.put(temp, curdict.get(temp) - 1);
						index = index + wordLen;
						count--;
					}
				}
			}// end for j
		}// end for i
		return res;
	}

	/**
	 * ���ճ������ġ�����ѭ������һ�����е�S��0��S.length-L.length*L[0].length��
	 * 
	 * @param S
	 * @param L
	 * @return
	 */
	public ArrayList<Integer> findSubstring2(String S, String[] L) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		HashMap<String, Integer> toFind = new HashMap<String, Integer>();
		HashMap<String, Integer> found = new HashMap<String, Integer>();
		int m = L.length, n = L[0].length();
		for (int i = 0; i < m; i++) {
			if (!toFind.containsKey(L[i])) {
				toFind.put(L[i], 1);
			} else {
				toFind.put(L[i], toFind.get(L[i]) + 1);
			}
		}
		for (int i = 0; i <= S.length() - n * m; i++) {
			found.clear();
			int j;
			for (j = 0; j < m; j++) {
				int k = i + j * n;
				String stub = S.substring(k, k + n);
				if (!toFind.containsKey(stub))
					break;
				if (!found.containsKey(stub)) {
					found.put(stub, 1);
				} else {
					found.put(stub, found.get(stub) + 1);
				}
				if (found.get(stub) > toFind.get(stub))
					break;
			}
			if (j == m){
				result.add(i);
			}
		}
		return result;
	}
}
