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
 * @date 2015-8-15 ����8:26:55
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
	 * ��������/../"����Ҫ�����ϼ�Ŀ¼�������ϼ�Ŀ¼�Ƿ�Ϊ�ա�
	 * 
	 * ������"/./"���ʾ�Ǳ���Ŀ¼���������κ����������
	 * 
	 * ������"//"���ʾ�Ǳ���Ŀ¼���������κβ�����
	 * 
	 * �����������ַ����ʾ���ļ�����������򻯡�
	 * 
	 * ���ַ����ǿջ���������/../��������Ҫ����һ��"/"��
	 * 
	 * ������"/a//b"������Ҫ��Ϊ"/a/b"�� ������Щ�����ܽ᣺ �Ƚ��ַ�����"/"�ָ������Ȼ����ÿ���ָ�������ַ�����
	 * 
	 * ���ַ���Ϊ�ջ���Ϊ"."�������κβ�����
	 * 
	 * ���ַ�����Ϊ".."�����ַ�����ջ��
	 * 
	 * ���ַ���Ϊ"..", ��ջ�������ϼ�Ŀ¼����
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
			if(s.length()==0||s.equals(".")){//.����ǰĿ¼
				continue;
			}else if(s.equals("..")){//��һĿ¼
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
		if(sb.length()==0){//����/��//�����
			return new String("/");
		}
		return sb.toString();
	}
}
