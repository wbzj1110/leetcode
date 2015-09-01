package org.wangbin.test;

public class testBFkey {
    public static void main(String[] args) {
        String max_diff = "2251799813685247";
        String max_key = "2251759782783702";
        
        String key = "11834633329163077";
        System.out.println(max_diff.length());
        System.out.println(max_key.length());
        System.out.println(key.length());
        String key2 ="0886164399411989";
        System.out.println(key2.length());
        System.out.println(   "max_diff :" + (((1L <<(63-12)) - 1)));
        //´Ó2015-08-30Æð
        Long maxDiff = 2251799813685247l;
        long keyJava = (395l<<(51))|maxDiff;
        System.out.println(keyJava);
    }
}
