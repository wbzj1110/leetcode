package org.wangbin.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 * 
 * Corner Cases: Did you consider the case where path = "/../"? In this case,
 * you should return "/". Another corner case is the path might contain multiple
 * slashes '/' together, such as "/home//foo/". In this case, you should ignore
 * redundant slashes and return "/home/foo".
 * 
 * @author wb
 * @date 2015-8-15 上午8:26:55
 */
public class _071SimplifyPath {
	public static void main(String[] args) {
		String str = new String("/home/");
		_071SimplifyPath ob = new _071SimplifyPath();
		System.out.println(ob.simplifyPath(str));
		str = "/a/./b/../../c/";
		System.out.println(ob.simplifyPath(str));
		str = "/../";
		System.out.println(ob.simplifyPath(str));
		str = "/home//foo/";
		System.out.println(ob.simplifyPath(str));
		str = "//";
		System.out.println(ob.simplifyPath(str));
	}

	/**
	 * 当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。
	 * 
	 * 当遇到"/./"则表示是本级目录，无需做任何特殊操作。
	 * 
	 * 当遇到"//"则表示是本级目录，无需做任何操作。
	 * 
	 * 当遇到其他字符则表示是文件夹名，无需简化。
	 * 
	 * 当字符串是空或者遇到”/../”，则需要返回一个"/"。
	 * 
	 * 当遇见"/a//b"，则需要简化为"/a/b"。 根据这些需求，总结： 先将字符串依"/"分割出来，然后检查每个分割出来的字符串。
	 * 
	 * 当字符串为空或者为"."，不做任何操作。
	 * 
	 * 当字符串不为".."，则将字符串入栈。
	 * 
	 * 当字符串为"..", 则弹栈（返回上级目录）。
	 * 
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		if(path==null||path.length()==0){
			return path;
		}
		String []str = path.split("/");
		LinkedList<String> stack = new LinkedList<String>();  
		for(String s :str){
			if(s.length()==0||s.equals(".")){//.代表当前目录
				continue;
			}else if(s.equals("..")){//上一目录
				if(!stack.isEmpty()){
					stack.pop();
				}
			}else{
				stack.push(s);
			}
		}
		StringBuilder sb = new StringBuilder("");
		while(!stack.isEmpty()){
			sb.append("/").append(stack.removeLast());
		}
		if(sb.length()==0){//对于/与//的情况
			return new String("/");
		}
		return sb.toString();
	}
}
