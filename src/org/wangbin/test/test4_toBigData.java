package org.wangbin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 这个直接java编译执行
 * 作用是
 * 1、用来校验所有的数据  按照月分表的 每月是否是按照mid纬度 
 * 2、用来校验放入bloomfilter的时候每行数据对应的key 是否满足
 * bloomfilter的要求~~每个文件2GB，服务器上边计算平均一个文件是85s左右。
 * 扫12*32个文件大概需要9个小时。
 * 注：5M缓存的扫文件，速度比较快，校验的时候可以开多线程处理，但是bloomfilter导入的时候
 * 最好是1个线程读，多线程处理。
 * @author wb
 * @date 2015-8-29 上午9:02:24
 */
public class test4_toBigData {
    public static void main(String[] args) {
        // String str =
        // "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
        // File.separator
        // + "status_att_1408.txt";
//        
//         String str =
//         "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
//         File.separator
//         + "status_att_1409.txt";
//       doIt(str);
//       
        
        
        
        
        String str =
                "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
                File.separator
                + "fixData20150902.txt";
              findRedisException(str);
              
        
        
        
        
//       String str =
//               "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
//               File.separator
//               + "2.txt";
//       setBloomFilterForFixData(str);
        
        
       
//        String str = "/data0/wangbin/att_timeline_" + "##@##" + "/status_att_@@#@@.txt";
//        //测试机测试，只测试1408即可
////        for(int j = 1408;j <=1408 ;j++){
//        for(int j = 1409;j <=1420 ;j++){
//            for (int i = 0; i <= 31; i++) {
//                String temp = new String(str);
//                temp = temp.replaceAll("##@##", i + "");
//                if(j<=1412){
//                    temp = temp.replaceAll("@@#@@", j+"");
//                }else{
//                    temp = temp.replaceAll("@@#@@", (j-1412+1500)+"");
//                }
////                 System.out.println(temp);
//                 doIt(temp);
//            }
//        }
        
        


    }
    
    private static boolean findRedisException(final String fileName) {
        // TODO Auto-generated method stub
        long count = 0;
        int countInterval=5000;
        List<String> ls = Lists.newArrayList();
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
                   String temp = StringUtils.substringAfter(line, "redis server all dead: REDIS");
                   if(StringUtils.isNotBlank(temp)){
                       sets.add(temp);
                       
                   }
                   String temp2 = new String(line);
                   temp2 = StringUtils.substringBetween(temp2, "ptm", ".eos");
                   if(temp2.equals("8814")){
                       System.out.println("!!!!找到8814啦");
                       Thread.sleep(50000);
                   }
                   if(StringUtils.isNotBlank(temp2)){
                       sets2.add(temp2);
                       
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
    
    private static boolean setBloomFilterForFixData(final String fileName) {
        // TODO Auto-generated method stub
        long count = 0;
        int countInterval=5000;
        try {
            final File file = new File(fileName);
            if(file.isFile()){
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// 用5M的缓冲读取文本文件,不会占用过多的内存
                String line = "";
                
                while((line = reader.readLine()) != null){  
                    count++;
                    //每隔countInterval行打记录
                    if(count%countInterval==0){
                        System.out.println("##@## ##@## ##@##LikesServiceImpl.setBloomFilterForFixData"+ fileName +" 导入第" 
                                + count + "行~~~此行的数据为" + line );
                    }
                    String temp = new String(line);
                    if(org.apache.commons.lang3.StringUtils.isBlank(temp)){
                        continue;
                    }
                    temp = org.apache.commons.lang3.StringUtils.substringBetween(temp, "线程执行有错误，记录错误行与信息值", "e:");
                    temp = org.apache.commons.lang3.StringUtils.substringAfter(temp, "信息：");
                    if(org.apache.commons.lang3.StringUtils.isBlank(temp)){
                        continue;
                    }else{
                        temp = org.apache.commons.lang3.StringUtils.trim(temp);
                    }
                    Long uid = Long.parseLong(temp.split("\t")[2]);
                    Long mid = Long.parseLong(temp.split("\t")[0]);
                    final String tempLine = line;
                    final long tempCount = count;
                    //一次写线程数de 数据
                    //注:这个地方不能够一次读取多行数据，然后多线程处理，因为我们取是按照mid的纬度取的
                    //最终放入数据是按照uid取的，且同一个mid对应的uid一定不会出现重复，所以一次取多行，放入
                    //基本没有效率上的提升。
                }
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return false;
        }
    }
    private static void doIt(String str) {
        // TODO Auto-generated method stub
        Long lon = System.currentTimeMillis();
        File file = new File(str);
        BufferedInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 10 * 1024 * 1024);// 用5M的缓冲读取文本文件

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
                        key = getBloomFilterKeyFromUidAndMid(uid, fast);
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
    private static final int BLOOMFILTER_SAVE_DAYS = 400;//我们定的bloomfilter存储多少天的数据
    private static final long ONE_DAY_TIME_SECONDS = 86400;//一天的秒数
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf中存放的数据是距离现在多少秒之内的数据
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1406822400l;//表示2014年8月1号 0时0分0秒
    //unix伦敦时间与北京时间差8小时的秒数
    private static final long EIGHT_HOUR_TIME_SECONDS = 28800;//8*3600
    //相对时间，2005-01-01 00：00:00开始, 
    private static final long WEIBO_INTI_TIME = 515483463L;
    /**
     * 根据uid与mid获取key(总长度63)。mid是距离现在BLOOMFILTER_SAVE_DAYS天以内的才放进去（这个是能否放进与取bf的标准）<br>
     * key的前12位位时间位，表示当前的mid距离我们规定的时间的天数(初步定为2014-08-25)，这个是为了保证bf里边key的平滑递增。<br>
     * key的后51位为uid与mid做CRC64后51位的结果
     * @param uid
     * @param mid
     * @return
     */
    public static String getBloomFilterKeyFromUidAndMid(long uid, long mid) {
        if(!isMidInOneYear(mid)){
            return "";
        }
        //表示当前的mid距离我们规定的时间的天数（初步定为2014-08-25），转换为北京时间
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = crc64Long(unionStr);
        long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64后可能出现负数，不能够直接取余操作,直接位与取值,取值低位
        long keyJava = (midDays<<(51))|temp;
        return keyJava+"";
    }
    /**
     * mid是否是BLOOMFILTER_SAVE_DAYS内的mid，如果是那么返回true
     * @param mid
     * @return
     */
    private static boolean isMidInOneYear(long mid) {
        long currTime = System.currentTimeMillis()/1000;//unix时间戳精确到秒
        long midTime = getTimeFromId(mid);//从mid中拿到unix时间戳
        if(Math.abs(currTime - midTime) < ALL_SECONDS_IN_BLOOMFILTER){
            return  true;
        }
        return false;
    }
    /**
     * 转换为北京时间
     * @param unixTime
     * @return
     */
    public static long getLocalTime(long unixTime) {
        return unixTime + EIGHT_HOUR_TIME_SECONDS;
    }
    public static final long ID_OFFSET = 515483463;
    public static long getTimeFromId(long id) {
        return getTimeNumberFromId(id) + ID_OFFSET;
    }
    public static long getTimeNumberFromId(long id) {
        return id >> IDC_SEQ_BIT_LENGTH;
    }
    public static final long SEQ_BIT_LENGTH = 18;
    public static final long IDC_SEQ_BIT_LENGTH = 4 + SEQ_BIT_LENGTH;
    
    
    /**
     * CRC64
     * 
     */
    private static final long INITIALCRC = 0xFFFFFFFFFFFFFFFFL;
    private static long[] sCrcTable = new long[256];
    private static final long POLY64REV = 0x95AC9329AC4BC9B5L;

    static {
        long value;
        for (int i = 0; i < 256; i++) {
            value = i;
            for (int j = 0; j < 8; j++) {
                long checkLSB = ((int) value & 0x1) != 0 ? POLY64REV : 0;
                value = (value >> 1) ^ checkLSB;
            }
            sCrcTable[i] = value;
        }
    }
    /**
     * get crc64 long
     * @param in
     * @return
     */
    public static final long crc64Long(String in) {
        if(in==null||in.length()==0||in.equals("")){
            return 0L;
        }
        return crc64Long(getBytes(in));
    }

    /**
     * get byte array
     * @param in
     * @return
     */
    public static byte[] getBytes(String in) {
        byte[] result = new byte[in.length() * 2];
        int index = 0;
        for (char ch : in.toCharArray()) {
            result[index++] = (byte) (ch & 0xFF);
            result[index++] = (byte) (ch >> 8);
        }
        return result;
    }

    /**
     * byte convert to long
     * @param buffer
     * @return
     */
    public static final long crc64Long(byte[] buffer) {
        long crcLong = INITIALCRC;
        for (int k = 0, n = buffer.length; k < n; ++k) {
            crcLong = sCrcTable[(((int) crcLong) ^ buffer[k]) & 0xff] ^ (crcLong >> 8);
        }
        return crcLong;
    }
    /**
     * get crc64, convert to hex string
     * @param in
     * @return
     */
    public static final String crc64Hex(String in) {
        long crcLong = crc64Long(in);
        return Long.toHexString(crcLong);
    }
}
