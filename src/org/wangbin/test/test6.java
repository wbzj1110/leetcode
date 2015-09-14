package org.wangbin.test;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʵ��tomcat����μ����ļ�����
 * 
 * @author wb
 * @date 2015-9-9 ����10:15:59
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
            // ����ط���ʵ���������룬�����ļ���ѡȡ����һ������,һ��32���ļ����䣬32/tomcatNumber��ÿ��tomcat������ļ�����
            tomcatIndex = tomcatIndex == 0 ? 1 : tomcatIndex;
            tomcatNumber = tomcatNumber == 0 ? 1 : tomcatNumber;
            Integer number = 32 / tomcatNumber;// ÿ��tomcat��Ҫ�������ļ�����
            // fileNameΪ�·ݣ�Ȼ��ѭ��32���˿ڶ�Ӧ�ı�
            List<String> fs = new ArrayList<String>(32);
            for (int i = 0; i <= 31; i++) {
                fs.add("/data1" + "/wangbin/att_timeline_" + i + "/status_att_" + fileName + ".txt");
            }
            Integer fromIndex = number * (tomcatIndex - 1);// ����ҳһ��ԭ��
            List<String> reList = null;
            if (tomcatIndex == tomcatNumber) {
                // ���һ��������ʣ���ȫ��
                reList = new ArrayList<String>(32 - (tomcatIndex - 1) * number);// ����Ĳ���
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
