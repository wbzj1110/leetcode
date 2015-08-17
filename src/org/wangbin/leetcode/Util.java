package org.wangbin.leetcode;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;

/**
 * 公共方法
 * @author wb
 * @date 2015-8-8 上午11:53:20
 */
public class Util {
	private static ThreadLocal<MessageDigest> MD5 = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("MD5");
			} catch (Exception e) {
			}
			return null;
		}
	};
	public static String md5Digest(byte[] data) {
		MessageDigest md5 = MD5.get();
		md5.reset();
		md5.update(data);
		byte[] digest = md5.digest();
		return encodeHex(digest);
	}
	public static String encodeHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length + bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	public static void showListInList(List<List<Object>>ls){
		for(List<Object> l :ls){
			for(Object o :l){
				System.out.print(o + "  ");
			}
			System.out.println();
		}
	}
	public static void showListInListInteger(List<List<Integer>>ls){
		for(List<Integer> l :ls){
			for(Integer o :l){
				System.out.print(o + "  ");
			}
			System.out.println();
		}
	}
	
	public static void showDoubleArrya(int [][] ints){
		for(int []ints2 :ints){
			for(int i :ints2){
				System.out.print(i + "  ");
			}
			System.out.println();
		}
		System.out.println("~~~~end~~~");
	}
	public static void main(String[] args) {
		System.out.println(md5Digest("22118988031882430803FFF1280168603abdvcdfgnnghDDX".getBytes()));
        System.out.println(md5Digest("44567003227367176950FFF1280168603abdvcdfgnnghDDD".getBytes()));
        System.out.println(URLEncoder.encode("module=all"));
	}
}
