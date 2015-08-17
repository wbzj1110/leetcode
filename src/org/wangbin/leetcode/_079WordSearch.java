package org.wangbin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ["ABCE"], ["SFCS"], ["ADEE"] ] word = "ABCCED", -> returns true, word =
 * "SEE", -> returns true, word = "ABCB", -> returns false.
 * 
 * @author wb
 * @date 2015-8-15 ����1:14:58
 */
public class _079WordSearch {
	public static void main(String[] args) {
		_079WordSearch ob = new _079WordSearch();
		char[][] cs = new char[][] { { 'A', 'B', 'C', 'E' },
				{ 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String word = "ABCCED";
		System.out.println(ob.exist(cs, word));
		word = "SEE";
		System.out.println(ob.exist(cs, word));
		word = "ABCB";
		System.out.println(ob.exist(cs, word));
	}

	/**
	 * ���������ˡ�case����������ˣ�����������case����������� ["ab","cd"], "abcd" false
	 * ע�����SEEƥ�䣬��ֻ��һ�еڶ��е��Ǹ�Sƥ�����ˣ�Ȼ��ƥ��Ķ�ά�������½ǵ�2��
	 * ����������������һ���ʣ���һ�г���Ҳ��true
	 * ��һ�г���Ҳ��true��һ�����¹���Ҳ��true��һ�����Ϲ���Ҳ��true��һ���������Ҳ��true
	 * ��һ�����ҹ���Ҳ��true��������Ҫ���ǵ����п�����
	 * ������˼·��ʹ��DFS����һ�������ĸ�����������������ǲ��Ǻ��и�����Word����Ҫά��һ��visited����
	 * ����ʾ�ӵ�ǰ���Ԫ���Ƿ��Ѿ������ʹ��ˣ�������һ��visitedҪ��false����Ϊ������һ��Ԫ�أ���ǰ���Ԫ��ҲӦ���ǿ��Ա����ʵġ�
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (helper(board, word, 0, i, j, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean helper(char[][] board, String word, int index, int rowindex,
			int colindex, boolean[][] visited) {
		if (index == word.length()) {
			return true;
		}
		//����Խ��
		if (rowindex < 0 || colindex < 0 || rowindex >= board.length
				|| colindex >= board[0].length) {
			return false;
		}
		//����Ѿ�����false
		if (visited[rowindex][colindex]) {
			return false;
		}
		//ƥ�䲻����
		if (board[rowindex][colindex] != word.charAt(index)) {
			return false;
		}
		visited[rowindex][colindex] = true;
		//ǰ�����ҿ����ܷ�ƥ�����
		boolean res = helper(board, word, index + 1, rowindex - 1, colindex,
				visited)
				|| helper(board, word, index + 1, rowindex + 1, colindex,
						visited)
				|| helper(board, word, index + 1, rowindex, colindex + 1,
						visited)
				|| helper(board, word, index + 1, rowindex, colindex - 1,
						visited);
		visited[rowindex][colindex] = false;
		return res;
	}
}
