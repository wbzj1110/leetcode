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
 * ���ֱ��java����ִ��
 * ������
 * 1������У�����е�����  �����·ֱ�� ÿ���Ƿ��ǰ���midγ�� 
 * 2������У�����bloomfilter��ʱ��ÿ�����ݶ�Ӧ��key �Ƿ�����
 * bloomfilter��Ҫ��~~ÿ���ļ�2GB���������ϱ߼���ƽ��һ���ļ���85s���ҡ�
 * ɨ12*32���ļ������Ҫ9��Сʱ��
 * ע��5M�����ɨ�ļ����ٶȱȽϿ죬У���ʱ����Կ����̴߳�������bloomfilter�����ʱ��
 * �����1���̶߳������̴߳���
 * @author wb
 * @date 2015-8-29 ����9:02:24
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
//        //���Ի����ԣ�ֻ����1408����
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�,����ռ�ù�����ڴ�
                String line = "";
                
                while((line = reader.readLine()) != null){  
                    count++;
                    //ÿ��countInterval�д��¼
//                    if(count%countInterval==0){
//                        System.out.println("##@## ##@## ##@##LikesServiceImpl.setBloomFilterForFixData"+ fileName +" �����" 
//                                + count + "��~~~���е�����Ϊ" + line );
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
                       System.out.println("!!!!�ҵ�8814��");
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
            System.out.println("�ļ�ɨ�����~~������" + count);
            System.out.println("���ս��չʾ sets�Ĵ�СΪ��" + sets.size());
            for(String set:sets){
                System.out.println(set);
            }
            System.out.println("���ս��չʾ sets2�Ĵ�СΪ��" + sets.size());
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�,����ռ�ù�����ڴ�
                String line = "";
                
                while((line = reader.readLine()) != null){  
                    count++;
                    //ÿ��countInterval�д��¼
                    if(count%countInterval==0){
                        System.out.println("##@## ##@## ##@##LikesServiceImpl.setBloomFilterForFixData"+ fileName +" �����" 
                                + count + "��~~~���е�����Ϊ" + line );
                    }
                    String temp = new String(line);
                    if(org.apache.commons.lang3.StringUtils.isBlank(temp)){
                        continue;
                    }
                    temp = org.apache.commons.lang3.StringUtils.substringBetween(temp, "�߳�ִ���д��󣬼�¼����������Ϣֵ", "e:");
                    temp = org.apache.commons.lang3.StringUtils.substringAfter(temp, "��Ϣ��");
                    if(org.apache.commons.lang3.StringUtils.isBlank(temp)){
                        continue;
                    }else{
                        temp = org.apache.commons.lang3.StringUtils.trim(temp);
                    }
                    Long uid = Long.parseLong(temp.split("\t")[2]);
                    Long mid = Long.parseLong(temp.split("\t")[0]);
                    final String tempLine = line;
                    final long tempCount = count;
                    //һ��д�߳���de ����
                    //ע:����ط����ܹ�һ�ζ�ȡ�������ݣ�Ȼ����̴߳�����Ϊ����ȡ�ǰ���mid��γ��ȡ��
                    //���շ��������ǰ���uidȡ�ģ���ͬһ��mid��Ӧ��uidһ����������ظ�������һ��ȡ���У�����
                    //����û��Ч���ϵ�������
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
            reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 10 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�

            String line = "";
            long slow = 0l;
            long fast = 0l;//��mid
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
                            System.out.println("����У����У�鵽�� " + count + " ��~~likes/wangbin/checkSortData,��ĿǰΪֹ��mid��˳�����+ file" + file.getName());
                        }
                        String[] strs = line.split("\t");
                        if(strs[0].length()<10){
                            System.out.println("##@##��������ֵ�����" + line );
//                            throw new RuntimeException("##@##��������ֵ�����" + line );
                        }
                        fast = Long.parseLong(strs[0]);
                        uid= Long.parseLong(strs[2]);
                        key = getBloomFilterKeyFromUidAndMid(uid, fast);
                        Long tempKey = Long.parseLong(key);
                        //У��ȫ������~~key����һ����������max_diff������ƽ������
                        if(tempKey > max_key){
                           if(max_key!=-1){
                               if(tempKey - max_key > max_diff){
                                   System.out.println("##@##������max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line����Ϣ��"+ line );
//                                   throw new RuntimeException("##@##������max_diff~~ key:" +key + "  max_key" +max_key + "  max_diff" +max_diff+  " line����Ϣ��"+ line );
                               }
                           }
                            max_key = tempKey;
                        }
                        
                        if(key.startsWith("-")){
                            System.out.println("##@##false~~ key����:" +key +" uid:" + uid + "  mid:" + fast );
//                            throw new RuntimeException("##@##false~~ key����:" +key +" uid:" + uid + "  mid:" + fast  );
                        }
                        if (count % 1000000 == 0) {
                            System.out.println("fast :" + fast + "  slow:" + slow + "  key:" + key + " max_key:" + max_key + "  max_diff:" + max_diff );
                        }
                        
                        
                        if (slow <= fast) {//�Ƚ��Ƿ����ϸ����
                            slow = fast;
                        } else {
                            System.out.println("##@##false~~:" + fast + "   slow :" + slow);
                            throw new RuntimeException("##@##false~~:" + fast + "   slow :" + slow);
                        }
                        // System.out.println("����ļ�������Ϊ:" + count);
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
            System.out.println("##@## file:" + str + " �ǰ���mid����ŵ�~~~Ȼ������ļ��ĳ���Ϊ��" + count + "  У���ļ��ĺ�ʱΪ"
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
    private static final int BLOOMFILTER_SAVE_DAYS = 400;//���Ƕ���bloomfilter�洢�����������
    private static final long ONE_DAY_TIME_SECONDS = 86400;//һ�������
    private static final long ALL_SECONDS_IN_BLOOMFILTER = BLOOMFILTER_SAVE_DAYS * ONE_DAY_TIME_SECONDS;//bf�д�ŵ������Ǿ������ڶ�����֮�ڵ�����
   
    private static final long LIKES_EXISTS_BLOOMFILTER_SINCE= 1406822400l;//��ʾ2014��8��1�� 0ʱ0��0��
    //unix�׶�ʱ���뱱��ʱ���8Сʱ������
    private static final long EIGHT_HOUR_TIME_SECONDS = 28800;//8*3600
    //���ʱ�䣬2005-01-01 00��00:00��ʼ, 
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
        //��ʾ��ǰ��mid�������ǹ涨��ʱ���������������Ϊ2014-08-25����ת��Ϊ����ʱ��
        long midDays = (getLocalTime(getTimeFromId(mid))- LIKES_EXISTS_BLOOMFILTER_SINCE) / ONE_DAY_TIME_SECONDS;
        String unionStr = new String(new StringBuilder(128).append(uid).append(mid));
        long crc64HashResult = crc64Long(unionStr);
        long temp = crc64HashResult & ((1L <<51) - 1);
        //crc64����ܳ��ָ��������ܹ�ֱ��ȡ�����,ֱ��λ��ȡֵ,ȡֵ��λ
        long keyJava = (midDays<<(51))|temp;
        return keyJava+"";
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
