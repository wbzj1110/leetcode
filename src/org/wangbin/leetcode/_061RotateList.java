package org.wangbin.leetcode;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * For example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 * 
 * @author wb
 * @date 2015-8-11 上午8:59:23
 */
public class _061RotateList {
	public static void main(String[] args) {
		// ListNode n5 = new ListNode(5);
		// ListNode n4 = new ListNode(4, n5);
		// ListNode n3 = new ListNode(3, n4);
		// ListNode n2 = new ListNode(2, n3);
		// ListNode n1 = new ListNode(1, n2);
		ListNode n1 = new ListNode(1);
		System.out.println(new _061RotateList().rotateRight(n1, 0));

	}

	/**
	 * 快慢指针
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || head.next == null || n == 0) {
			return head;
		}
		ListNode counterNode = head;
		int len = 0;// 链表的长度
		while (counterNode != null) {
			counterNode = counterNode.next;
			len++;
		}
		n = n % len;
		if (n == 0) {
			return head;
		}
		ListNode result = new ListNode(-1);
		result.next = head;
		ListNode slow = head;
		ListNode fast = head;
		// 快的先走
		while (n > 0) {
			fast = fast.next;
			n--;
		}
		// 然后快的走到最后的位置
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		// 结束了 这个时候 结果指一下slow.next
		result = slow.next;
		// 形成环
		fast.next = head;
		// 解环
		slow.next = null;
		return result;
	}
}
