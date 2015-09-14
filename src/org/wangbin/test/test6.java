package org.wangbin.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 多实例tomcat，如何计算文件名称
 * 
 * @author wb
 * @date 2015-9-9 上午10:15:59
 */
public class test6 {
    public static void main(String[] args) {
        try {
            int count = 32;
            for(int i = 1;i <=count;i++){
                List<String> fs = getFileNameLists("1508",  count, i);
                for (String str : fs) {
                    System.out.println(str);
                }
                System.out.println("+++++++++++++++++++++++++++");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String str ="status_att_##@##";
        StringBuilder sb = new StringBuilder();
        for(int i = 1409;i <=1412;i++){
            sb.append(new String(str).replaceAll("##@##", i+"")).append(",");
        }
        System.out.println(sb.toString());
        sb = new StringBuilder();
        for(int i = 1501;i <=1509;i++){
            sb.append(new String(str).replaceAll("##@##", i+"")).append(",");
        }
        System.out.println(sb.toString());
    }

    private static List<String> getFileNameLists(String fileName, Integer tomcatNumber, Integer tomcatIndex) {
        // TODO Auto-generated method stub
        try {
            // 这个地方多实例并发导入，所以文件名选取单独一个方法,一共32个文件不变，32/tomcatNumber即每个tomcat导入的文件个数
            tomcatIndex = tomcatIndex == 0 ? 1 : tomcatIndex;
            tomcatNumber = tomcatNumber == 0 ? 1 : tomcatNumber;
            Integer number = 32 / tomcatNumber;// 每个tomcat需要操作的文件个数
            // fileName为月份，然后循环32个端口对应的表
            List<String> fs = new ArrayList<String>(32);
            for (int i = 0; i <= 31; i++) {
                fs.add("/data1" + "/wangbin/att_timeline_" + i + "/status_att_" + fileName + ".txt");
            }
            Integer fromIndex = number * (tomcatIndex - 1);// 跟分页一个原理
            List<String> reList = null;
            if (tomcatIndex == tomcatNumber) {
                // 最后一个，处理剩余的全部
                reList = new ArrayList<String>(32 - (tomcatIndex - 1) * number);// 多余的补齐
                for (int i = 0; i < 32 - (tomcatIndex - 1) * number  && (i+fromIndex) <32; i++) {
                    reList.add(fs.get(i + fromIndex));
                }
            } else {
                reList = new ArrayList<String>(number);
                for (int i = 0; i < number && (i+fromIndex) <32; i++) {
                    reList.add(fs.get(i + fromIndex));
                }
            }
            return reList;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
