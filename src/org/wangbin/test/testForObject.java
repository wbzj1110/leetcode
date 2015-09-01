package org.wangbin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 找文件中的某些语句
 * @author wb
 * @date 2015-9-1 上午8:17:34
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
            reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// 用5M的缓冲读取文本文件

            String line = "";
            long slow = 0l;
            long fast = 0l;//即mid
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
                            System.out.println("数据校验中校验到第 " + count + " 行~~likes/wangbin/checkSortData,到目前为止，mid是顺序递增+ file" + file.getName());
                        }
                        String[] strs = line.split("\t");
                        if(strs[0].length()<10){
                            System.out.println("##@##遇到个奇怪的数据" + line );
//                            throw new RuntimeException("##@##遇到个奇怪的数据" + line );
                        }
                        fast = Long.parseLong(strs[0]);
                        uid= Long.parseLong(strs[2]);
                        Long tempKey = Long.parseLong(key);
                        //校验全部数据~~key不能一次增长超过max_diff，且是平滑递增
                        if(tempKey > max_key){
                           if(max_key!=-1){
                               if(tempKey - max_key > max_diff){
                                   System.out.println("##@##超出了max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line的信息："+ line );
//                                   throw new RuntimeException("##@##超出了max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line的信息："+ line );
                               }
                           }
                            max_key = tempKey;
                        }
                        
                        if(key.startsWith("-")){
                            System.out.println("##@##false~~ key错误:" +key +" uid:" + uid + "  mid:" + fast );
//                            throw new RuntimeException("##@##false~~ key错误:" +key +" uid:" + uid + "  mid:" + fast  );
                        }
                        if (count % 1000000 == 0) {
                            System.out.println("fast :" + fast + "  slow:" + slow + "  key:" + key + " max_key:" + max_key + "  max_diff:" + max_diff );
                        }
                        
                        
                        if (slow <= fast) {//比较是否是严格递增
                            slow = fast;
                        } else {
                            System.out.println("##@##false~~:" + fast + "   slow :" + slow);
                            throw new RuntimeException("##@##false~~:" + fast + "   slow :" + slow);
                        }
                        // System.out.println("输出文件的行数为:" + count);
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
            System.out.println("##@## file:" + str + " 是按照mid排序号的~~~然后这个文件的长度为：" + count + "  校验文件的耗时为"
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
