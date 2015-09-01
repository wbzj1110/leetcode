package org.wangbin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
/**
 * ���ֱ��java����ִ��
 * ������
 * 1������У�����е�����  �����·ֱ�� ÿ���Ƿ��ǰ���midγ�� 
 * 2������У�����bloomfilter��ʱ��ÿ�����ݶ�Ӧ��key �Ƿ�����
 * bloomfilter��Ҫ��~~ÿ���ļ�2GB���������ϱ߼���ƽ��һ���ļ���85s���ҡ�
 * ɨ12*32���ļ������Ҫ9��Сʱ��
 * ע��5M�����ɨ�ļ����ٶȱȽϿ죬У���ʱ����Կ����̴߳�������bloomfilter�����ʱ��
 * �����1���̶߳������̴߳���
 * ���̴߳���
 * @author wb
 * @date 2015-8-29 ����9:02:24
 */
public class test4_toBigData_ForMultiThreads {
    public static void main(String[] args) {
        // String str =
        // "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
        // File.separator
        // + "status_att_1408.txt";
        
        
         String str =
         "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
         File.separator
         + "status_att_1409.txt";
//       doIt(str);
       doWrite();
        
        
        
       
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

    private static void doWrite() {
        // TODO Auto-generated method stub
        String str =
                "C:" + File.separator + "Users" + File.separator + "wb" + File.separator + "Desktop" +
                File.separator
                + "testMultiTh2.txt";
        BufferedWriter bufWriter = null;
        FileWriter fileW = null; 
        Long count = 0l;
        System.out.println("��ʼд�ˣ�д���λ����:" + str);
        try {
            fileW = new FileWriter(str);
            bufWriter = new BufferedWriter(fileW);  
           while(true){
               bufWriter.write(count++ +"");  
               //newLine()����д�������ϵͳ�����Ļ����ַ�����ִ�л�����ʱ��OS��������������ֻ����ַ�  
               bufWriter.newLine();  
               if(count==100000l){
                   return;
               }
           }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            try {
                if(bufWriter!=null){
                    bufWriter.close();
                }
                if(fileW!=null){
                    fileW.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }finally{
                System.out.println("д����");
            }
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
            reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�

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
