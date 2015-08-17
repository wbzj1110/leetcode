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
 * @date 2015-8-15 下午1:14:58
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
	 * 理解错题意了。case很容易误解人，加上这样的case就容易理解了 ["ab","cd"], "abcd" false
	 * 注意这个SEE匹配，是只第一行第二列的那个S匹配上了，然后匹配的二维数组右下角的2个
	 * 这道题分析看，就是一个词，在一行出现也是true
	 * ，一列出现也是true，一行往下拐弯也是true，一行往上拐弯也是true，一列往左拐弯也是true
	 * ，一列往右拐弯也是true。所以是要考虑到所有可能性
	 * ，基本思路是使用DFS来对一个起点字母上下左右搜索，看是不是含有给定的Word。还要维护一个visited数组
	 * ，表示从当前这个元素是否已经被访问过了，过了这一轮visited要回false，因为对于下一个元素，当前这个元素也应该是可以被访问的。
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
		//不能越界
		if (rowindex < 0 || colindex < 0 || rowindex >= board.length
				|| colindex >= board[0].length) {
			return false;
		}
		//如果已经访问false
		if (visited[rowindex][colindex]) {
			return false;
		}
		//匹配不上了
		if (board[rowindex][colindex] != word.charAt(index)) {
			return false;
		}
		visited[rowindex][colindex] = true;
		//前后左右看看能否匹配的上
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
