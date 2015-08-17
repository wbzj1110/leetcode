package org.wangbin.test;

import org.apache.commons.lang3.StringUtils;

public class tets1 {
	public static void main(String[] args) {
		String appendStr = "from_uid=111111";
		String temp = org.apache.commons.lang3.StringUtils.substringBefore(appendStr, "=");
		System.out.println(temp);
		System.out.println(appendStr);
		
		String url_long = "http://gongyi.weibo.com/r/213509?from_uid=123456789?from_uid=123456";
		String temp2 = enSureUrlLegalAndSaveParams(url_long,appendStr);
		System.out.println(temp2);
	}
	private static String patternSmall = "=[0-9A-Za-z]*";
    private static String enSureUrlLegalAndSaveParams(String url_long, String appendStr) {
        // TODO Auto-generated method stub
        if(appendStr.contains("=")){
            String temp = org.apache.commons.lang3.StringUtils.substringBefore(appendStr, "=");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(temp)){
                if(url_long.contains(temp)){
                    temp+=patternSmall;
                    String result = url_long.replaceAll(temp, appendStr);
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(result)){
                        return result;
                    }else{
                        return url_long;   
                    }
                }else{
                    //没有from_uid
                    //且后边有其他参数
                    if(url_long.contains("?")){
                        return new StringBuilder(url_long).append("&").append(appendStr).toString();
                    }else{
                        return  new StringBuilder(url_long).append("?").append(appendStr).toString();
                    }
                }
            }
        }
        return url_long;
       
    }
}
