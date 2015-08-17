package org.wangbin.leetcode;
/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * @author wb
 * @version 2015-7-28 上午9:45:59
 */
public class _014LongestCommonPrefix {
	public static void main(String[] args) {
		String [] strs = new String[]{"aa","a"};
		System.out.println(new _014LongestCommonPrefix().longestCommonPrefix(strs));
		System.out.println(new _014LongestCommonPrefix().longestCommonPrefix2(strs));
	}
	/**
	 * 方法一，取第一个的char依次比较
	 * @param strs
	 * @return
	 */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        
        StringBuilder res = new StringBuilder();
        int index = 0;
        int len = minlen(strs);
        while (index < len){
            for (int i=1; i<strs.length;i++){
                if (strs[i].charAt(index) != strs[0].charAt(index))
                    return res.toString();
            }
            res.append(strs[0].charAt(index));
            index++;
        }
        return res.toString();
    }
    private int minlen(String[] strs) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<strs.length;i++)
            min = Math.min(min,strs[i].length());
        return min;
    }
    /**
     * 方法二字符串比较
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
    	if(strs==null||strs.length==0){
    		return "";
    	}
    	if(strs.length==1){
    		return strs[0];
    	}
    	String prefix = strs[0];
    	for(int i= 1,len = strs.length;i <len;i++){
    		int j = 0;
    		while(j<prefix.length()&&j<strs[i].length()&&(strs[i].charAt(j)==prefix.charAt(j))){
    			j++;
    		}
    		if(j==0){
    			return "";
    		}
    		prefix = prefix.substring(0, j);
    	}
    	
    	return prefix;
    }
}
