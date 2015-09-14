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
 * @date 2015-8-26 ����10:25:56
 */
public class test2 {
    public static void main(String[] args) {
        String key = "";
        Long uid = (long) 1803626420l;
        Long id = (long) 3738779545298037l;
//        System.out.println("���һ��");
//        System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//        System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //��¼�ĳ���ĵ�һ����¼
//         uid = (long) 3091983213l;
//         id = (long) 3738788079451178l;
//         System.out.println("�������");
//         System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//         System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //Ҳ�и�����ļ�¼
//         uid = (long) 5058695223l;
//         id = (long) 3880128227874834l;
//         System.out.println("�������");
//        System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//        System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//        //Ҳ�и�����ļ�¼
//        uid = (long) 2727046555l;
//        id = (long) 3881656467222381l;
//        System.out.println("����ģ�");
//       System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//       System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//     //Ҳ�и�����ļ�¼
//       uid = (long) 3790383711l;
//       id = (long) 3738546470771002l;
//       System.out.println("����壺");
//       System.out.println(" key :" + getBloomFilterKeyFromUidAndMid(uid, id));
//       System.out.println(" key2:" + getBloomFilterKeyFromUidAndMid2(uid, id));
//       
       uid = (long) 2607718135l;
       id = (long) 3661913085997764l;
       System.out.println("�������");
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
        System.out.println("��������max����key��47287793490112720") ;
        System.out.println(" dff2:2251799813685248");
        
        
        System.out.println("~~~~");
       List<String> times = Lists.newArrayList();
       times.add("3760652126647685");
       times.add("3760652126647685");
//       times.add("3872240233629331");
       for(String time:times){
           long unixTime  = getTimeFromId(time);
           String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(unixTime * 1000));
           System.out.println("�鿴��Сʱ�� :  ~~" + date);
       }
       
       
    }
    
    private static final int BLOOMFILTER_SAVE_DAYS = 380;//���Ƕ���bloomfilter�洢�����������
    private static final long ONE_DAY_TIME_SECONDS = 86400;//һ�������
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf�д�ŵ������Ǿ������ڶ�����֮�ڵ�����
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1406822400l;//��ʾ2014��8��1�� 0ʱ0��0��
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
//        Long lon = System.currentTimeMillis();
//        for(int i = 0;i <=1000;i++){
//            
        if(!isMidInOneYear(mid)){
            return "";
        }
        StringBuilder result = new StringBuilder(64);
        //��ʾ��ǰ��mid�������ǹ涨��ʱ���������������Ϊ2014-08-25����ת��Ϊ����ʱ��
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        result.append(midDays);
        
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
       long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64����ܳ��ָ��������ܹ�ֱ��ȡ�����,ֱ��λ��ȡֵ,ȡֵ��λ
        result.append(temp);
        return Long.valueOf(result.toString()) + "";
//        }
//        System.out.println("java Stringbuilder��" + (System.currentTimeMillis() - lon));
//        return "";
    }
    /**
     * java��λƴ�ӵ�
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
            //��ʾ��ǰ��mid�������ǹ涨��ʱ���������������Ϊ2014-08-25����ת��Ϊ����ʱ��
            System.out.println("~~~" + getLocalTime(getTimeFromId(mid)));
            long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
//            long midDays=10;
            String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
            long crc64HashResult = CRC64Algorithm.crc64Long(unionStr);
            
            String unionStr2 =String.valueOf(uid) + String.valueOf(mid);
            long crc64HashResult2 = CRC64Algorithm.crc64Long(unionStr);
            
            long temp = crc64HashResult & ((1L <<52) - 1);
            //crc64����ܳ��ָ��������ܹ�ֱ��ȡ�����,ֱ��λ��ȡֵ,ȡֵ��λ
            
            long temp2 = crc64HashResult2 & ((1L <<52) - 1);
            long keyJava2 = (midDays<<(51))|temp2;
            long keyJava = (midDays<<(51))|temp;
            return keyJava+"";
//        }
//        System.out.println("javaλ���㣺" + (System.currentTimeMillis() - lon));
//        return "";
    }
    /**
     * mid�Ƿ���BLOOMFILTER_SAVE_DAYS�ڵ�mid���������ô����true
     * @param mid
     * @return
     */
    //2015��9��1�գ�֮������ݿ�������ķ���bloomfilter
    private static final long BLOOMFILTER_NO_LIMIT_TIME = 1441036800L;
    private static boolean isMidInOneYear(long mid) {
        long currTime = System.currentTimeMillis()/1000;//unixʱ�����ȷ����
        long midTime = getTimeFromId(mid);//��mid���õ�unixʱ���
        System.out.println(getLocalTime(currTime));
        if(Math.abs(currTime - midTime) < ALL_SECONDS_IN_BLOOMFILTER || getLocalTime(mid) > BLOOMFILTER_NO_LIMIT_TIME){
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
    public static long getTimeFromId(String id) {
        return getTimeNumberFromId(Long.valueOf(id)) + ID_OFFSET;
    }
    public static long getTimeNumberFromId(long id) {
        return id >> IDC_SEQ_BIT_LENGTH;
    }
    public static final long SEQ_BIT_LENGTH = 18;
    public static final long IDC_SEQ_BIT_LENGTH = 4 + SEQ_BIT_LENGTH;
    
}
