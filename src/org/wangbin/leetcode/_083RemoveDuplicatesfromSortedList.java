package org.wangbin.leetcode;

/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author wb
 * @date 2015-8-16 ����10:23:04
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
	 * ֻ���ܳ���һ�Σ�̫���ˣ�ֱ�ӿ���ǰָ������һ���Ƿ���ȼ���
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
			// �ظ���
			if (node.val == node.next.val) {
				node.next = node.next.next;
			} else {
				node = node.next;
			}
		}
		return result.next;
	}

	/**
	 * �±��þ��������������ָ�룬����ж���ظ���զ�죿
	 * ������ָ��һǰһ��ָ�������������ָ��ָ���ֵ��ȣ���ô���õڶ���ָ��һֱ����Ų��Ų�����һ��ָ�벻ͬΪֹ
	 * ��Ȼ���õ�һ��ָ���nextָ��ڶ���ָ�룬����ָ��ͬʱ����Ų����������Ĳ�����
	 * 
	 * ��Ҫע�⣬��list�Ľ�β����node���ظ���ʱ������1->2->3->3����ôptr2��ָ��null����Ҫ���⴦����ptr1.next =
	 * null������listβ���Ͳ��ᶪ��
	 * 
	 * ��������Ͳ������⴦���β�ˣ���Ϊ��βû���ظ�ֵ��ֻ������͹��ˣ��������⴦��β����
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
