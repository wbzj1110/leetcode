package org.wangbin.leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * 
 * @author wb
 * @version 2015-8-1 ����5:12:12
 */
public class _024SwapNodesinPairs {
	public static void main(String[] args) {
		ListNode node5 = new ListNode(5);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(new _024SwapNodesinPairs().swapPairs4(node1));
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		// ��ֻ��һ��Ԫ�ص����
		if (head.next == null) {
			return head;
		}
		ListNode i = head; // iָ���1��
		ListNode j = i.next; // jָ���2��
		ListNode k = j.next; // kָ���3��

		head = head.next;
		while (j != null) {
			j.next = i;
			if (k != null && k.next != null) { // ����ż�����ڵ�
				i.next = k.next;
			} else { // �����������ڵ�
				i.next = k;
			}

			// ����i��j��k��ֵ��ǰ������
			i = k;
			if (k != null) {
				j = k.next;
			} else {
				j = null;
			}
			if (k != null && k.next != null) {
				k = k.next.next;
			} else {
				k = null;
			}
		}
		return head;
	}

	/**
	 * ��Ҫ����fakehead��ָ��ԭָ��ͷ����ֹ������������ָ�룬ptr1ʼ��ָ����Ҫ������pair��ǰ��һ��node��
	 * ptr2ʼ��ָ����Ҫ������pair�ĵ�һ��node��
	 * 
	 * Ȼ����ǽ�����������
	 * 
	 * ��Ҫ��һ����ʱָ��nextstart�� ָ����һ����Ҫ������pair�ĵ�һ��node����֤��һ�ν�������ȷ���С�
	 * 
	 * Ȼ��ͽ�������������������ָ��Ų���ͺá�
	 * 
	 * ��������Ϊ����ʱ��ptr2.next����Ϊnull��
	 * 
	 * ��������Ϊż��ʱ��ptr2����Ϊnull��
	 * 
	 * ���԰������������Ϊ��ֹ��������while�жϾͺã���󷵻�fakehead.next�� ��Ϊһֱ�ǵ�һ����ڶ���Ԫ�ؽ�������һ��Ԫ��������
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs2(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode fakehead = new ListNode(-1);
		fakehead.next = head;

		ListNode ptr1 = fakehead;// ������Ԫ�ص�ǰһ��
		ListNode ptr2 = head;// Ҫ������Ԫ�صĵ�һ��

		while (ptr2 != null && ptr2.next != null) {
			ListNode nextstart = ptr2.next.next;// ��Ҫ��һ����ʱָ��nextstart��
												// ָ����һ����Ҫ������pair�ĵ�һ��node
			ptr2.next.next = ptr2;// ��Ϊ��·
			ptr1.next = ptr2.next;// ֮ǰ��ָ��Ҫ����Ԫ�صĵڶ���
			ptr2.next = nextstart;
			ptr1 = ptr2;
			ptr2 = ptr2.next;
		}
		return fakehead.next;

	}

	public ListNode swapPairs3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode point = new ListNode(0);
		point.next = head;//point��ָ�򽻻�λ��֮ǰ��λ��
		head = point;
		while (point.next != null && point.next.next != null) {
			ListNode tmp = point.next.next.next;// ��¼��һ��Ҫ������λ��
			point.next.next.next = point.next;// �γɻ�������1,2,3,4 ���ӣ������1,2,1,2
			point.next = point.next.next;// ���û�·��pointָ����ǽ���ǰһ��λ�ã���ô�ͳ�Ϊ��2,1,2,1
			point.next.next.next = tmp;// Ȼ��ͳ���2,1,3,4��
			point = point.next.next;// ָ��1��λ�á�
		}
		return head.next;
	}

	public ListNode swapPairs4(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode point = new ListNode(0);
		point.next = head;
		head = point;
		ListNode temp = null;
		while (point.next != null && point.next.next != null) {
			temp = point.next.next.next;
			point.next.next.next = point.next;
			point.next = point.next.next;
			point.next.next.next = temp;
			point = point.next.next;

		}
		return head.next;
	}
}
