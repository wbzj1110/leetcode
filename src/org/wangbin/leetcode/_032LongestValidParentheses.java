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
 * @version 2015-8-2 上午10:42:51
 */
public class _032LongestValidParentheses {
	public static void main(String[] args) {
		String str = "()(()()";
		System.out.println(new _032LongestValidParentheses()
				.longestValidParentheses(str));
	}

	/**
	 * 这道题是求最长的括号序列，比较容易想到用栈这个数据结构。基本思路就是维护一个栈，遇到左括号就进栈，遇到右括号则出栈，并且判断当前合法序列是否为最
	 * 长序列。不过这道题看似思路简单，但是有许多比较刁钻的测试集。具体来说，主要问题就是遇到右括号时如何判断当前的合法序列的长度。比较健壮的方式如下：
	 * (1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）； (2)
	 * 否则弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置
	 * -start+1；否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而
	 * 且左括号出过栈了。 因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)。
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
				if(stk.isEmpty()){// 栈为空时，出现一个')'，可以将sum置0
					sum=0;
				}else{
					temp = i-stk.pop()+1;
					if(stk.isEmpty()){
						//最前边的一个合法( 与最后边的)的距离
						sum+=temp;
						max = Math.max(max, sum);
					}else{
						//最前边的一个合法( 与最后边的)的距离
						max = Math.max(max, i - stk.peek());
					}
				}
			}
		}

		return max;
	}
}
