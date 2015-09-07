package org.wangbin.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 输出脚本的语句
 * @author wb
 * @date 2015-9-1 下午1:18:21
 */
public class testForCASE {
    public static void main(String[] args) {
        //rsync接受数据
//       doOne();
        
        
        //删除rm 文件
//       doTwo(); 
        
        
        //创建文件夹 
//        doThree();
        
        //rsync接受数据
      doOne2();
       
       
       //删除rm 文件
//      doTwo2(); 
        
//        Collection<String> cs = new ArrayList<String>();
//        doFour(cs);
      
      //导数据的接口  从数据库中
//      doFour();
        
    }
    private static void doFour() {
        // TODO Auto-generated method stub
        String str = "./mydumper -h  10.75.20.141 -P ##@## -u openapi  -p im1400uc  -B att_timeline_@@#@@ -T status_att_1508 -o s##@## -m -f csv -C utf8 -e -t 4 -L ##@##.log ";
        int j = 0;
        for(int i = 4831;i <=4838;i++){
            String temp = new String(str);
            temp = temp.replaceAll("##@##", i+"");
            for(int count = 0;count <4;count++){
                String temp2 = new String(temp);
                temp2 = temp2.replaceAll("@@#@@", j+"");
                System.out.println(temp2);
                j++;
            }
        }
    }
    private static void doFour(Collection<String> str){
        System.out.println(str);
        
        
    }
    private static void doThree() {
        // TODO Auto-generated method stub
        String str = "mkdir att_timeline_##@##";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }

    private static void doTwo() {
        // TODO Auto-generated method stub
        String str = "rm -rf /data0/wangbin/att_timeline_##@##/status_att_*.txt";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }

    private static void doOne() {
        // TODO Auto-generated method stub
        String str = "rsync -av 10.77.113.146::wangbin/att_timeline_##@##/status_att_1503.txt ./att_timeline_##@##/";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static void doTwo2() {
        // TODO Auto-generated method stub
        String str = "rm -rf /data1/wangbin/att_timeline_##@##/status_att_*.txt";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }

    private static void doOne2() {
        // TODO Auto-generated method stub
        String str = "rsync -av 10.77.113.146::wangbin/att_timeline_##@##/status_att_1503.txt ./att_timeline_##@##/";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
    
    
    
    
    
    
    
    
    
}
