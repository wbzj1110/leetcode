package org.wangbin.leetcode;

/**
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 链表加法
 * 
 * @author wb
 * @version 2015-7-25 上午9:56:23
 */
public class _002AddTwoNumber {
	public static void main(String[] args) {
		ListNode n3 = new ListNode(3);
		ListNode n2 = new ListNode(4, n3);
		ListNode n1 = new ListNode(2, n2);
		System.out.println(n1);
		ListNode nn3 = new ListNode(4);
		ListNode nn2 = new ListNode(6, nn3);
		ListNode nn1 = new ListNode(5, nn2);
		System.out.println(nn1);
		System.out.println(addTwoNumbers(n1, nn1));

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 下边这个地方不能要，因为还存在这样的
		/**
		 * More Details
		 * 
		 * Input: [5], [5] Output: [0] Expected: [0,1]
		 */
		// if(l1==null){
		// return l2;
		// }
		// if(l2==null){
		// return l1;
		// }
		int carry = 0;
		ListNode newHead = new ListNode(0);
		ListNode p3 = newHead;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				carry += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				carry += l2.val;
				l2 = l2.next;
			}
			p3.next = new ListNode(carry % 10);
			p3 = p3.next;
			carry /= 10;
		}
		if (carry == 1) {
			p3 = new ListNode(1);
		}
		return newHead.next;
	}

}
