package org.wangbin.leetcode;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * @author wb
 * @version 2015-8-7 ����8:37:12
 */
public class _043MultiplyStrings {
	public static void main(String[] args) {
		String num = "385";
		String num2 = "97";
		System.out.println(new _043MultiplyStrings().multiply(num, num2));
	}

	/**
	 * ֱ�ӳ˻����������ÿ�ζ�Ҫ����single digit��ˣ����81����������� ����385 * 97, ���Ǹ�λ=5 * 7��ʮλ=8 * 7
	 * + 5 * 9 ����λ=3 * 7 + 8 * 9 �� ����ÿһλ��һ��Int��ʾ������һ��int[]���档 ���������󳤶���num1.len
	 * + num2.len������99 * 99����󲻻ᳬ��10000������4λ�͹��ˡ�
	 * ���ָ�λ�ں���ģ���������10��0�η�����ϧ��Ӧλ������index����0����n-1���� ���Ըɴ��Ȱ�string reverse�˴���������öࡣ
	 * �����ǰ���0Ҫ���
	 * �ȼ���ÿ��λ�õĽ�� Ȼ������ٽ�λ
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();
		// even 99 * 99 is < 10000, so maximaly 4 digits
		int a =0;
		int b = 0;
		int[] d = new int[num1.length() + num2.length()];
		for (int i = 0; i < num1.length(); i++) {
			a = num1.charAt(i) - '0';
			for (int j = 0; j < num2.length(); j++) {
				b = num2.charAt(j) - '0';
				d[i + j] += a * b;
			}
		}
		StringBuilder sb = new StringBuilder();
		int digit = 0;
		int carry = 0;
		for (int i = 0; i < d.length; i++) {
			digit = d[i] % 10;
			carry = d[i] / 10;
			sb.insert(0, digit);
			if (i < d.length - 1  && carry >0) {
				d[i + 1] += carry;
			}

		}
		// trim starting zeros
		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
}
