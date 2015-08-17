package org.wangbin.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not. 用栈解决即可。
 * 
 * @author wb
 * @version 2015-7-31 上午9:17:49
 */
public class _020ValidParentheses {
	public static void main(String[] args) {
		String src = "()[]{}";
		String src2 = "([)]";
		System.out.println(isValid(src));
		System.out.println(isValid(src2));
	}

	private static boolean isValid(String s) {
		if (s == null || s.length() <= 1) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		char[] cs = s.toCharArray();
		char c = '0';
		char top = '0';
		for (int i = 0, len = cs.length; i < len; i++) {
			c = cs[i];
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.size() == 0) {
					return false;
				}
				top = stack.pop();
				if (c == ')') {
					if (top != '(') {
						return false;
					}
				} else if (c == '}') {
					if (top != '{') {
						return false;
					}
				} else if (c == ']') {
					if (top != '[') {
						return false;
					}
				}
			}
		}

		return stack.size()==0;
	}
	/**
	 * 更优雅的写法
	 * @param s
	 * @return
	 */
	private static boolean isValid2(String s) {
		if (s == null || s.length() <= 1) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		String index = "({[";
		for(Character c :s.toCharArray()){
			if(index.contains(String.valueOf(c))){
				stack.push(c);
			}else{
				if(!stack.isEmpty()&&isValidChars(stack.peek(),c)){
					stack.pop();
				}else{
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}

	private static boolean isValidChars(Character c1, Character c2) {
		// TODO Auto-generated method stub
		return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
				|| (c1 == '[' && c2 == ']');
	}
	
}
