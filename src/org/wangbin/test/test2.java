package org.wangbin.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.common.collect.Lists;

import crc64.CRC64Algorithm;

/**
 * 
 * @author wb
 * @date 2015-8-26 上午10:25:56
 */
public class test2 {
    public static void main(String[] args) {
        String key = "";
        Long uid = (long) 1803626420l;
        Long id = (long) 3738779545298037l;
//        System.out.println("情况一：");
//        System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//        System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //记录的出错的第一个记录
//         uid = (long) 3091983213l;
//         id = (long) 3738788079451178l;
//         System.out.println("情况二：");
//         System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//         System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //也有个出错的记录
//         uid = (long) 5058695223l;
//         id = (long) 3880128227874834l;
//         System.out.println("情况三：");
//        System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//        System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //也有个出错的记录
//        uid = (long) 2727046555l;
//        id = (long) 3881656467222381l;
//        System.out.println("情况四：");
//       System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//       System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//     //也有个出错的记录
//       uid = (long) 3790383711l;
//       id = (long) 3738546470771002l;
//       System.out.println("情况五：");
//       System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//       System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//       
       uid = (long) 2607718135l;
       id = (long) 3661913085997764l;
       System.out.println("情况六：");
//       System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
       System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        key = getBloomFilterKeyFromUidAndMid(uid, id);
//        Long max_diff = 2251799813685247l;
//        Long max_key = 0l;
//        Long keyL = Long.parseLong(key);
//        System.out.println((keyL - max_key));
//        System.out.println((keyL - max_key) >= max_diff);
//        int i = Integer.MAX_VALUE;
//        System.out.println(++i);
        System.out.println("现在最大的max——key：47287793490112720") ;
        System.out.println(" dff2:2251799813685248");
        
        
        System.out.println("~~~~");
       List<String> times = Lists.newArrayList();
       times.add("3760652126647685");
       times.add("3760652126647685");
//       times.add("3872240233629331");
       for(String time:times){
           long unixTime  = getTimeFromId(time);
           String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(unixTime * 1000));
           System.out.println("查看最小时间 :  ~~" + date);
       }
       
       
    }
    
    private static final int BLOOMFILTER_SAVE_DAYS = 380;//我们定的bloomfilter存储多少天的数据
    private static final long ONE_DAY_TIME_SECONDS = 86400;//一天的秒数
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf中存放的数据是距离现在多少秒之内的数据
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1406822400l;//表示2014年8月1号 0时0分0秒
    //unix伦敦时间与北京时间差8小时的秒数
    private static final long EIGHT_HOUR_TIME_SECONDS = 28800;//8*3600
    //相对时间，2005-01-01 00：00:00开始, 515483463是微博mid中固定减去的时间
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
//        Long lon = System.currentTimeMillis();
//        for(int i = 0;i <=1000;i++){
//            
        if(!isMidInOneYear(mid)){
            return "";
        }
        StringBuilder result = new StringBuilder(64);
        //表示当前的mid距离我们规定的时间的天数（初步定为2014-08-25），转换为北京时间
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        result.append(midDays);
        
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
       long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64后可能出现负数，不能够直接取余操作,直接位与取值,取值低位
        result.append(temp);
        return Long.valueOf(result.toString()) + "";
//        }
//        System.out.println("java Stringbuilder：" + (System.currentTimeMillis() - lon));
//        return "";
    }
    /**
     * java的位拼接的
     * @param uid
     * @param mid
     * @return
     */
    public static String getBloomFilterKeyFromUidAndMid2(long uid, long mid) {
//        Long lon = System.currentTimeMillis();
//        for(int i = 0;i <=1000;i++){
            if(!isMidInOneYear(mid)){
                return "";
            }
            //表示当前的mid距离我们规定的时间的天数（初步定为2014-08-25），转换为北京时间
            System.out.println("~~~" + getLocalTime(getTimeFromId(mid)));
            long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
//            long midDays=10;
            String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
            long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
            
            String unionStr2 =String.valueOf(uid) + String.valueOf(mid);
            long crc64HashResult2 = CRC64Algorithm.crc64Long(unionStr);
            
            long temp = crc64HashResult & ((1L <<52) - 1);
            //crc64后可能出现负数，不能够直接取余操作,直接位与取值,取值低位
            
            long temp2 = crc64HashResult2 & ((1L <<52) - 1);
            long keyJava2 = (midDays<<(51))|temp2;
            long keyJava = (midDays<<(51))|temp;
            return keyJava+"";
//        }
//        System.out.println("java位运算：" + (System.currentTimeMillis() - lon));
//        return "";
    }
    /**
     * mid是否是BLOOMFILTER_SAVE_DAYS内的mid，如果是那么返回true
     * @param mid
     * @return
     */
    //2015年9月1日，之后的数据可以随意的放入bloomfilter
    private static final long BLOOMFILTER_NO_LIMIT_TIME = 1441036800L;
    private static boolean isMidInOneYear(long mid) {
        long currTime = System.currentTimeMillis()/1000;//unix时间戳精确到秒
        long midTime = getTimeFromId(mid);//从mid中拿到unix时间戳
        System.out.println(getLocalTime(currTime));
        if(Math.abs(currTime - midTime) < ALL_SECONDS_IN_BLOOMFILTER || getLocalTime(mid) > BLOOMFILTER_NO_LIMIT_TIME){
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
    public static long getTimeFromId(String id) {
        return getTimeNumberFromId(Long.valueOf(id)) + ID_OFFSET;
    }
    public static long getTimeNumberFromId(long id) {
        return id >> IDC_SEQ_BIT_LENGTH;
    }
    public static final long SEQ_BIT_LENGTH = 18;
    public static final long IDC_SEQ_BIT_LENGTH = 4 + SEQ_BIT_LENGTH;
    
}
