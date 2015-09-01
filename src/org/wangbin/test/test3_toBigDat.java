package org.wangbin.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class test3_toBigDat {
    public static void main(String[] args) {
        String str = "C:"+File.separator
                + "Users"+File.separator + "wb"+File.separator + "Desktop"+File.separator +
                "status_att_1408.txt";
//                "favorite" +File.separator + "result";
        System.out.println(str);
        File theFile = new File(str);
        LineIterator it  = null;
        long lon = System.currentTimeMillis();
        try {
            int i = 0;
            it = FileUtils.lineIterator(theFile, "UTF-8");
            while (it.hasNext()) {
                i++;
//                String line = it.nextLine();
//                String []strs = line.split("\t");
//                System.out.println("");
                // do something with line
//                System.out.println(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            LineIterator.closeQuietly(it);
            System.out.println(System.currentTimeMillis()-lon);
        }
    }
}
