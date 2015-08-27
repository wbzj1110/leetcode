package org.wangbin.test;
/**
 * 
 * @author wb
 * @date 2015-8-26 ����10:25:56
 */
public class test2 {
    public static void main(String[] args) {
        String key = "";
        Long uid = (long) 5056762429l;
        Long id = (long) 3880063314987169l;
        System.out.println(getBloomFilterKeyFromUidAndMid(uid, id));
        
    }
    
    private static final int BLOOMFILTER_SAVE_DAYS = 400;//���Ƕ���bloomfilter�洢��һ�������
    private static final long ONE_DAY_TIME_SECONDS = 86400;//һ�������
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf�д�ŵ������Ǿ������ڶ�����֮�ڵ�����
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1408464000l;//��ʾ2014��8��20��
    //unix�׶�ʱ���뱱��ʱ���8Сʱ������
    private static final long EIGHT_HOUR_TIME_SECONDS = 28800;//8*3600
    //���ʱ�䣬2005-01-01 00��00:00��ʼ, 515483463��΢��mid�й̶���ȥ��ʱ��
    private static final long WEIBO_INTI_TIME = 515483463L;
    /**
     * ����uid��mid��ȡkey(�ܳ���63)��mid�Ǿ�������BLOOMFILTER_SAVE_DAYS�����ڵĲŷŽ�ȥ��������ܷ�Ž���ȡbf�ı�׼��<br>
     * key��ǰ12λλʱ��λ����ʾ��ǰ��mid�������ǹ涨��ʱ�������(������Ϊ2014-08-25)�������Ϊ�˱�֤bf���key��ƽ��������<br>
     * key�ĺ�51λΪuid��mid��CRC64��51λ�Ľ��
     * @param uid
     * @param mid
     * @return
     */
    public static String getBloomFilterKeyFromUidAndMid(long uid, long mid) {
        if(!isMidInOneYear(mid)){
            return "";
        }
        StringBuilder result = new StringBuilder(64);
        //��ʾ��ǰ��mid�������ǹ涨��ʱ���������������Ϊ2014-08-25����ת��Ϊ����ʱ��
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        result.append(midDays);
        
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
        System.out.println(crc64HashResult);
       long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64����ܳ��ָ��������ܹ�ֱ��ȡ�����,ֱ��λ��ȡֵ,ȡֵ��λ
        result.append(temp);
        return result.toString();
    }
    /**
     * mid�Ƿ���BLOOMFILTER_SAVE_DAYS�ڵ�mid���������ô����true
     * @param mid
     * @return
     */
    private static boolean isMidInOneYear(long mid) {
        long currTime = System.currentTimeMillis()/1000;//unixʱ�����ȷ����
        long midTime = getTimeFromId(mid);//��mid���õ�unixʱ���
        if(Math.abs(currTime - midTime) < ALL_SECONDS_IN_BLOOMFILTER){
            return  true;
        }
        return false;
    }
    /**
     * ת��Ϊ����ʱ��
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
