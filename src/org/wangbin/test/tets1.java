package org.wangbin.test;

import org.apache.commons.lang3.StringEscapeUtils;
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
		String str3 = null;
		System.out.println("~~~~" + StringEscapeUtils.escapeHtml4(str3));
		long id = 3870818041560873l;
		id = id >> IDC_SEQ_BIT_LENGTH;
		id = id + ID_OFFSET;
		System.out.println(id +"");
		long id2 = 1438358400l;
		id2 -= ID_OFFSET;
		id2 = id2<<IDC_SEQ_BIT_LENGTH;
		System.out.println("2014.08.01 :  " + id2 +"");
		
		id2 = 1439136000l;
        id2 -= ID_OFFSET;
        id2 = id2<<IDC_SEQ_BIT_LENGTH;
        System.out.println("2014.08.10 :  " + id2 +"");
		
		
		
		id = id2;
        id = id >> IDC_SEQ_BIT_LENGTH;
        id = id + ID_OFFSET;
        System.out.println(id +"");
        
        
        
        
        
        String special = "rsync -av 10.77.113.146::wangbin/att_timeline_"+"##@##" +"/status_att_1408.txt ./att_timeline_"+"##@##"+"/";
        for(int i= 0;i <4;i++){
            System.out.println(special.replaceAll("##@##", i+""));
        }
        
		
	}
	public static final long ID_OFFSET = 515483463;
	public static final long SEQ_BIT_LENGTH = 18;
    public static final long IDC_SEQ_BIT_LENGTH = 4 + SEQ_BIT_LENGTH;
    
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
