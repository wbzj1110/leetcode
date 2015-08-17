package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * For example, words: ["This", "is", "an", "example", "of", "text",
 * "justification."] L: 16.
 * 
 * Return the formatted lines as: [ "This    is    an", "example  of text",
 * "justification.  " ] Note: Each word is guaranteed not to exceed L in length.
 * 
 * click to show corner cases.
 * 
 * Corner Cases: A line other than the last line might contain only one word.
 * What should you do in this case? In this case, that line should be
 * left-justified.
 * 
 * @author wb
 * @date 2015-8-13 ����8:45:30
 */
public class _068TextJustification {
	public static void main(String[] args) {
		
		
		Map<String,String> maps =null;
		for(Map.Entry<String, String> entry :maps.entrySet()){
			
		}
		System.out.println("end");
		
	}

	/**
	 * ���潲��������Code
	 * Ganker��http://blog.csdn.net/linhuanmars/article/details/24063271
	 * �������벿������ע�Ͷ��������¡�
	 * 
	 * ���� �������ڴ�����ַ���������Ҫ��һ�����ʰ��ųɶ����޶����ȵ��ַ�������Ҫ�ѵ����ڿո�İ��ţ�����ÿ������֮������пո������
	 * ������ǰ�зŲ��¸���� ���ʲ����ַ��ֲ�����������Lʱ������Ҫ�ѿո���ȵ�����ڵ���֮�䡣���ʣ��Ŀո����պ��Ǽ��������ô�;��ȷ��伴�ɣ�
	 * ���򻹱���Ѷ��һ���ո�ŵ�
	 * ǰ��ļ�����档ʵ��������ά��һ��count������¼��ǰ���ȣ�����֮�����Ǽ��㹲ͬ�Ŀո����Լ����һ���Ŀո�����Ȼ�󽫵����ַ���������������һ
	 * ��ϸ�ھ������һ�в���Ҫ���ȷ���ո񣬾�β���վͿ��ԣ�����Ҫ��������һ�¡�ʱ����������Ҫɨ�赥��һ�飬Ȼ�����ҵ���β��ʱ����ɨ��һ�鵱ǰ�еĵ�
	 * �ʣ���������ÿ�����ʲ��ᱻ���ʳ������飬��������ʱ�临�Ӷ���O(n)�����ռ临�Ӷ����ǽ���Ĵ�С�������������ͳ����йأ�����׼ȷ���壬���֪����
	 * ������r������O(r*L)�����������£���
	 */
	public List<String> fullJustify(String[] words, int L) {
		List<String> res = new ArrayList<String>();
		if (words == null || words.length == 0)
			return res;
		int count = 0;
		int last = 0;
		for (int i = 0; i < words.length; i++) {
			// count����һ�μ���ĵ��ʵĳ��ȣ�words[i].length()�ǵ�ǰ���Էŵ�һ�����ʵĳ��ȣ�
			// ���赱ǰ������������ʣ���ô��һ�е��ʸ����ʼ�ļ��������i-last
			// �ж���Щ�ܵĳ��ȼ������ǲ��Ǵ���L���������ˣ�
			if (count + words[i].length() + (i - last) > L) {
				int spaceNum = 0;
				int extraNum = 0;
				// ��Ϊ���Ե�words[i]ʧ���ˣ����Լ������1.��ʱ�ж�ʣ��ļ�����Ƿ����0
				if (i - last - 1 > 0) {
					// �Ǽ���ı�����ΪɶҪ��1����Ϊ���Ե�ǰwords[i]���ֱ�L���ˣ�
					// ���Ե�ǰ������ʲ����������У����Լ���ͼ���һ��
					spaceNum = (L - count) / (i - last - 1);
					extraNum = (L - count) % (i - last - 1);// ���Ǳ����Ļ���Ҫ����
				}
				StringBuilder str = new StringBuilder();
				for (int j = last; j < i; j++) {
					str.append(words[j]);
					if (j < i - 1) {// words[i-1]�Ļ�����Ͳ�����ո��ˣ���������j<i-1
						for (int k = 0; k < spaceNum; k++)
							str.append(" ");

						if (extraNum > 0)
							str.append(" ");

						extraNum--;
					}
				}

				// �������forѭ��������һ��ֻ��һ�����ʻ�û����һ�е����
				for (int j = str.length(); j < L; j++)
					str.append(" ");

				res.add(str.toString());
				count = 0;
				last = i;// ��һ����ʼ�ĵ���
			}
			count += words[i].length();
		}

		// �������һ��
		StringBuilder str = new StringBuilder();
		for (int i = last; i < words.length; i++) {
			str.append(words[i]);
			if (str.length() < L)
				str.append(" ");
		}
		for (int i = str.length(); i < L; i++)
			str.append(" ");

		res.add(str.toString());
		return res;
	}
}
