package org.wangbin.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

public class test5 {
    public static void main(String[] args) {
//        System.out.println("hello linux");
//        String str = "/data0/wangbin/att_timeline_"+"##@##" + "/status_att_1408.txt";
//        StringBuilder sb = new StringBuilder();
//        for(int i =0;i <=31;i++){
//            sb.append(new String(str).replaceAll("##@##", i+""));
//            if(i!=31){
//                sb.append(",");
//            }
//        }
//        System.out.println(sb.toString());
//        //计算1后边51个0的值
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        for (int i = 1; i <= 51; i++) {
            sb.append("0");
        }
        System.out.println(sb.toString());
        long lon = Long.valueOf(sb.toString(), 2);
        System.out.println(lon-1);
        System.out.println(Long.valueOf(sb.toString(), 2));
        System.out.println();
        String fileName = "1408";
        List<String> fileNames = new ArrayList<String>(32);
        for(int i = 0;i <=31;i++){
            fileNames.add("/data0/wangbin/att_timeline_"+ i+"/status_att_"+fileName+".txt");
            System.out.println(fileNames.get(i));
        }
        System.out.println(StringEscapeUtils.unescapeHtml4("test今天星期几？\u2014\u2014－－－[电影]"));
    }
}
