package org.wangbin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * ���ļ��е�ĳЩ���
 * @author wb
 * @date 2015-9-1 ����8:17:34
 */
public class testForObject {
    public static void main(String[] args) {



    }
    private static void doIt(String str) {
        // TODO Auto-generated method stub
        Long lon = System.currentTimeMillis();
        File file = new File(str);
        BufferedInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�

            String line = "";
            long slow = 0l;
            long fast = 0l;//��mid
            long count = 0l;
            long uid= 0l;
            String key = null;
            Long max_diff = 2251799813685247l;
            Long max_key = -1l;
            while (true) {
                if ((line = reader.readLine()) != null) {
                    try {
                        count++;
                        if(true){
                            continue;
                        }
                        if (count % 500000 == 0) {
                            System.out.println("����У����У�鵽�� " + count + " ��~~likes/wangbin/checkSortData,��ĿǰΪֹ��mid��˳�����+ file" + file.getName());
                        }
                        String[] strs = line.split("\t");
                        if(strs[0].length()<10){
                            System.out.println("##@##��������ֵ�����" + line );
//                            throw new RuntimeException("##@##��������ֵ�����" + line );
                        }
                        fast = Long.parseLong(strs[0]);
                        uid= Long.parseLong(strs[2]);
                        Long tempKey = Long.parseLong(key);
                        //У��ȫ������~~key����һ����������max_diff������ƽ������
                        if(tempKey > max_key){
                           if(max_key!=-1){
                               if(tempKey - max_key > max_diff){
                                   System.out.println("##@##������max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line����Ϣ��"+ line );
//                                   throw new RuntimeException("##@##������max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line����Ϣ��"+ line );
                               }
                           }
                            max_key = tempKey;
                        }
                        
                        if(key.startsWith("-")){
                            System.out.println("##@##false~~ key����:" +key +" uid:" + uid + "  mid:" + fast );
//                            throw new RuntimeException("##@##false~~ key����:" +key +" uid:" + uid + "  mid:" + fast  );
                        }
                        if (count % 1000000 == 0) {
                            System.out.println("fast :" + fast + "  slow:" + slow + "  key:" + key + " max_key:" + max_key + "  max_diff:" + max_diff );
                        }
                        
                        
                        if (slow <= fast) {//�Ƚ��Ƿ����ϸ����
                            slow = fast;
                        } else {
                            System.out.println("##@##false~~:" + fast + "   slow :" + slow);
                            throw new RuntimeException("##@##false~~:" + fast + "   slow :" + slow);
                        }
                        // System.out.println("����ļ�������Ϊ:" + count);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("~~");
                    break;
                }
                // TODO: write your business
                // System.out.println(line);
            }
            System.out.println("##@## file:" + str + " �ǰ���mid����ŵ�~~~Ȼ������ļ��ĳ���Ϊ��" + count + "  У���ļ��ĺ�ʱΪ"
                    + (System.currentTimeMillis() - lon));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        System.out.println(System.currentTimeMillis() - lon);
        System.out.println("~~~~~~~~~~");
        // StringBuilder sb = new StringBuilder();
        // for(int i = 0;i <=31;i++){
        // System.out.println("mkdir att_timeline_"+i);
        // }
    }
}
