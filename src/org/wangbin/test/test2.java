package org.wangbin.test;
/**
 * 
 * @author wb
 * @date 2015-8-26 上午10:25:56
 */
public class test2 {
    public static void main(String[] args) {
        String key = "";
        Long uid = (long) 5056762429l;
        Long id = (long) 3880063314987169l;
        System.out.println(getBloomFilterKeyFromUidAndMid(uid, id));
        
    }
    
    private static final int BLOOMFILTER_SAVE_DAYS = 400;//我们定的bloomfilter存储的一天的数据
    private static final long ONE_DAY_TIME_SECONDS = 86400;//一天的秒数
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf中存放的数据是距离现在多少秒之内的数据
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1408464000l;//表示2014年8月20号
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
        if(!isMidInOneYear(mid)){
            return "";
        }
        StringBuilder result = new StringBuilder(64);
        //表示当前的mid距离我们规定的时间的天数（初步定为2014-08-25），转换为北京时间
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        result.append(midDays);
        
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
        System.out.println(crc64HashResult);
       long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64后可能出现负数，不能够直接取余操作,直接位与取值,取值低位
        result.append(temp);
        return result.toString();
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
    
}
