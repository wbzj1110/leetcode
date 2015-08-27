package org.wangbin.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits, determine the total
 * number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 * 
 * @author wb
 * @date 2015-8-27 ����8:10:38
 */
public class _091DecodeWays {
    public static void main(String[] args) {
        String str = "123";
        char c = '1';
//        System.out.println(c);
//        System.out.println((int)c);
        System.out.println(new _091DecodeWays().numDecodings(str));
        str="1023";
//        System.out.println(new _090DecodeWays().numDecodings2(str));
    }
    /**
     * һλ���鶯̬�滮��dp[i]��ʾ1~i��λ���ж��ٸ�decodeways
     * 12��2�������2���ⷨ������2�������
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if(s==null||s.length()==0|| s.equals("0")){
            return 0;
        }
        int []dp = new int [s.length()+1];
        dp[0] = 1;
        if(isValid(s.substring(0, 1))){
            dp[1] = 1;
        }else{
            dp[1] = 0;
        }
        int count = 0;
        char []cs = s.toCharArray();
        for(int i = 2,len = s.length();i<=len;i++ ){
            if(isValid(s.substring(i-1, i))){
                dp[i]+=dp[i-1];
            }
            if (isValid(s.substring(i-2,i))) {
                dp[i]+=dp[i-2];
            }
        }
        
        return dp[s.length()];
    }
    private boolean isValid(String substring) {
        // TODO Auto-generated method stub
        if(substring.charAt(0)=='0'){
            return false;
        }
        int code = Integer.parseInt(substring);
        return code>=1&&code<=26;
    }
    /**
     * �̵��ϱ������⣬���������123  ����ô�Ͻ�Ӧ����
     * 1  2  3  12  23
     * �����1023
     * 1 10 2 3 23
     * ���ʼ������¡���˵����ȫ�á����Ǿ�û�취�ˡ�
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<String>sets = new HashSet<String>();
        for(int i = 0,len = s.length();i <len;i++){
            for(int j =0;i+j <=len;j++){
                String temp = s.substring(i, i+j);
                if(temp==null||temp.length()==0){
                    continue;
                }
                int tempI = Integer.parseInt(temp);
                if(tempI>26){
                    break;
                }else{
                    if(tempI >=0&&tempI<=26){
                        sets.add(tempI+"");
                    }
                }
            }
        }
        
        return sets.size();
    }
}
