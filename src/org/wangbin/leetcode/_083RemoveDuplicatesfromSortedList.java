package org.wangbin.leetcode;

/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author wb
 * @date 2015-8-16 上午10:23:04
 */
public class _083RemoveDuplicatesfromSortedList {
	public static void main(String[] args) {
//		ListNode n5 = new ListNode(3);
//		ListNode n4 = new ListNode(3, n5);
//		ListNode n3 = new ListNode(2, n4);
//		ListNode n2 = new ListNode(1, n3);
//		ListNode n1 = new ListNode(1, n2);
		ListNode n3 = new ListNode(2);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);
		System.out.println(new _083RemoveDuplicatesfromSortedList()
				.deleteDuplicates2(n1));
		System.out.println(System.currentTimeMillis());
	}

	/**
	 * 只可能出现一次，太简单了，直接看当前指针与下一个是否相等即可
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode result = new ListNode(-1, head);
		ListNode node = head;
		while (node.next != null) {
			// 重复了
			if (node.val == node.next.val) {
				node.next = node.next.next;
			} else {
				node = node.next;
			}
		}
		return result.next;
	}

	/**
	 * 下边用经典的做法，快慢指针，如果有多个重复滴咋办？
	 * 用两个指针一前一后指向链表。如果两个指针指向的值相等，那么就让第二个指针一直往后挪，挪到与第一个指针不同为止
	 * 。然后让第一个指针的next指向第二个指针，两个指针同时往后挪，进行下面的操作。
	 * 
	 * 需要注意，当list的结尾几个node是重复的时候，例如1->2->3->3，那么ptr2会指向null，需要特殊处理，令ptr1.next =
	 * null，这样list尾部就不会丢。
	 * 
	 * 其他情况就不用特殊处理结尾了，因为结尾没有重复值，只须遍历就够了，不用特殊处理尾部。
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null) {
			if (slow.val == fast.val) {
				fast = fast.next;
				if (fast == null) {
					slow.next = null;
				}
			} else {
				slow.next = fast;
				slow = slow.next;
				fast = fast.next;
			}
		}
		return head;
	}
}
