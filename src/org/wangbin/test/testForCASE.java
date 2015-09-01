package org.wangbin.test;
/**
 * 输出脚本的语句
 * @author wb
 * @date 2015-9-1 下午1:18:21
 */
public class testForCASE {
    public static void main(String[] args) {
//       doOne();
       doTwo(); 
        
        
    }

    private static void doTwo() {
        // TODO Auto-generated method stub
        String str = "rm /data0/wangbin/att_timeline_##@##/status_att_1408.txt";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }

    private static void doOne() {
        // TODO Auto-generated method stub
        String str = "rsync -av 10.77.113.146::wangbin/att_timeline_##@##/status_att_1411.txt ./att_timeline_##@##/";
        for(int i = 0;i <=31;i++){
            System.out.println(new String(str).replaceAll("##@##", i+""));
        }
    }
}
