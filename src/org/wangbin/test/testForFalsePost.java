package org.wangbin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class testForFalsePost {
    public static void main(String[] args) {
//        String str =
//                "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" + File.separator
//                        + "fixData20150902.txt";
//        String str = "/data1/web_v4_wangbin/testJAVA/fixData20150902.txt";
//        findRedisException(str);
        List<String>ls = new   ArrayList<String>(2);
        try {
           ls.get(10);
            System.out.println("~~~~2222");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("~~~~11111");
//            ls.get(4);
        }
        System.out.println("@@@@@@");
        
        
        List<String> keysL = Lists.newArrayList("1","2","3");
        String[]keys = (String[])keysL.toArray(new String[keysL.size()]);
        for(String key:keys){
            System.out.println(key);
        }
        System.out.println("~~~");
        String str = "1243";
        String []strs = StringUtils.split(str, ",");
        System.out.println(Arrays.toString(strs));
    }
    private static boolean findRedisException(final String fileName) {
        // TODO Auto-generated method stub
        long count = 0;
        int countInterval=5000;
        List<String> ls = new ArrayList<String>();
        int indexCounter = 0;
        String str = "ptm##@##.eos.grid.sina.com.cn:##@##";
        for(int i = 8812;i <=8827;i++){
            ls.add(new String(str).replaceAll("##@##", i+""));
        }
        Set<String> sets = new HashSet<String>();
        Set<String> sets2 = new HashSet<String>();
        Map<String, String>maps = new HashMap<String, String>();
        try {
            final File file = new File(fileName);
            if(file.isFile()){
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// 用5M的缓冲读取文本文件,不会占用过多的内存
                String line = "";
                
                while((line = reader.readLine()) != null){  
                    count++;
                    //每隔countInterval行打记录
//                    if(count%countInterval==0){
//                        System.out.println("##@## ##@## ##@##LikesServiceImpl.setBloomFilterForFixData"+ fileName +" 导入第" 
//                                + count + "行~~~此行的数据为" + line );
//                    }
                    if(maps.size()==16){
                        break;
                    }
                    if(line.contains("redis server all dead: REDIS")){
                        String temp =line.substring(line.indexOf("redis server all dead: REDIS"), line.length());
                        if(temp!=null && temp.length()!=0){
                            sets.add(temp);
                        }
                    }
                   
                   String temp2 = new String(line);
                   if(temp2.contains("ptm")&&temp2.contains(".eos")){
                       temp2 = temp2.substring(temp2.indexOf("ptm"), temp2.indexOf("eos"));
                       if(temp2.equals("8814")){
                           System.out.println("!!!!找到8814啦");
                           Thread.sleep(500);
                       }
                       if(temp2!=null && temp2.length()!=0){
                           sets2.add(temp2);
                           
                       }
                   }
                  
                    
                    
                }
                
                
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            e.printStackTrace();
            return false;
        }finally{
            System.out.println("文件扫描完毕~~行数：" + count);
            System.out.println("最终结果展示 sets的大小为：" + sets.size());
            for(String set:sets){
                System.out.println(set);
            }
            System.out.println("最终结果展示 sets2的大小为：" + sets.size());
            for(String set:sets2){
                System.out.println(set);
            }
        }
    }

}
