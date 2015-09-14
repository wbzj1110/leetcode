package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address
 * combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @author wb
 * @date 2015-9-14 下午11:12:18
 */
public class _093RestoreIPAddresses {
    public static void main(String[] args) {
        String str = "25525511135";
        str = "010010";
        List<String> result = new _093RestoreIPAddresses().restoreIpAddresses(str);
        for (String s : result) {
            System.out.println(s);
        }


    }

    /**
     * 读完题就想到递归、、、、
     * 
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if (s == null) {
            //s=""，返回[]
            return null;
        }
        if(s.length()==0){
            return Collections.EMPTY_LIST;
        }
        List<String> result = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        helper(s, 0, temp, result);

        return result;
    }

    private void helper(String s, int index, List<String> temp, List<String> result) {
        // TODO Auto-generated method stub
        if (temp.size() == 4) {
            if (index == s.length()) {
                StringBuilder sb = new StringBuilder(15);
                for (int i = 0; i < 4; i++) {
                    sb.append(temp.get(i));
                    if (i != 3) {
                        sb.append(".");
                    }
                }
                result.add(sb.toString());
            }
            return;
        }
        int len = s.length();
        for (int i = index; i < index + 3 && i < len; i++) {// 每次最多取3个，不能更多啦
            /*
             * "010010"对于这个例子，不能出现0XX的情况哈
             * 例如 
             * ["0.1.0.010","0.1.00.10","0.1.001.0","0.10.0.10","0.10.01.0","0.100.1.0","01.0.0.10","01.0.01.0","01.00.1.0","010.0.1.0"]
             * 实际应该是：["0.10.0.10","0.100.1.0"]
             */
            if (s.charAt(index) == '0' && i > index) {
                break;
            }
            String pre = s.substring(index, i + 1);
            int num = Integer.parseInt(pre);
            if (num > 255) {
                continue;
            }
            temp.add(pre);
            helper(s, i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
}
