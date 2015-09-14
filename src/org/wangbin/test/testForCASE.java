package org.wangbin.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 输出脚本的语句
 * @author wb
 * @date 2015-9-1 下午1:18:21
 */
public class testForCASE {
    public static void main(String[] args) {
        //rsync接受数据
       doOne();
        
        
        //删除rm 文件
//       doTwo(); 
        
        
        //创建文件夹 
//        doThree();
        
        //rsync接受数据
//      doOne2();
       
       
       //删除rm 文件
//      doTwo2(); 
        
//        Collection<String> cs = new ArrayList<String>();
//        doFour(cs);
      
      //导数据的接口  从数据库中
//      doFour();
//      doFour_3();
      doFour_2();
      //复制文件
//      doFive();
      //修改名称
//      doSix();
      
      //删除错误的文件
//    doSeven();
      //删除错误的文件
//    doEight();
      
    }
    private static void doFour_3() {
        // TODO Auto-generated method stub
        List<String> ls = Lists.newArrayList(
            "10.75.20.141",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.19.190",
            "10.75.16.214",
            "10.75.19.190",
            "10.75.19.190"
            );
        List<String> ls2 = Lists.newArrayListWithExpectedSize(12);
        for(int i = 1409;i <=1412;i++){
            ls2.add(i+"");
        }
        for(int i = 1501;i <=1509;i++){
            ls2.add(i+"");
        }
        
        String str = "nohup ./mydumper -h  ##### -P ##@## -u openapi  -p im1400uc  -B att_timeline_@@#@@ -T  status_att_@@@@@  -o s##@##  -f csv -C utf8 -e -t 4 -L ##@##.log  &";
        int j = 0;
        int mark = 3;
        List<String> result = Lists.newArrayListWithExpectedSize(384);
        for(int z= 0;z <12;z++){
            for(int i = 4831;i <=4838;i++){
//              //4835 4836db 给推了
//              if(i==4835 || i == 4836){
//                  continue;
//              }
              String temp = new String(str);
              temp = temp.replaceAll("##@##", i+"");
              temp = temp.replaceAll("#####", ls.get((i-4831)));
              for(int count = 0;count <4;count++){
                  String temp2 = new String(temp);
                  temp2 = temp2.replaceAll("@@#@@", j+"");
                  temp2 =  temp2.replaceAll("@@@@@", ls2.get(z));
                  result.add(temp2);
//                  System.out.println(temp2);
                  j++;
                  if(j==32){
                      j=0;
                  }
              }
          }
        }
        
        
        System.out.println("############################################");
        for(int i = 0;i <12;i++){
            //按照月取端口号
            int fromIndex = 32*i;
            List<String> temp = Lists.newArrayListWithExpectedSize(32);
            for(int z=0;z <32;z++){
                int index = fromIndex+z;
//                System.out.println(result.get(index));
                temp.add(result.get(index));
            }
            System.out.println("####################");
            //一次8句并发执行。
            for(int m=0; m<4;m++){
                for(int n = 0;n <8;n++){
                    int countT = n*4 +m;
                    if(n==7){
                        System.out.println(new String(temp.get(countT)).replaceAll("nohup", "").replaceAll("&", ""));
                        System.out.println("#######################");
                    }else{
                        System.out.println(temp.get(countT));
                    }
                    countT++;
                }
            }
           
           
            
        }
        
        
        
        
    }
    private static void doEight() {
        // TODO Auto-generated method stub
        String str = "mkdir s##@##";
        for(int i =4831;i <=4838;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
    private static void doSeven() {
        // TODO Auto-generated method stub
        String str = "rm -rf /data0/wangbin/att_timeline_##@##/*status_att_1508.csv";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
    private static void doSix() {
        // TODO Auto-generated method stub
        String str = "mv /data0/wangbin/att_timeline_##@##/att_timeline_##@##.status_att_1509.csv /data0/wangbin/att_timeline_##@##/status_att_1509.txt";
        for(int i = 0;i <=31;i++){
            String temp = new String(str);
            System.out.println(temp.replaceAll("##@##", i+""));
        }
    }
    private static void doFive() {
        // TODO Auto-generated method stub
        String str = "cp /data0/wangbin/shelldir/s#####/att_timeline_##@##.status_att_1509.csv /data0/wangbin/att_timeline_##@##/";
        int j = 0;
        for(int i = 4831;i <=4838;i++){
            String temp = new String(str);
            temp = temp.replaceAll("#####", i+"");
            for(int count = 0;count <4;count++){
                String temp2 = new String(temp);
                temp2 = temp2.replaceAll("##@##", j+"");
                System.out.println(temp2);
                j++;
            }
        }
    }
    private static void doFour() {
        // TODO Auto-generated method stub
        List<String> ls = Lists.newArrayList(
            "10.75.20.141",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.19.190",
            "10.75.16.214",
            "10.75.19.190",
            "10.75.19.190"
            );
        String str = "nohup ./mydumper -h  ##### -P ##@## -u openapi  -p im1400uc  -B att_timeline_@@#@@ -T  status_att_1409  -o s##@##  -f csv -C utf8 -e -t 4 -L ##@##.log  &";
        int j = 0;
        int mark = 3;
        for(int i = 4831;i <=4838;i++){
//            //4835 4836db 给推了
//            if(i==4835 || i == 4836){
//                continue;
//            }
            String temp = new String(str);
            temp = temp.replaceAll("##@##", i+"");
            temp = temp.replaceAll("#####", ls.get((i-4831)));
            for(int count = 0;count <4;count++){
                String temp2 = new String(temp);
                temp2 = temp2.replaceAll("@@#@@", j+"");
                System.out.println(temp2);
                j++;
            }
        }
    }
    private static void doFour_2() {
        // TODO Auto-generated method stub
        List<String> ls = Lists.newArrayList(
            "10.75.20.141",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.16.213",
            "10.75.19.190",
            "10.75.16.214",
            "10.75.19.190",
            "10.75.19.190"
            );
        String str = " ./mydumper -h  ##### -P ##@## -u openapi  -p im1400uc -B att_timeline_@@#@@ -T status_att_1409,status_att_1410,status_att_1411,status_att_1412,status_att_1501,status_att_1502,status_att_1503,status_att_1504,status_att_1505,status_att_1506,status_att_1507,status_att_1508,status_att_1509 -o s##@## -f csv -C utf8 -e -t 4 -L ##@##.log ";
        String str2 = " ./mydumper -h ##### -P ##@## -u openapi -p im1400uc -B att_timeline_@@#@@  -T status_att_1409,status_att_1410,status_att_1411,status_att_1412,status_att_1501,status_att_1502,status_att_1503,status_att_1504,status_att_1505,status_att_1506,status_att_1507,status_att_1508,status_att_1509 -o s##@##  -f csv -C utf8 -e -t 4 -L ##@##.log  ";
        int j = 0;
        for(int i = 4831;i <=4838;i++){
            //4835 4836db 给推了
//            if(i==4835 || i == 4836){
//                continue;
//            }
            String temp = new String(str);
            temp = temp.replaceAll("##@##", i+"");
            temp = temp.replaceAll("#####", ls.get((i-4831)));
            for(int count = 0;count <4;count++){
                String temp2 = new String(temp);
                temp2 = temp2.replaceAll("@@#@@", j + "");
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
        String str = "nohup rsync -av 10.77.113.146::wangbin/att_timeline_##@##/status_att_1508.txt ./att_timeline_##@##/ &";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
    
    
    
    
    
    
    
    
    
}
